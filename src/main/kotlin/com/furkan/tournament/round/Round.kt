package com.furkan.tournament.round

import com.furkan.tournament.enums.RoundStatus
import com.furkan.tournament.match.Match
import com.furkan.tournament.player.TournamentPlayer
import com.furkan.tournament.team.Team
import com.google.common.collect.ImmutableList
import kotlin.math.floor

class Round(val match: Match) {

    var time: Int = 245
    var status: RoundStatus = RoundStatus.WAITING
    var winner: Team? = null
    var roundController: RoundController? = null

    fun getRoundTime(): String {
        if (time < 60) {
            val seconds: Int = Math.floorMod(time, 60)
            return if (seconds < 10) "00:0$seconds" else "00:$seconds"
        } else if (time <= 3599) {
            val minutes = floor(time / 60.0).toInt()
            val seconds = Math.floorMod(time, 60)
            return if (minutes < 10) if (seconds < 10) "0$minutes:0$seconds" else "0$minutes:$seconds" else if (seconds < 10) "$minutes:0$seconds" else "$minutes:$seconds"
        }
        return "00:00"
    }

    fun getAllPlayers(): List<TournamentPlayer> {
        return match.team1.players.plus(match.team2.players)
    }

}