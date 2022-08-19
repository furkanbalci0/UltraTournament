package com.furkan.tournament.round

import com.furkan.tournament.enums.RoundStatus
import com.furkan.tournament.enums.RoundStopReason
import com.furkan.tournament.manager.yaml.Language
import com.furkan.tournament.manager.yaml.Settings
import com.furkan.tournament.manager.yaml.SettingsConfig
import com.furkan.tournament.match.MatchHandler
import com.furkan.tournament.team.Team
import com.furkan.tournament.utils.LocationHandler
import org.bukkit.Location
import org.bukkit.entity.Player
import java.util.*

object RoundHandler {

    fun start(round: Round) {

        round.status = RoundStatus.STARTING

        val team1Location = LocationHandler.deserialize(Settings.get(Settings.TEAM_1_SPAWN_LOCATION).toString())
        val team2Location = LocationHandler.deserialize(Settings.get(Settings.TEAM_2_SPAWN_LOCATION).toString())

        val builder = StringBuilder()
        for (line in listOf(Language.get(Language.ROUND_INFO_MESSAGE))) {
            val currentRound = round.match.rounds.filter { it.status == RoundStatus.ENDING }.size + 1
            builder.append(
                line.replace("{current-round}", currentRound.toString())
                    .replace("{team-1}", round.match.team1.name)
                    .replace("{team-2}", round.match.team2.name)
            )
        }

        for (team: Team in listOf(round.match.team1, round.match.team2)) {
            val selectedLocation: Location = if (round.match.team1 == team) team1Location else team2Location

            for (tournamentPlayer in team.players) {
                if (!tournamentPlayer.player.isOnline) continue
                val player = tournamentPlayer.player as Player
                player.teleport(selectedLocation)
                player.sendMessage(builder.toString())
            }

            //TODO: Oyunculara eşya giydir ve ışınla.

        }

        round.match.currentRound = round
        round.roundController = RoundController(round)


    }

    fun stop(round: Round, stopReason: RoundStopReason) {

        //Cancel scheduler and change status.
        round.roundController!!.scheduler.cancel()
        round.status = RoundStatus.ENDING

        val match = round.match

        //Best of win amount.
        val bestOfWin = Settings.get(Settings.MATCH_COUNT_TO_WIN) as Int

        //Wins
        val team1Win = match.getWins(match.team1)
        val team2Win = match.getWins(match.team2)

        //If stop reason is all players offline
        if (stopReason == RoundStopReason.PLAYERS_OFFLINE) {
            MatchHandler.stop(match)
            match.winner = round.winner

            match.tour.tournament.players.forEach {
                if (it.isOnline) {
                    it.sendMessage(Language.get(Language.OFFLINE_TEAM))
                }
            }
        } else if (team1Win > bestOfWin || team2Win > bestOfWin) {
            match.winner = if (team1Win > bestOfWin) match.team1 else match.team2
            MatchHandler.stop(match)
        }

        val builder = StringBuilder()
        for (line in listOf(Language.get(Language.ROUND_END_MESSAGE))) {
            builder.append(
                line.replace("{winner-team}", round.winner!!.name)
                    .replace("{team-1}", round.match.team1.name)
                    .replace("{team-2}", round.match.team2.name)
                    .replace("{team-1-wins}", team1Win.toString())
                    .replace("{team-2-wins}", team2Win.toString())
                    .replace("{passing-time}", (Settings.get(Settings.MAX_ROUND_TIME) as Int - round.time).toString())
            )
        }

        for (player in match.tour.tournament.players) {
            player.sendMessage(builder.toString())
        }
    }
}