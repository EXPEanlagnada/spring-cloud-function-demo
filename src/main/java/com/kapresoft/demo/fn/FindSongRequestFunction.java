package com.kapresoft.demo.fn;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kapresoft.demo.pojo.dto.FindSongRequest;
import com.kapresoft.demo.pojo.dto.SongInfoResponse;
import com.kapresoft.demo.service.RequestUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Function;

@Slf4j
public class FindSongRequestFunction implements Function<FindSongRequest, SongInfoResponse> {

    public static final String LYRIC = "When the world must come together as one";
    public static final String WE_ARE_THE_WORLD_SONG = "We are the World";
    private final RequestUtil requestUtil;

    public FindSongRequestFunction() {
        this.requestUtil = new RequestUtil(new ObjectMapper());
    }

    @Override
    public SongInfoResponse apply(FindSongRequest request) {
        log.debug("\nFindSongRequest: {}", request);
        SongInfoResponse response = SongInfoResponse.builder().build();
        if (request.getLyrics().equalsIgnoreCase(LYRIC)) {
            response = SongInfoResponse.builder().request(request.getLyrics())
                    .song(WE_ARE_THE_WORLD_SONG).build();
        }
        log.debug("\nSong Found: {}", requestUtil.toJsonString(response));
        return response;
    }

}
