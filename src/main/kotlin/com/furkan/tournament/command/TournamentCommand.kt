package com.furkan.tournament.command

import com.hakan.core.command.executors.base.BaseCommand
import com.hakan.core.command.executors.sub.SubCommand
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

@BaseCommand(name = "uteam", aliases = ["team"])
class TournamentCommand {

    @SubCommand
    private fun run(sender: CommandSender, args: Array<String>) {

        //If args is empty, send help message
        if (args.isEmpty()) {
            CommandHandler.sendHelpMessages(sender as Player)
            return
        }




    }
}