package com.furkan.tournament.tour

import com.furkan.tournament.enums.TourName
import com.furkan.tournament.manager.yaml.Language
import com.furkan.tournament.match.MatchHandler

object TourHandler {

    fun start(tour: Tour) {

        val builder = StringBuilder()

        for (line in Language.get(Language.TOUR_INFO_MESSAGE) as Array<*>) {

            val content = line.toString()
            if (content contentEquals "{tour-name}") {
                when (tour.tourName) {
                    TourName.QUARTER_FINAL -> {
                        builder.append(Language.get(Language.TITLE_QUARTER_FINAL))
                    }
                    TourName.SEMIFINAL -> {
                        builder.append(Language.get(Language.TITLE_SEMI_FINAL))
                    }
                    else -> {
                        builder.append(Language.get(Language.TITLE_FINAL))
                    }
                }
            } else if (content contentEquals "{encounters}") {
                for (match in tour.matches) {
                    builder.append("§e" + tour.teams[0].name + " §7- §b" + tour.teams[1].name).append("\n")
                }
            } else {
                builder.append(content)
            }
        }

        for (player in tour.tournament.players) {
            player.sendMessage(builder.toString())
        }

        //Starting first match.
        MatchHandler.start(tour.matches[0])

    }
}