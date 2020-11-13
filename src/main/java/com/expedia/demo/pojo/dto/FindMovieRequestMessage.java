package com.expedia.demo.pojo.dto;

import org.springframework.messaging.support.GenericMessage;

import java.util.HashMap;
import java.util.Map;

public class FindMovieRequestMessage extends GenericMessage<FindMovieRequest> {

    public FindMovieRequestMessage() {
        this(FindMovieRequest.builder().build(), new HashMap<>());
    }

    public FindMovieRequestMessage(FindMovieRequest payload, Map<String, Object> headers) {
        super(payload, headers);
    }


}
