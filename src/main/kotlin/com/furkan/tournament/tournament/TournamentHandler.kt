package com.furkan.tournament.tournament

import com.furkan.tournament.UltraTournamentPlugin
import com.furkan.tournament.enums.TourName
import com.furkan.tournament.enums.TournamentStatus
import com.furkan.tournament.manager.yaml.Language
import com.furkan.tournament.manager.yaml.Settings
import com.furkan.tournament.tour.Tour
import com.furkan.tournament.tour.TourHandler
import org.bukkit.Bukkit

object TournamentHandler {

    val tournaments = mutableListOf<Tournament>()

    fun start(tournament: Tournament) {

        //If not enough teams.
        if (tournament.teams.size < tournament.minTeams) {
            for (player in tournament.players) {
                player.sendMessage(Language.get(Language.NOT_ENOUGH_TEAMS))
            }
            return
        }

        //Change tournament status because to decline new join requests.
        tournament.status = TournamentStatus.PLAYING

        //Send info messages to tournament players.
        for (player in tournament.players) {
            player.sendMessage(Language.get(Language.TOURNAMENT_INFO_MESSAGE))
        }

        Bukkit.getScheduler().runTaskLater(UltraTournamentPlugin.instance, {
            //Shuffle team list.
            val teams = tournament.teams.shuffled()

            //Create new tour.
            val tour = Tour(tournament, teams, TourName.QUARTER_FINAL)
            TourHandler.start(tour)

            tournament.tours[TourName.QUARTER_FINAL] = tour
        }, Settings.get(Settings.FIRST_STARTING_COOLDOWN) as Long)

    }
}