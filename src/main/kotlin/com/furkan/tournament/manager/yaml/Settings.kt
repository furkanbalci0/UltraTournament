package com.furkan.tournament.manager.yaml

import com.furkan.tournament.UltraTournamentPlugin

enum class Settings(private val path: String, private val default: Any) {

    TEAM_1_SPAWN_LOCATION("locations.team-1-spawn", "world;0;0;0;0;0"),
    TEAM_2_SPAWN_LOCATION("locations.team-2-spawn", "world;0;0;0;0;0"),
    SPECTATE_SPAWN_LOCATION("locations.spectate-spawn", "world;0;0;0;0;0"),
    MATCH_COUNT_TO_WIN("tournament.count-to-win", 4),
    MAX_ROUND_TIME("tournament.max-round-time", 240),
    FIRST_STARTING_COOLDOWN("tournament.first-tour-starting-cooldown", 15);

    companion object {
        private val settingsYaml = UltraTournamentPlugin.instance.yamlManager.settings

        fun get(settingValue: Settings): Any {

            //Control value.
            val value = settingsYaml.get(settingValue.path)
            //If value deleted in language.yml, set again.
            if (value == null) {
                settingsYaml.set(settingValue.path, settingValue.default)
            }
            //Return value.
            return value

        }
    }


}