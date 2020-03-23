package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MovieFinder {

    private static final Logger log = LoggerFactory.getLogger(MovieFinder.class);

    public MovieInfo findCharacterFromMovie(String characterName) {
        log.info("Finding movie for character name: {}", characterName);
        final MovieInfo movieInfo = new MovieInfo();
        movieInfo.setCharacterName(characterName);
        movieInfo.setMovieName("The Princess Pride");
        movieInfo.setYear("1987");
        movieInfo.setUrl("https://www.imdb.com/title/tt0093779/");
        return movieInfo;
    }

}
