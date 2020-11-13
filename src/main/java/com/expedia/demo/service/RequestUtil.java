package com.expedia.demo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RequestUtil {

    private ObjectMapper mapper;

    public RequestUtil() {
        this(new ObjectMapper());
    }

    public RequestUtil(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public String toJsonString(Object o) {
        try {
            return mapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            log.info("Failed to write object to json: {}", e.getMessage(), e);
        }
        return null;
    }

}
