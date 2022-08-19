package com.furkan.tournament.manager.yaml

import com.furkan.tournament.UltraTournamentPlugin
import javax.persistence.Entity

enum class Language(private val path: String, private val default: Any) {

    TITLE_QUARTER_FINAL("tour.tour-names.quarter-final", "&a&lQUARTER FINAL TOUR"),
    TITLE_SEMI_FINAL("tour.tour-names.semi-final", "&e&lSEMI FINAL TOUR"),
    TITLE_FINAL("tour.tour-names.final", "&c&lFINAL TOUR"),
    TELEPORT("tournament.teleport-message", "&aTeleporting..."),
    TOURNAMENT_INFO_MESSAGE(
        "tournament.info-messages",
        listOf("{tournament-name}", "{min-team-amount}", "{players-per-team}", "{best-of-win}")
    ),
    TOUR_INFO_MESSAGE("tour.info-messages", listOf("{tour-name}", "{encounters}")),
    MATCH_INFO_MESSAGE("match.info-messages", listOf("{team-1}", "{team-2}")),
    ROUND_INFO_MESSAGE(
        "round.info-messages", listOf("{current-round}", "{team-1}", "{team-2}")
    ),
    ROUND_END_MESSAGE(
        "round.end-messages",
        listOf("{winner-team}", "{team-1}", "{team-2}", "{team-1-wins}", "{team-2-wins}", "{passing-time}")
    ),

    NOT_ENOUGH_TEAMS("tournament.not-enough-teams", "&cThere are not enough teams for the tournament to start."),
    OFFLINE_TEAM("tournament.offline-team", "&aThe opposing team was disqualified for inactivity.");

    companion object {
        private val langYaml = UltraTournamentPlugin.instance.yamlManager.language

        fun get(langValue: Language): String {
            //Control value.
            val value = langYaml.get(langValue.path)
            //If value deleted in language.yml, set again.
            if (value == null) {
                langYaml.set(langValue.path, langValue.default)
            }
            //Return value.
            return value.toString().replace("&", "§")

        }
    }


}