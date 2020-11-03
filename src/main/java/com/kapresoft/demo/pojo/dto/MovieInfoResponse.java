package com.kapresoft.demo.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieInfoResponse {

    String characterName;
    String movieName;
    String year;
    String url;

}