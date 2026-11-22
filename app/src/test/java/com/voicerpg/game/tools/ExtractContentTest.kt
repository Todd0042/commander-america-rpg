package com.voicerpg.game.tools

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.google.gson.GsonBuilder
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.voicerpg.engine.combat.MovesetTable
import com.voicerpg.engine.engine.StoryEncounters
import com.voicerpg.engine.engine.StoryScript
import com.voicerpg.engine.model.DialogueNode
import com.voicerpg.engine.model.DialogueSpeaker
import com.voicerpg.engine.model.Enemy
import com.voicerpg.engine.model.EncounterDefinition
import com.voicerpg.engine.model.HeroClass
import com.voicerpg.engine.model.PartyMember
import com.voicerpg.engine.model.Spell
import com.voicerpg.engine.model.SpellGraphics
import com.voicerpg.engine.model.StoryScene
import com.voicerpg.engine.template.TemplateHero
import org.junit.Test
import java.io.File

/**
 * One-off tool (engine convergence, step 5b): turns Commander America's Kotlin content into the shared
 * engine's JSON schema. Runs on this repo's pre-convergence code and writes to app/build/extracted/game,
 * plus REPORT.md listing everything that needs a human decision. Delete after the switch-over.
 *
 * Run: ./gradlew testDebugUnitTest --tests '*ExtractContentTest*'
 */
class ExtractContentTest {

    private val out = File("build/extracted/game").also { it.mkdirs() }
    private val gson = GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create()
    private val report = StringBuilder("# Commander America content extraction report\n\n")
    private val migration = File("../docs/engine-migration")

    private fun hex(c: Color): String = String.format("#%06X", c.toArgb() and 0xFFFFFF)
    private fun write(path: String, json: Any) {
        val f = File(out, path); f.parentFile.mkdirs(); f.writeText(gson.toJson(json) + "\n")
    }

    // Echoes of Logos leftovers in this repo: never extracted.
    private val echoesIds = setOf("aethel", "cedric", "lyra", "zephyr", "malakor", "galahault", "nocturne", "ouros", "vaelor")

    @Test
    fun extract() {
        val scenes: Collection<StoryScene> = StoryScript.ALL_SCENES.values.distinctBy { it.id }  // the map has legacy alias keys
        val nodes: Collection<DialogueNode> = StoryScript.ALL_NODES.values

        // ---- reachability from both tracks' starts (drop leftovers nothing reaches) ------------------
        val starts = listOf("act1_intro", "act1_tb_ch1_intro")
        val nodeById = StoryScript.ALL_NODES
        val reached = mutableSetOf<String>()
        val queue = ArrayDeque(starts)
        while (queue.isNotEmpty()) {
            val id = queue.removeFirst()
            if (!reached.add(id)) continue
            val n = nodeById[id] ?: continue
            n.nextNodeId?.let { queue.add(it) }
            n.choices.forEach { queue.add(it.nextNodeId) }
            n.hubCompletion?.let { queue.add(it.redirectToNodeId) }
            n.changeSceneId?.let { sid -> StoryScript.ALL_SCENES[sid]?.let { queue.add(it.initialNodeId) } }
        }
        val keptNodes = nodes.filter { it.id in reached }
        val keptSceneIds = keptNodes.mapNotNull { it.changeSceneId }.toSet() +
            scenes.filter { it.initialNodeId in reached }.map { it.id } +
            starts.mapNotNull { trackStartScene(it) }   // each track opens in its own scene
        // A kept scene whose entry node was dropped gets the first kept node that enters it (chapter menu).
        val keptScenes = scenes.filter { it.id in keptSceneIds }.map { sc ->
            val trackStart = starts.firstOrNull { trackStartScene(it) == sc.id }
            if (trackStart != null && sc.initialNodeId != trackStart) {
                report.append("- scene ${sc.id}: entry ${sc.initialNodeId} -> $trackStart (this track's start)\n")
                sc.copy(initialNodeId = trackStart)
            } else if (sc.initialNodeId in reached) sc else {
                val entry = keptNodes.firstOrNull { it.changeSceneId == sc.id }?.id
                    ?: starts.firstOrNull { st -> StoryScript.ALL_SCENES.values.any { it.id == sc.id } && st in reached && sc.id == trackStartScene(st) }
                report.append("- scene ${sc.id}: entry ${sc.initialNodeId} was unreachable, now ${entry ?: "⚠ NONE"}\n")
                sc.copy(initialNodeId = entry ?: sc.initialNodeId)
            }
        }
        report.append("## Story\n- nodes: ${nodes.size} defined, ${keptNodes.size} reachable from the two track starts (kept)\n")
        report.append("- scenes: ${scenes.size} defined, ${keptScenes.size} kept\n")
        val droppedNodes = nodes.filter { it.id !in reached }.map { it.id }
        if (droppedNodes.isNotEmpty()) report.append("- dropped (unreachable) nodes: ${droppedNodes.joinToString()}\n")
        val missingLinks = keptNodes.flatMap { n -> (listOfNotNull(n.nextNodeId) + n.choices.map { it.nextNodeId }).filter { it !in nodeById } .map { "${n.id} -> $it" } }
        if (missingLinks.isNotEmpty()) report.append("- ⚠ links to nodes that don't exist: ${missingLinks.joinToString()}\n")

        write("story/scenes.json", mapOf("scenes" to keptScenes.map { s ->
            linkedMapOf(
                "id" to s.id, "name" to s.name, "chapterTitle" to s.chapterTitle, "backgroundAsset" to s.backgroundAsset,
                "initialNodeId" to s.initialNodeId, "ambientDescription" to s.ambientDescription, "musicAsset" to s.musicAsset,
                "chapterIndex" to s.chapterIndex, "actNumber" to s.actNumber, "actTitle" to s.actTitle,
                "milestoneTitle" to s.milestoneTitle, "milestoneLocation" to s.milestoneLocation, "milestoneSummary" to s.milestoneSummary,
                "milestoneIcon" to s.milestoneIcon, "defaultObjective" to s.defaultObjective
            )
        }))
        val terminals = keptNodes.filter { it.nextNodeId == null && it.choices.isEmpty() && it.triggerBattleEncounterId == null && it.hubCompletion == null }
        report.append("- terminal nodes (now set game_completed): ${terminals.map { "${it.id} (was ${it.setFlagOnEnter})" }}\n")
        val terminalIds = terminals.map { it.id }.toSet()
        write("story/nodes.json", mapOf("nodes" to keptNodes.map { it0 -> val n = if (it0.id in terminalIds) it0.copy(setFlagOnEnter = "game_completed") else it0
            linkedMapOf<String, Any?>(
                "id" to n.id, "speakerId" to n.speaker.id, "side" to n.side.name, "text" to n.text, "revisitText" to n.revisitText,
                "choices" to n.choices.map { c ->
                    linkedMapOf<String, Any?>(
                        "id" to c.id, "text" to c.text, "voiceKeywords" to c.voiceKeywords, "nextNodeId" to c.nextNodeId,
                        "completionFlag" to c.completionFlag, "requiredFlags" to c.requiredFlags.ifEmpty { null },
                        "metricDeltas" to c.metricDeltas.ifEmpty { null }, "assignHeroClassId" to c.assignHeroClassId,
                        "triggerMiniGameId" to c.triggerMiniGameId
                    ).filterValues { it != null }
                }.ifEmpty { null },
                "nextNodeId" to n.nextNodeId, "triggerBattleEncounterId" to n.triggerBattleEncounterId,
                "triggerMiniGameId" to n.triggerMiniGameId, "changeSceneId" to n.changeSceneId, "setFlagOnEnter" to n.setFlagOnEnter,
                "hubCompletion" to n.hubCompletion?.let { mapOf("requiredFlags" to it.requiredFlags, "redirectToNodeId" to it.redirectToNodeId) },
                "healPartyOnEnter" to n.healPartyOnEnter, "metricDeltas" to n.metricDeltas.ifEmpty { null },
                "metricOverrides" to n.metricOverrides.ifEmpty { null }, "assignHeroClassId" to n.assignHeroClassId,
                "allowIncidents" to (if (n.allowIncidents) null else false)
            ).filterValues { it != null }
        }))

        // ---- encounters: only those the kept story triggers ----------------------------------------
        val usedEncounterIds = keptNodes.mapNotNull { it.triggerBattleEncounterId }.toSet()
        val allEncounters = StoryEncounters.ALL_ENCOUNTERS
        val encounters = allEncounters.filter { it.id in usedEncounterIds }.distinctBy { it.id }
        val missingEncounters = usedEncounterIds - allEncounters.map { it.id }.toSet()
        report.append("\n## Encounters\n- ${allEncounters.size} defined, ${encounters.size} used by the story (kept)\n")
        if (missingEncounters.isNotEmpty()) report.append("- ⚠ story triggers encounters that don't exist: ${missingEncounters.joinToString()}\n")
        for (e in encounters) report.append("  - ${e.id}: party ${e.initialParty?.map { it.id }}, enemies ${e.enemies.map { it.id }}\n")
        val noParty = encounters.filter { it.initialParty == null }.map { it.id }
        if (noParty.isNotEmpty()) report.append("- encounters with no explicit party (use the campaign party): ${noParty.joinToString()}\n")

        // ---- party members -> characters (combat blocks); report stat conflicts -----------------------
        val members = encounters.flatMap { e -> (e.initialParty ?: emptyList()).map { e.id to it } }
        val byId = members.groupBy({ it.second.id }, { it })
        report.append("\n## Party members (${byId.size}: ${byId.keys.joinToString()})\n")
        val chosen = mutableMapOf<String, PartyMember>()
        for ((id, uses) in byId) {
            val variants = uses.map { it.second }.distinctBy { listOf(it.maxHp, it.maxMp, it.speed, it.defense, it.spells.map { s -> s.id }) }
            // The first appearance is the character's base; later acts' growth comes from engine progression.
            chosen[id] = uses.first().second
            if (variants.size > 1) {
                report.append("- **$id** appears with ${variants.size} different stat/spell sets:\n")
                for (v in variants) {
                    val where = uses.filter { (_, m) -> m.maxHp == v.maxHp && m.spells.map { it.id } == v.spells.map { it.id } }.map { it.first }
                    report.append("  - hp ${v.maxHp}, mp ${v.maxMp}, spd ${v.speed}, def ${v.defense}, spells ${v.spells.map { it.id }} — in ${where.joinToString()}\n")
                }
            }
        }

        // ---- spells ----------------------------------------------------------------------------------
        val presetChants = readJsonMap(File(migration, "preset_chants.json"))
        val allSpells = linkedMapOf<String, Spell>()
        fun addSpell(s: Spell) { if (s.id !in allSpells) allSpells[s.id] = s }
        chosen.values.forEach { m -> m.spells.forEach(::addSpell) }
        members.forEach { (_, m) -> m.spells.forEach(::addSpell) }
        HeroClass.COMMANDER.starterSpellIds.forEach { id -> spellByReflection(id)?.let(::addSpell) }
        listOf(TemplateHero.TREMENDOUS_STRIKE, TemplateHero.ART_OF_THE_DEAL, TemplateHero.OBAMA_DID_IT_FIRST, TemplateHero.PRESIDENTIAL_VETO,
            TemplateHero.EXECUTIVE_ACTION, TemplateHero.BUILD_THE_WALL, TemplateHero.RALLY_THE_PATRIOTS, TemplateHero.TIRED_OF_WINNING).forEach(::addSpell)
        StoryEncounters.hillRodAct1Spells.forEach(::addSpell)
        val leftoverSpells = allSpells.keys.filter { id -> echoesSpell(id) }
        if (leftoverSpells.isNotEmpty()) report.append("\n- Echoes spells found in Commander parties (kept, since a party uses them): ${leftoverSpells.joinToString()}\n")
        write("spells/spells.json", mapOf("spells" to allSpells.values.map { s -> spellJson(s, presetChants[s.id]) }))
        report.append("\n## Spells: ${allSpells.size}\n")

        // ---- enemies -> templates (+ per-encounter overrides) ------------------------------------------
        val templates = linkedMapOf<String, Enemy>()
        val enemyRefs = mutableMapOf<String, List<Map<String, Any?>>>()
        for (e in encounters) {
            enemyRefs[e.id] = e.enemies.map { en ->
                val templateId = en.id.replace(Regex("_\\d+$"), "")
                val base = templates.getOrPut(templateId) { decorate(en) }
                linkedMapOf<String, Any?>(
                    "templateId" to templateId, "id" to (if (en.id != templateId) en.id else null),
                    "name" to en.name.takeIf { it != base.name }, "subtitle" to en.subtitle.takeIf { it != base.subtitle },
                    "hp" to en.maxHp.takeIf { it != base.maxHp }, "attack" to en.baseAttack.takeIf { it != base.baseAttack },
                    "defense" to en.defense.takeIf { it != base.defense }, "speed" to en.speed.takeIf { it != base.speed },
                    "isTargeted" to (if (en.isTargeted) true else null), "atbGauge" to (if (en.atbGauge != 0f) en.atbGauge else null)
                ).filterValues { it != null }
            }
        }
        val usedMovesets = templates.values.map { it.movesetId }.filter { it.isNotBlank() }.toSet()
        write("enemies/enemies.json", mapOf("enemies" to templates.map { (tid, en) ->
            linkedMapOf<String, Any?>(
                "id" to tid, "name" to en.name, "subtitle" to en.subtitle, "family" to en.family, "selfElement" to en.selfElement,
                "hp" to en.maxHp, "attack" to en.baseAttack, "defense" to en.defense, "speed" to en.speed, "xpReward" to en.xpReward,
                "movesetId" to en.movesetId, "isBoss" to en.isBoss, "spriteId" to en.spriteId, "spriteTint" to hex(en.spriteTint),
                "soundBites" to enemySoundBites(tid, en).ifEmpty { null }
            ).filterValues { it != null }
        }))
        report.append("\n## Enemies: ${templates.size} templates; movesets used: ${usedMovesets.size}\n")
        val movesetMissing = usedMovesets.filter { MovesetTable.movesetFor(it) == null }
        if (movesetMissing.isNotEmpty()) report.append("- ⚠ movesets referenced but not defined: ${movesetMissing.joinToString()}\n")
        write("enemies/enemy_spells.json", mapOf("movesets" to usedMovesets.mapNotNull { mid ->
            MovesetTable.movesetFor(mid)?.let { ms ->
                mid to linkedMapOf<String, Any?>(
                    "moves" to ms.moves.map { mv -> moveJson(mv) },
                    "phases" to ms.phases.map { p -> mapOf("hpBelow" to p.hpBelow, "note" to p.note, "addMoves" to p.addMoves.map { moveJson(it) }) }.ifEmpty { null }
                ).filterValues { it != null }
            }
        }.toMap()))

        write("encounters/encounters.json", mapOf("encounters" to encounters.map { e ->
            linkedMapOf<String, Any?>(
                "id" to e.id, "name" to e.name, "description" to e.description, "environment" to e.environment.name,
                "environmentId" to e.environmentId.ifBlank { null },
                "party" to e.initialParty?.map { it.id },
                "enemies" to enemyRefs[e.id],
                "bossPhases" to e.bossPhases.map { bp ->
                    linkedMapOf<String, Any?>("enemyId" to bp.enemyId, "hpBelow" to bp.hpBelow, "bannerText" to bp.bannerText,
                        "narrationText" to bp.narrationText, "reducePartyToHpPct" to bp.reducePartyToHpPct,
                        "executeBelowHpPct" to bp.executeBelowHpPct, "summonEnemyId" to bp.summonEnemyId,
                        "summonCount" to bp.summonCount.takeIf { it > 0 }).filterValues { it != null }
                }.ifEmpty { null },
                "midBattleJoin" to e.midBattleJoin?.let { mapOf("characterId" to it.characterId, "bannerText" to it.bannerText,
                    "announcementText" to it.announcementText, "afterEnemyActions" to it.afterEnemyActions) }
            ).filterValues { it != null }
        }))

        // ---- characters: every speaker the story uses, plus party members ------------------------------
        val voiceIds = readJsonMap(File(migration, "preferred_voice_ids.json"))
        val speakers = keptNodes.map { it.speaker }.distinctBy { it.id }.associateBy { it.id }.toMutableMap()
        report.append("\n## Characters\n- story speakers: ${speakers.size}\n")
        val outOfTurn = mapOf(
            "hero" to "Hold on folks, that's {owner}'s move! Commander America has the tremendous spells ready right here!",
            "marco" to "That's {owner}'s jurisdiction! I'm Marco Rubio, and I have sanctions and tariffs standing by!",
            "vance" to "Hold your horses, that belongs to {owner}. I'm JD Vance, and we've got debate dismantling ready to go.",
            "kash" to "That's in {owner}'s file! I'm Kash Patel, let's declassify the truth or plug the leaks!",
            "kennedy" to "Now hold on, son! That's {owner}'s business. In plain English, I'm Senator Kennedy and we're here to cross-examine these folks!"
        )
        val charIds = (speakers.keys + chosen.keys + "narrator").filter { it !in echoesIds || it in chosen }.toSortedSet()
        for (id in charIds) {
            val sp: DialogueSpeaker? = (if (id == "narrator") DialogueSpeaker.NARRATOR.copy(id = "narrator") else null) ?: speakers[id] ?: speakerByReflection(id)
                ?: speakerOfMember[id]?.let { sid -> speakers[sid] ?: speakerByReflection(sid) }
            val pm = chosen[id]
            val c = linkedMapOf<String, Any?>(
                "id" to id,
                "role" to when { id == "hero" -> "hero"; id.startsWith("narrator") -> "narrator"; pm != null -> "companion"; else -> "npc" },
                "name" to (sp?.name ?: pm?.name ?: id), "title" to sp?.title, "gender" to sp?.gender,
                "portraitAsset" to sp?.portraitAsset, "themeColor" to sp?.let { hex(it.themeColor) },
                "ttsPitch" to sp?.ttsPitch, "samplePhrase" to sp?.samplePhrase,
                "preferredVoiceId" to (voiceIds[id] as? String ?: when (id) { "hero" -> voiceIds["commander_america"] as? String; "narrator" -> voiceIds[DialogueSpeaker.NARRATOR.id] as? String; else -> null }),
                "allowedVoiceRegions" to sp?.allowedVoiceRegions?.ifEmpty { null },
                "voiceAliases" to voiceAliasesFor(id, sp, pm).ifEmpty { null },
                "loreClass" to pm?.loreClass,
                "soundBites" to characterSoundBites(id).ifEmpty { null },
                "outOfTurnLine" to outOfTurn[id],
                "combat" to pm?.let { m ->
                    linkedMapOf<String, Any?>("maxHp" to m.maxHp, "maxMp" to m.maxMp, "speed" to m.speed, "defense" to m.defense,
                        "avatarTint" to hex(m.avatarTint), "startAtb" to m.atbGauge, "isGhost" to m.isGhost.takeIf { it },
                        "canAttack" to (if (!m.canAttack) false else null), "isUntargetable" to m.isUntargetable.takeIf { it },
                        "spriteAlpha" to m.spriteAlpha, "spellIds" to m.spells.map { it.id }).filterValues { it != null }
                }
            ).filterValues { it != null }
            write("characters/$id.json", c)
            if (sp == null) report.append("- ⚠ `$id` is a party member with no dialogue speaker (name/portrait need filling in)\n")
        }

        // ---- classes: the two perspectives' heroes --------------------------------------------------
        val commander = HeroClass.COMMANDER
        write("classes/commander.json", linkedMapOf("id" to commander.id, "title" to commander.title, "subtitle" to commander.subtitle,
            "description" to commander.description, "startingHp" to commander.startingHp, "startingMp" to commander.startingMp,
            "startingSpeed" to commander.startingSpeed, "startingBaseAttack" to commander.startingBaseAttack,
            "preferredSchool" to commander.preferredSchool.name, "starterSpellIds" to commander.starterSpellIds))
        report.append("\n## Classes\n- commander: ${commander.starterSpellIds} (both tracks, as before; Track B plays a relay of fixed heroes)\n")

        // ---- thesaurus words taken out of the engine --------------------------------------------------
        File(migration, "thesaurus_words.json").takeIf { it.exists() }?.let { f ->
            report.append("\n## Thesaurus: merge ${f.name} into spells/thesaurus.json (step done by script)\n")
        }

        File(out, "REPORT.md").writeText(report.toString())
        println(report)
    }

    // ---- helpers ------------------------------------------------------------------------------------

    private fun trackStartScene(startNode: String): String? = when (startNode) {
        "act1_intro" -> "scene_act1_ch1_escalator"
        "act1_tb_ch1_intro" -> "scene_act1_tb_ch1_server"
        else -> null
    }

    /** What Commander's CombatViewModel.decorateEnemy did at runtime, baked in. */
    private fun decorate(enemy: Enemy): Enemy {
        val codex = com.voicerpg.engine.combat.EnemyCodex.entryOf(enemy.name)
        val decorated = enemy.copy(
            family = codex?.family ?: enemy.family,
            selfElement = codex?.selfElement?.ifEmpty { null } ?: enemy.selfElement.ifEmpty { null } ?: "",
            defense = if (codex != null) maxOf(codex.defense, enemy.defense) else enemy.defense,
            xpReward = codex?.baseXp ?: enemy.xpReward,
            movesetId = codex?.moveset?.ifEmpty { null } ?: enemy.movesetId.ifEmpty { null } ?: ""
        )
        if (decorated.movesetId.isNotBlank() && MovesetTable.movesetFor(decorated.movesetId) != null) return decorated
        // CombatViewModel.defaultMovesetFor: family + role word from the subtitle.
        val role = when {
            enemy.subtitle.contains("Sniper", true) || enemy.subtitle.contains("Assassin", true) -> "sniper"
            enemy.subtitle.contains("Occultist", true) || enemy.subtitle.contains("Caster", true) ||
                enemy.subtitle.contains("Summoner", true) || enemy.subtitle.contains("Mystic", true) -> "caster"
            else -> "bruiser"
        }
        val key = "${decorated.family.lowercase()}_$role"
        return decorated.copy(movesetId = if (MovesetTable.movesetFor(key) != null) key else decorated.movesetId)
    }

    /** Party member id -> the dialogue speaker that voices them (ids differ for a few). */
    private val speakerOfMember = mapOf(
        "hero" to "commander_america", "joe_naptime" to "biden", "kamala_coconut" to "kamala", "nancy_palooza" to "pelosi"
    )

    private fun echoesSpell(id: String) = id in setOf("holy_smite", "lay_on_hands", "shield_wall", "aegis_dawn", "soothing_rain",
        "briar_entangle", "verdant_cataclysm", "shadow_strike", "venom_flurry", "umbral_siphon", "fireball", "frost_spike", "chain_lightning")

    private fun readJsonMap(f: File): Map<String, Any?> =
        if (!f.exists()) emptyMap() else gson.fromJson(f.readText(), Map::class.java).mapKeys { it.key.toString() }

    private fun graphicsJson(g: SpellGraphics?): Any? = g?.let { JsonParser.parseString(gson.toJson(it)) }

    private fun spellJson(s: Spell, preset: Any?): Map<String, Any?> = linkedMapOf<String, Any?>(
        "id" to s.id, "name" to s.name, "school" to s.school.name, "basePower" to s.basePower, "mpCost" to s.mpCost,
        "isHeal" to s.isHeal.takeIf { it }, "hitsAll" to s.hitsAll.takeIf { it }, "description" to s.description,
        "exampleChant" to s.exampleChant, "status" to s.status, "lifesteal" to s.lifesteal.takeIf { it }, "isGuard" to s.isGuard.takeIf { it },
        "manaRestorePct" to s.manaRestorePct.takeIf { it > 0f }, "aliases" to s.aliases.ifEmpty { null },
        "graphics" to graphicsJson(s.graphics), "cleansesDebuffs" to s.cleansesDebuffs.takeIf { it },
        "presetChants" to preset, "soundBites" to spellSoundBites(s.id).ifEmpty { null }
    ).filterValues { it != null }

    private fun moveJson(m: com.voicerpg.engine.combat.EnemyMove): Map<String, Any?> = linkedMapOf<String, Any?>(
        "name" to m.name, "school" to m.school, "kind" to m.kind, "powerMult" to m.powerMult, "targetRule" to m.targetRule,
        "applyStatus" to m.applyStatus, "cooldownTurns" to m.cooldownTurns.takeIf { it > 0 }, "weight" to m.weight,
        "summon" to m.summon, "summonCount" to m.summonCount.takeIf { it > 0 }, "graphics" to graphicsJson(m.graphics),
        "soundBites" to moveSoundBites(m.name).ifEmpty { null }
    ).filterValues { it != null }

    private fun speakerByReflection(id: String): DialogueSpeaker? =
        DialogueSpeaker.Companion::class.java.declaredMethods.filter { it.parameterCount == 0 && it.returnType == DialogueSpeaker::class.java }
            .mapNotNull { runCatching { it.invoke(DialogueSpeaker.Companion) as DialogueSpeaker }.getOrNull() }
            .firstOrNull { it.id == id }

    private fun spellByReflection(id: String): Spell? =
        TemplateHero::class.java.declaredMethods.filter { it.parameterCount == 0 && it.returnType == Spell::class.java }
            .mapNotNull { runCatching { it.invoke(TemplateHero) as Spell }.getOrNull() }
            .firstOrNull { it.id == id }

    private fun voiceAliasesFor(id: String, sp: DialogueSpeaker?, pm: PartyMember?): List<String> = when (id) {
        "hero" -> listOf("commander", "america", "trump", "chief")
        "marco" -> listOf("marco", "rubio", "little marco")
        "vance" -> listOf("vance", "jd", "j d")
        "kash" -> listOf("kash", "patel", "wizard kash")
        "kennedy" -> listOf("kennedy", "foghorn", "senator kennedy")
        else -> emptyList()
    }

    // Sound bites: the same rules Commander's CombatViewModel applied by matching names in code.
    private val bites = mapOf(
        "biden" to listOf("audio/quotes/biden/biden_truinanashabbadepressure.ogg", "audio/quotes/biden/biden_badakathcare.ogg"),
        "trump" to listOf("audio/quotes/trump/trump_greatness_birthright.ogg", "audio/quotes/trump/trump_easily_win.ogg"),
        "obama" to listOf("audio/quotes/obama/obama_dont_boo_vote.ogg"),
        "spicer" to listOf("audio/quotes/spicer/spicer_period.ogg"),
        "hillary" to listOf("audio/quotes/hillary/hillary_congratulated_trump.ogg"),
        "kamala" to listOf("audio/quotes/kamala/kamala_ride_a_bus.ogg"),
        "press" to listOf("audio/quotes/press/psaki_legislative_action.ogg", "audio/quotes/press/kayleigh_heroes.ogg")
    )
    private fun enemySoundBites(id: String, en: Enemy): List<String> = when {
        id.contains("joe") || id.contains("biden") -> bites.getValue("biden")
        id.contains("trump") || id.contains("commander") -> bites.getValue("trump")
        id.contains("obama") || id.contains("barry") -> bites.getValue("obama")
        id.contains("spicer") || id.contains("spicy") -> bites.getValue("spicer")
        id.contains("hill") -> bites.getValue("hillary")
        id.contains("kamala") || id.contains("vice") || id.contains("coconut") -> bites.getValue("kamala")
        id.contains("psaki") || id.contains("kayleigh") || id.contains("press") -> bites.getValue("press")
        else -> emptyList()
    }
    private fun moveSoundBites(name: String): List<String> = when {
        name.contains("Gibberish") || name.contains("Truinana") || name.contains("Badakathcare") -> bites.getValue("biden")
        name.contains("Fight") || name.contains("Birthright") -> bites.getValue("trump")
        name.contains("Boo") || name.contains("Vote") -> bites.getValue("obama")
        name.contains("Period") || name.contains("Crowd") -> bites.getValue("spicer")
        name.contains("Concession") || name.contains("Cloth") -> bites.getValue("hillary")
        name.contains("Bus") || name.contains("Venn") -> bites.getValue("kamala")
        else -> emptyList()
    }
    private fun characterSoundBites(id: String): List<String> = when {
        id.contains("trump") || id.contains("commander") || id == "hero" -> bites.getValue("trump")
        id.contains("obama") || id.contains("barry") -> bites.getValue("obama")
        id.contains("spicer") || id.contains("spicy") -> bites.getValue("spicer")
        id.contains("hill") -> bites.getValue("hillary")
        id.contains("kamala") || id.contains("vice") || id.contains("coconut") -> bites.getValue("kamala")
        else -> emptyList()
    }
    private fun spellSoundBites(id: String): List<String> = when {
        id == "biden_gibberish_audio" || id.contains("gibberish") -> bites.getValue("biden")
        id.contains("trump") || id.contains("commander") -> bites.getValue("trump")
        id.contains("obama") -> bites.getValue("obama")
        id.contains("spicer") -> bites.getValue("spicer")
        id.contains("hill") -> bites.getValue("hillary")
        id.contains("kamala") -> bites.getValue("kamala")
        else -> emptyList()
    }

    @Suppress("unused") private val unused = listOf(JsonObject(), JsonArray(), EncounterDefinition::class)
}
