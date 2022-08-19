package com.furkan.tournament.utils

import com.furkan.tournament.manager.yaml.Language
import org.bukkit.GameMode
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

object EquipUtil {

    fun equip(players: List<Player>, items: List<ItemStack>) {

        for (item in items) {
            val type = item.type
            val name = type.name

            if (name.contains("_CHESTPLATE")) {
                for (player in players) player.inventory.chestplate = item
            } else if (name.contains("_LEGGINGS")) {
                for (player in players) player.inventory.leggings = item
            } else if (name.contains("_HELMET")) {
                for (player in players) player.inventory.helmet = item
            } else if (name.contains("_BOOTS")) {
                for (player in players) player.inventory.boots = item
            } else if (name.contains("_SWORD")) {
                for (player in players) player.inventory.setItem(0, item)
            } else {
                for (player in players) player.inventory.addItem(item)
            }
        }

        for (player in players) {

            //Set gamemode
            player.gameMode = GameMode.SURVIVAL

            //Remove potion effects
            player.activePotionEffects.stream().map { obj: PotionEffect -> obj.type }
                .forEach { type: PotionEffectType? ->
                    player.removePotionEffect(type)
                }

            player.health = player.maxHealth
            player.sendMessage(Language.get(Language.TELEPORT))


        }
    }
}
