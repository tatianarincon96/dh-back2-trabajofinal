package com.dh.catalogservice.api.service;

import com.dh.catalogservice.domain.model.dto.CatalogWS;
import com.dh.catalogservice.domain.model.dto.MovieWS;

import java.util.List;

public interface CatalogService {
    CatalogWS getMoviesCatalogByGenre(String genre);
}
