package com.kapresoft.demo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.cloud.function.context.FunctionalSpringApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Function;

@SpringBootConfiguration
public class Application implements Function<FindMovieRequest, MovieInfo> {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        FunctionalSpringApplication.run(Application.class, args);
    }

    @Override
    public MovieInfo apply(FindMovieRequest request) {
        log.debug("\nFindMovieRequest: {}", toJsonString(request));
        final MovieInfo movieInfo = movieFinder().findCharacterFromMovie(request.getCharacterName());
        log.debug("\nMovie found: {}", toJsonString(movieInfo));
        return movieInfo;
    }

    @Bean
    ObjectMapper objectMapper() {
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return objectMapper;
    }

    @Bean
    MovieFinder movieFinder() {
        return new MovieFinder();
    }

    private String toJsonString(Object o) {
        try {
            return objectMapper().writeValueAsString(o);
        } catch (JsonProcessingException e) {
            log.info("Failed to write object to json: {}", e.getMessage(), e);
        }
        return null;
    }
}