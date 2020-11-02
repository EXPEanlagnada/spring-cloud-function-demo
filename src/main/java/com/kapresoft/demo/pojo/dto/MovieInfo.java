package com.kapresoft.demo.pojo.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class MovieInfo {

    String characterName;
    String movieName;
    String year;
    String url;

}