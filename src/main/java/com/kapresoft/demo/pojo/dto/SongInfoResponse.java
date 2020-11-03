package com.kapresoft.demo.pojo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SongInfoResponse {

    String request;
    String song;

}
