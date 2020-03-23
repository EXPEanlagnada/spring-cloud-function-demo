package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.cloud.function.context.FunctionalSpringApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Function;

import static java.util.Optional.ofNullable;

@SpringBootConfiguration
public class Application implements Function<FindMovieRequest, MovieInfo> {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        FunctionalSpringApplication.run(Application.class, args);
    }

//    @Bean
//    public Function<FindMovieRequest, MovieInfo> findMovie(MovieFinder movieFinder) {
//        return request -> {
//            log.debug("FindMovieRequest: {}", ofNullable(request).map(Object::toString)
//                    .orElse(""));
//            return movieFinder.findCharacterFromMovie(request.getCharacterName());
//        };
//    }

    @Bean
    MovieFinder movieFinder() {
        return new MovieFinder();
    }

    @Override
    public MovieInfo apply(FindMovieRequest request) {
        log.debug("FindMovieRequest: {}", ofNullable(request).map(Object::toString)
                .orElse(""));
        return movieFinder().findCharacterFromMovie(request.getCharacterName());
    }
}