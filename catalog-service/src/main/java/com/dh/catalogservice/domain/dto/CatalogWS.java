package com.dh.catalogservice.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data @Builder
@AllArgsConstructor
@NoArgsConstructor
public class CatalogWS {
	@Transient
	public static final String SEQUENCE_NAME = "catalog_sequence";
	private Long id;
	private String genre;
	private List<MovieWS> movies;
	private List<SerieWS> series;
}
