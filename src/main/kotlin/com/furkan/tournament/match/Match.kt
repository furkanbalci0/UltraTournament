package com.furkan.tournament.match

import com.furkan.tournament.round.Round
import com.furkan.tournament.team.Team
import com.furkan.tournament.tour.Tour

class Match(val tour: Tour, val team1: Team, val team2: Team) {

    val rounds: MutableList<Round> = mutableListOf()
    var currentRound: Round? = null

    var winner: Team? = null
    var startingTime: Long = 0

    fun getWins(team: Team): Int {
        return rounds.filter { round: Round -> team == round.winner }.size
    }

}