package com.furkan.tournament.utils

import com.furkan.tournament.UltraTournamentPlugin
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import java.util.logging.Level

object ItemUtil {

    fun deserialize(itemText: String): ItemStack {

        var itemStack: ItemStack?
        try {
            val args = itemText.split(" ")
            val itemNo = args[0]

            itemStack = if (itemNo.contains(":")) {
                val item = itemNo.split(":")
                val material = Material.valueOf(item[0])
                ItemStack(material, 1, item[1].toShort())
            } else {
                val material = Material.valueOf(itemNo)
                ItemStack(material, args[1].toInt())
            }

            if (args.size >= 2) {
                itemStack.amount = args[1].toInt()
            }

            for (arg: String in args) {
                if (arg contentEquals args[0]) {
                    continue
                }

                if (arg.contains("name:")) {
                    val name: String = arg.split(":")[1].replace("&", "§").replace("_", "")
                    val meta = itemStack.itemMeta
                    meta.displayName = name
                    itemStack.itemMeta = meta
                } else if (arg.contains("lore:")) {
                    val lore: String = arg.split(":")[1].replace("&", "§").replace("_", "")
                    val meta: ItemMeta = itemStack.itemMeta
                    meta.lore = lore.split("\\|\\|")
                    itemStack.itemMeta = meta
                } else if (arg.contains(":")) {
                    val ench = arg.split(":")
                    val name = Enchantment.getByName(ench[0])
                    val level = ench[1].toInt()
                    itemStack.addUnsafeEnchantment(name, level)

                }
            }
        } catch (e: Exception) {
            UltraTournamentPlugin.instance.logger.log(Level.WARNING, "Wrong item syntax: $itemText")
            itemStack = ItemStack(Material.STONE)
        }
        return itemStack!!

    }
}