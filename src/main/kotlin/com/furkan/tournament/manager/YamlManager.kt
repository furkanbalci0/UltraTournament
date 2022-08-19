package com.furkan.tournament.manager

import com.furkan.tournament.UltraTournamentPlugin
import com.hakan.core.utils.yaml.HYaml

open class YamlManager(plugin: UltraTournamentPlugin) {

    val settings: HYaml = HYaml.create(plugin, "settings.yml", "settings.yml")
    val language: HYaml = HYaml.create(plugin, "language.yml", "language.yml")
    val config: HYaml = HYaml.create(plugin, "config.yml", "config.yml")


}