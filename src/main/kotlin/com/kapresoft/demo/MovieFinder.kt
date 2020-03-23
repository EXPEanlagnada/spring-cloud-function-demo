package com.kapresoft.demo

import org.slf4j.LoggerFactory

class MovieFinder {

    fun findCharacterFromMovie(characterName: String?): MovieInfo {
        log.debug("Finding movie for character name: {}", characterName)
        val movieInfo = MovieInfo()
        movieInfo.characterName = characterName
        movieInfo.movieName = "The Princess Pride"
        movieInfo.year = "1987"
        movieInfo.url = "https://www.imdb.com/title/tt0093779/"
        return movieInfo
    }

    companion object {
        private val log = LoggerFactory.getLogger(MovieFinder::class.java)
    }
}