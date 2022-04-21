package com.dh.catalogservice.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SeasonWS {
    private Integer id;
    private Integer seasonNumber;
    private List<ChapterWS> chapters;
}
