package com.expedia.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.expedia.demo.pojo.dto.FindMovieRequest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@SpringBootTest
public class ApplicationTest {

    @Autowired
    ObjectMapper mapper;

    @Test
    void contextLoads() {
    }

    @Test
    void messageHeaders() throws JsonProcessingException {
        final FindMovieRequest request = FindMovieRequest.builder()
                .characterName("Frodo")
                .build();
        final Map<String, Object> headers = new HashMap<>();
        headers.put("hello", "there");
        headers.put("first", "name");
        final Message<FindMovieRequest> message = new GenericMessage<>(request, headers);
        final String messageText = mapper.writeValueAsString(message);
        log.info(messageText);
    }
}
