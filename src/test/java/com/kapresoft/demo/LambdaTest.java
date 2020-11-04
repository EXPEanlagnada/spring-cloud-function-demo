package com.kapresoft.demo;

import lombok.extern.slf4j.Slf4j;
import lombok.val;

import com.amazonaws.services.lambda.runtime.Context;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kapresoft.demo.fn.FindMovieRequestFunction;
import com.kapresoft.demo.pojo.dto.MovieInfoResponse;
import com.kapresoft.demo.pojo.dto.SongInfoResponse;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.DefaultResourceLoader;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static com.kapresoft.demo.fn.FindMovieRequestFunction.INIGO_MONTOYA_CHARACTER;
import static com.kapresoft.demo.fn.FindSongRequestFunction.LYRIC;
import static com.kapresoft.demo.fn.FindSongRequestFunction.WE_ARE_THE_WORLD_SONG;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Define Env Vars:
 * MAIN_CLASS=com.kapresoft.demo.Application
 * FUNCTION_NAME=findMovie or findSong
 */
@Slf4j
@SpringBootTest
class LambdaTest {

    public static final String FUNCTION_DEFINITION = "spring.cloud.function.definition";
    @Mock
    Context context;
    @Autowired
    ObjectMapper mapper;
    @Autowired
    DefaultResourceLoader resourceLoader;


    @Test
    void findMovieRequest() throws IOException {
        val lambda = createLambda("findMovie");

        val requestInputStream = resourceLoader.getResource("classpath:findMovieRequest.json").getInputStream();
        val outputStream = new ByteArrayOutputStream();
        lambda.handleRequest(requestInputStream, outputStream, context);

        val responseText = outputStream.toString(StandardCharsets.UTF_8);
        assertThat(responseText).isNotEmpty();

        val response = mapper.readValue(responseText, MovieInfoResponse.class);

        assertThat(response).as("Response")
                .isNotNull();
        assertThat(response.getCharacterName()).as("CharacterName")
                .isEqualTo(INIGO_MONTOYA_CHARACTER);
        assertThat(response.getMovieName()).as("Movie Name")
                .isEqualTo(FindMovieRequestFunction.THE_PRINCESS_PRIDE_MOVIE);
    }

    @Test
    void findSongRequest() throws IOException {
        val lambda = createLambda("findSong");

        val requestInputStream = resourceLoader.getResource("classpath:findSongRequest.json").getInputStream();
        val outputStream = new ByteArrayOutputStream();
        lambda.handleRequest(requestInputStream, outputStream, context);

        val responseText = outputStream.toString(StandardCharsets.UTF_8);
        assertThat(responseText).isNotEmpty();

        val response = mapper.readValue(responseText, SongInfoResponse.class);

        assertThat(response).as("Response")
                .isNotNull();
        assertThat(response.getSong())
                .isEqualTo(WE_ARE_THE_WORLD_SONG);
        assertThat(response.getRequest()).isEqualTo(LYRIC);
    }

    private Lambda createLambda(String functionName) {
        System.setProperty(FUNCTION_DEFINITION, functionName);
        System.setProperty("MAIN_CLASS", Application.class.getName());
        return new Lambda();
    }
}