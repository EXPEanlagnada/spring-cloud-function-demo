package com.kapresoft.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kapresoft.demo.fn.MovieRequestFunction;
import com.kapresoft.demo.fn.SongRequestFunction;
import com.kapresoft.demo.pojo.dto.FindMovieRequest;
import com.kapresoft.demo.pojo.dto.FindSongRequest;
import com.kapresoft.demo.pojo.dto.MovieInfoResponse;
import com.kapresoft.demo.pojo.dto.SongInfoResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Function;

/**
 * Need to define lambda env <b>FUNCTION_NAME</b> as either "findMovie" or "findSong"
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    Function<FindMovieRequest, MovieInfoResponse> findMovie() {
        return new MovieRequestFunction();
    }

    @Bean
    Function<FindSongRequest, SongInfoResponse> findSong() {
        return new SongRequestFunction();
    }

}