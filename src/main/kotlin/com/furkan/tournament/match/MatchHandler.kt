package com.furkan.tournament.match

import com.furkan.tournament.manager.yaml.Language

object MatchHandler {

    fun start(match: Match) {

        match.startingTime = System.currentTimeMillis() / 1000
        val builder = StringBuilder()
        for (line in listOf(Language.get(Language.MATCH_INFO_MESSAGE))) {
            var content = line
            content = content.replace("{team-1}", match.team1.name)
            content = content.replace("{team-2}", match.team2.name)
            builder.append(content)
        }

        //todo: Herkese mesaj gönder.


    }

    fun stop(match: Match) {

    }
}