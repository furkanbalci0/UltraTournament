package com.furkan.tournament.command

import com.hakan.core.HCore
import org.bukkit.entity.Player

object CommandHandler {

    //Register commands.
    init {
        HCore.registerCommands(TournamentCommand())
    }

    /**
     * Send help messages to player.
     * @param player Player
     */
    fun sendHelpMessages(player: Player) {

        var message = "\n"
        message += "\n"
        message += "    §6§lTURNUVADA TAKIM OLUŞTURMA KOMUTLARI\n"
        message += "    §7Turnuvaya katılmak için bir takım oluşturmalısınız.\n"
        message += "\n"
        message += "    §e§l* §f/takım oluştur <isim> §8» §7Takım kurar.\n"
        message += "    §e§l* §f/takım davet <oyuncu> §8» §7Takım daveti gönderir.\n"
        message += "    §e§l* §f/takım kabul <takım> §8» §7Takım davetini kabul eder.\n"
        message += "    §e§l* §f/takım reddet <takım> §8» §7Takım davetini reddeder.\n"
        message += "    §e§l* §f/takım sohbet §8» §7Takım sohbetine katılır veya ayrılır.\n"
        message += "    §e§l* §f/takım at <oyuncu> §8» §7Oyuncuyu takımdan atar.\n"
        message += "    §e§l* §e/takım bilgi <takım> §8» §7Takımın bilgilerini gösterir.\n"
        message += "    §e§l* §c/takım dağıt §8» §7Takımı dağıtır.\n"
        message += "    §e§l* §c/takım ayrıl §8» §7Takımdan ayrılır.\n"
        message += "\n"
        player.sendMessage(message)

    }
}