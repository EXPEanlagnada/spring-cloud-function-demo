package com.kapresoft.demo.service;

import com.kapresoft.demo.pojo.dto.MovieInfoResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MovieFinder {

    public MovieInfoResponse findCharacterFromMovie(String characterName) {
        log.debug("Finding movie for character name: {}", characterName);
        if (characterName.equalsIgnoreCase("Inigo Montoya")) {
            return MovieInfoResponse.builder()
                    .characterName(characterName)
                    .movieName("The Princess Pride")
                    .year("1987")
                    .url("https://www.imdb.com/title/tt0093779/")
                    .build();
        }
        return MovieInfoResponse.builder().build();
    }

}