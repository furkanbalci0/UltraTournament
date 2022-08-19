package com.furkan.tournament.utils

import com.furkan.tournament.UltraTournamentPlugin
import org.bukkit.Bukkit
import org.bukkit.Location

object LocationHandler {

    fun serialize(location: Location): String {
        return location.world.name + ";" + location.x + ";" + location.y + ";" + location.z + ";" + location.yaw + ";" + location.pitch
    }

    fun deserialize(stringLocation: String): Location {
        val splitLoc: List<String> = stringLocation.split(";")
        var location: Location
        try {
            location = Location(
                Bukkit.getWorld(splitLoc[0]),
                splitLoc[1].toDouble(),
                splitLoc[2].toDouble(),
                splitLoc[3].toDouble()
            )
            location.yaw = splitLoc[4].toFloat()
            location.pitch = splitLoc[5].toFloat()
        } catch (e: Exception) {
            UltraTournamentPlugin.instance.logger.warning("Wrong location, please delete: $stringLocation")
            location = Location(Bukkit.getWorlds()[0], 0.0, 0.0, 0.0)
        }

        return location
    }
}