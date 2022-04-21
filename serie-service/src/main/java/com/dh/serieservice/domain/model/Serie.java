package com.dh.serieservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Series")
public class Serie {
    @Transient
    public static final String SEQUENCE_NAME = "series_sequence";

    @Id
    private Long id;
    private String name;
    private String genre;
    private List<Season> seasons;
}
