package com.furkan.tournament.manager.yaml

import com.furkan.tournament.UltraTournamentPlugin
import com.hakan.core.HCore
import com.hakan.core.configuration.ConfigType
import com.hakan.core.configuration.annotations.ConfigFile
import com.hakan.core.configuration.annotations.ConfigValue

@ConfigFile(
    plugin = UltraTournamentPlugin::class,
    type = ConfigType.YAML,
    resource = "settings.yml",
    path = "plugins/UltraTournament/settings.yml",
)
object SettingsConfig {

    init {
        HCore.loadConfig(this)
    }

    @ConfigValue(path = "locations.team-spawn-1")
    val TEAM_SPAWN_1 = "world;0;0;0;0;0"

    @ConfigValue(path = "locations.team-spawn-2")
    val TEAM_SPAWN_2 = "world;0;0;0;0;0"
}