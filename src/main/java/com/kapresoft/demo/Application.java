package com.kapresoft.demo;

import lombok.extern.slf4j.Slf4j;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kapresoft.demo.fn.CustomRouterFunction;
import com.kapresoft.demo.fn.FindMovieRequestFunction;
import com.kapresoft.demo.fn.FindSongRequestFunction;
import com.kapresoft.demo.pojo.dto.FindMovieRequest;
import com.kapresoft.demo.pojo.dto.FindSongRequest;
import com.kapresoft.demo.pojo.dto.MovieInfoResponse;
import com.kapresoft.demo.pojo.dto.SongInfoResponse;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.function.context.FunctionCatalog;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;

import java.util.Locale;
import java.util.function.Function;

/**
 * Need to define lambda env <b>FUNCTION_NAME</b> as either "findMovie" or "findSong"
 */
@Slf4j
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    Function<FindMovieRequest, MovieInfoResponse> findMovie() {
        return new FindMovieRequestFunction();
    }

    @Bean
    Function<FindSongRequest, SongInfoResponse> findSong() {
        return new FindSongRequestFunction();
    }

    @Bean
    Function<String, String> uppercase() {
        return value -> {
            log.info("Upper-casing: {}", value);
            return value.toUpperCase(Locale.getDefault());
        };
    }

    @Bean
    Function<String, String> reverse() {
        return value -> {
            log.info("Reversing: {}", value);
            return new StringBuilder(value).reverse().toString();
        };
    }

    @Bean
    Function<Message<Object>, Object> customRouter(@Qualifier("requestMessageObjectMapper") ObjectMapper objectMapper, FunctionCatalog functionCatalog) {
        return new CustomRouterFunction(objectMapper, functionCatalog);
    }

}