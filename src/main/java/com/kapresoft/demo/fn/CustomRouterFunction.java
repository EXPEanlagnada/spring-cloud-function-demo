package com.kapresoft.demo.fn;

import lombok.extern.slf4j.Slf4j;
import lombok.val;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kapresoft.demo.FunctionContext;
import com.kapresoft.demo.pojo.dto.FindMovieRequest;
import com.kapresoft.demo.pojo.dto.FindSongRequest;
import com.kapresoft.demo.pojo.dto.MovieInfoResponse;
import com.kapresoft.demo.pojo.dto.SongInfoResponse;

import org.springframework.cloud.function.context.FunctionCatalog;
import org.springframework.cloud.function.context.catalog.BeanFactoryAwareFunctionRegistry;
import org.springframework.messaging.Message;

import java.util.Optional;
import java.util.function.Function;

import static java.util.Optional.*;

@Slf4j
public class CustomRouterFunction implements Function<Message<Object>, Object> {

    private final ObjectMapper objectMapper;
    private final FunctionCatalog functionCatalog;

    public CustomRouterFunction(ObjectMapper objectMapper, FunctionCatalog functionCatalog) {
        this.objectMapper = objectMapper;
        this.functionCatalog = functionCatalog;
    }

    @Override
    public Object apply(Message<Object> message) {
        log.info("Payload type: {}", message.getPayload().getClass());
        val functionContext = locateFunction(objectMapper, message);
        if (functionContext.isPresent()) {
            return functionContext.get().apply();
        }
        return "Function not found for request message: " + message;
    }

    private Optional<FunctionContext<?, ?>> locateFunction(ObjectMapper objectMapper, Message<Object> message) {

        val findMovieRequest = readValue(objectMapper, FindMovieRequest.class, message.getPayload());
        log.info("findMovieRequest: {}", findMovieRequest);
        if (findMovieRequest.isPresent()) {
            val findMovie = (BeanFactoryAwareFunctionRegistry.FunctionInvocationWrapper) functionCatalog.lookup("findMovie");
            val functionContext = FunctionContext.<FindMovieRequest, MovieInfoResponse>builder()
                    .requestInstance(findMovieRequest.get())
                    .responseType(MovieInfoResponse.class)
                    .fn(findMovie).build();
            return of(functionContext);
        }

        val findSongRequest = readValue(objectMapper, FindSongRequest.class, message.getPayload());
        if (findSongRequest.isPresent()) {
            val findSong = (BeanFactoryAwareFunctionRegistry.FunctionInvocationWrapper) functionCatalog.lookup("findSong");
            val functionContext = FunctionContext.<FindSongRequest, SongInfoResponse>builder()
                    .requestInstance(findSongRequest.get())
                    .responseType(SongInfoResponse.class)
                    .fn(findSong).build();
            return of(functionContext);
        }

        return empty();
    }

    private <T> Optional<T> readValue(ObjectMapper objectMapper, Class<T> targetType, Object payload) {
        try {
            return ofNullable(objectMapper.readValue(payload.toString(), targetType));
        } catch (JsonProcessingException e) {
            log.warn("Payload is not of type: {}", targetType);
        }
        return empty();
    }
}
