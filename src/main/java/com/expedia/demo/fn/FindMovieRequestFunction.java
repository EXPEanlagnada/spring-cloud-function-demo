package com.expedia.demo.fn;

import lombok.extern.slf4j.Slf4j;
import lombok.val;

import com.expedia.demo.pojo.dto.FindMovieRequest;
import com.expedia.demo.pojo.dto.MovieInfoResponse;
import com.expedia.demo.service.RequestUtil;

import java.util.function.Function;

@Slf4j
public class FindMovieRequestFunction implements Function<FindMovieRequest, MovieInfoResponse> {

    public static final String THE_PRINCESS_PRIDE_MOVIE = "The Princess Pride";
    public static final String INIGO_MONTOYA_CHARACTER = "Inigo Montoya";

    private final RequestUtil requestUtil;

    public FindMovieRequestFunction() {
        this(new RequestUtil());
    }

    public FindMovieRequestFunction(RequestUtil requestUtil) {
        this.requestUtil = requestUtil;
    }

    public MovieInfoResponse apply(FindMovieRequest request) {
        log.debug("\nFindMovieRequest: {}", requestUtil.toJsonString(request));
        val movieInfo = findCharacterFromMovie(request.getCharacterName());
        log.debug("\nMovie found: {}", requestUtil.toJsonString(movieInfo));
        return movieInfo;
    }

    private MovieInfoResponse findCharacterFromMovie(String characterName) {
        log.debug("Finding movie for character name: {}", characterName);
        if (characterName.equalsIgnoreCase(INIGO_MONTOYA_CHARACTER)) {
            return MovieInfoResponse.builder()
                    .characterName(characterName)
                    .movieName(THE_PRINCESS_PRIDE_MOVIE)
                    .year("1987")
                    .url("https://www.imdb.com/title/tt0093779/")
                    .build();
        }
        return MovieInfoResponse.builder().build();
    }

}
