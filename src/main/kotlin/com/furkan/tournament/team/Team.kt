package com.furkan.tournament.team

import com.furkan.tournament.player.TournamentPlayer

class Team(val name: String, owner: TournamentPlayer) {

    val players: MutableList<TournamentPlayer> = mutableListOf()
    var isEliminated: Boolean = false

    init {
        this.players.add(owner)
    }

}