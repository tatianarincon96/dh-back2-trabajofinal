package com.dh.catalogservice.domain.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MovieWS {
    private Integer id;
    private String name;
    private String genre;
    private String urlStream;
}
