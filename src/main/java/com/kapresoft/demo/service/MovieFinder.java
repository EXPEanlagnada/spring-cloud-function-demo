package com.kapresoft.demo.service;

import com.kapresoft.demo.pojo.dto.MovieInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MovieFinder {

    public MovieInfo findCharacterFromMovie(String characterName) {
        log.debug("Finding movie for character name: {}", characterName);
        return MovieInfo.builder()
                .characterName("The Princess Pride")
                .year("1987")
                .url("https://www.imdb.com/title/tt0093779/")
                .build();
    }

}