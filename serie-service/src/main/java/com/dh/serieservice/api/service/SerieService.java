package com.dh.serieservice.api.service;

import com.dh.serieservice.domain.model.Serie;

import java.util.List;

public interface SerieService {
    List<Serie> getListByGenre(String genre);
    Serie save(Serie serie);
}
