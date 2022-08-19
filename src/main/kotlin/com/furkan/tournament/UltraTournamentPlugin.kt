package com.furkan.tournament

import com.furkan.tournament.manager.YamlManager
import org.bukkit.plugin.java.JavaPlugin

class UltraTournamentPlugin : JavaPlugin() {

    lateinit var yamlManager: YamlManager

    companion object {
        lateinit var instance: UltraTournamentPlugin
    }

    override fun onEnable() {
        super.onEnable()
        instance = this
        yamlManager = YamlManager(this)
    }
}
