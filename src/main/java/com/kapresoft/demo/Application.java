package com.kapresoft.demo;

import com.kapresoft.demo.fn.MovieRequestFunction;
import com.kapresoft.demo.pojo.dto.FindMovieRequest;
import com.kapresoft.demo.pojo.dto.MovieInfo;
import com.kapresoft.demo.service.MovieFinder;
import com.kapresoft.demo.service.RequestUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Function;

@SpringBootApplication
public class Application {

    private MovieFinder movieFinder;
    private RequestUtil requestUtil;

    public Application(MovieFinder movieFinder, RequestUtil requestUtil) {
        this.movieFinder = movieFinder;
        this.requestUtil = requestUtil;
    }

    @Bean
    Function<FindMovieRequest, MovieInfo> findMovieRequest() {
        return new MovieRequestFunction(movieFinder, requestUtil);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}