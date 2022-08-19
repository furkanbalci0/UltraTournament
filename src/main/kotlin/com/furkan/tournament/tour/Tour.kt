package com.furkan.tournament.tour

import com.furkan.tournament.enums.TourName
import com.furkan.tournament.match.Match
import com.furkan.tournament.team.Team
import com.furkan.tournament.tournament.Tournament

class Tour(val tournament: Tournament, val teams: List<Team>, val tourName: TourName) {

    val matches = mutableListOf<Match>()
    val completed = false

    init {
        for (i in 0 until this.teams.size / 2) {
            val match = Match(this, this.teams[i], this.teams[i + this.teams.size / 2])
            matches.add(match)
        }
    }

}