package com.dh.catalogservice.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChapterWS {
    private Integer id;
    private String name;
    private Integer number;
    private String urlStream;
}
