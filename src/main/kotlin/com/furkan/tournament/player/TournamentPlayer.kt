package com.furkan.tournament.player

import com.furkan.tournament.team.Team
import org.bukkit.OfflinePlayer

data class TournamentPlayer(val player: OfflinePlayer, val team: Team) {
    var kills = 0
    var deaths = 0
    var isAlive = true
    var teamChat = false
}