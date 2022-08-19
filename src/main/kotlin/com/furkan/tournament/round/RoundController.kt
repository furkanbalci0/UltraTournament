package com.furkan.tournament.round

import com.furkan.tournament.UltraTournamentPlugin
import com.furkan.tournament.enums.RoundStatus
import com.furkan.tournament.enums.RoundStopReason
import com.hakan.core.HCore
import com.hakan.core.scheduler.HScheduler
import org.bukkit.Bukkit
import org.bukkit.scheduler.BukkitScheduler
import org.bukkit.scheduler.BukkitTask
import java.util.concurrent.TimeUnit
import java.util.function.Consumer
import kotlin.random.Random


class RoundController(private val round: Round) {

    var scheduler: BukkitTask = Bukkit.getScheduler().runTaskTimer(UltraTournamentPlugin.instance, {

        if (round.status == RoundStatus.ENDING) {
            return@runTaskTimer
        }

        //team1 online players
        val team1OnlinePlayers = round.getAllPlayers().filter { it.team == round.match.team1 && it.player.isOnline }
        val team1LivingPlayers = team1OnlinePlayers.filter { it.isAlive }

        val team2OnlinePlayers = round.getAllPlayers().filter { it.team == round.match.team2 && it.player.isOnline }
        val team2LivingPlayers = team2OnlinePlayers.filter { it.isAlive }

        if (team1OnlinePlayers.isEmpty() || team2OnlinePlayers.isEmpty()) {
            round.winner = if (team1LivingPlayers.isEmpty()) round.match.team2 else round.match.team1
            RoundHandler.stop(round, RoundStopReason.PLAYERS_OFFLINE)
        } else if (team1LivingPlayers.isEmpty() || team2LivingPlayers.isEmpty()) {
            RoundHandler.stop(round, RoundStopReason.DEATH_PLAYERS)
            round.winner = if (team1LivingPlayers.isEmpty()) round.match.team2 else round.match.team1
        } else if (round.time <= 0) {
            RoundHandler.stop(round, RoundStopReason.TIME_IS_OVER)
            round.winner = if (Random.nextInt() % 2 == 0) round.match.team1 else round.match.team2
        }


    }, 0L, 20L)



}