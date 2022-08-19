package com.furkan.tournament.tournament

import com.furkan.tournament.enums.TourName
import com.furkan.tournament.enums.TournamentStatus
import com.furkan.tournament.match.Match
import com.furkan.tournament.team.Team
import com.furkan.tournament.tour.Tour
import com.google.common.collect.ImmutableList
import org.bukkit.entity.Player

class Tournament(
    val name: String,
    var minTeams: Int = 8,
    var playersPerTeam: Int = 3,
    var bestOfWin: Int = 4,
) {

    val players = mutableListOf<Player>()
    val tours = mutableMapOf<TourName, Tour>()
    val teams = mutableListOf<Team>()

    var currentMatch: Match? = null
    var winnerTeam: Team? = null

    var status: TournamentStatus = TournamentStatus.CLOSED
}