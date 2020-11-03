package com.kapresoft.demo.fn;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kapresoft.demo.pojo.dto.FindSongRequest;
import com.kapresoft.demo.pojo.dto.SongInfoResponse;
import com.kapresoft.demo.service.RequestUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Function;

@Slf4j
public class SongRequestFunction implements Function<FindSongRequest, SongInfoResponse> {

    private RequestUtil requestUtil;

    public SongRequestFunction() {
        this.requestUtil = new RequestUtil(new ObjectMapper());
    }

    @Override
    public SongInfoResponse apply(FindSongRequest request) {
        log.debug("\nFindSongRequest: {}", request);
        final SongInfoResponse response = SongInfoResponse.builder().request(request.getLyrics())
                .song("We are the World").build();
        log.debug("\nFindSongRequest Response: {}", requestUtil.toJsonString(response));
        return response;
    }

}
