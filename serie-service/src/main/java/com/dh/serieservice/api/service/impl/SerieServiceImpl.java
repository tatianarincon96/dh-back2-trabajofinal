package com.dh.serieservice.api.service.impl;

import com.dh.serieservice.api.service.SerieService;
import com.dh.serieservice.domain.model.Serie;
import com.dh.serieservice.domain.repository.SerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SerieServiceImpl implements SerieService {
    private final SerieRepository serieRepository;

    @Autowired
    public SerieServiceImpl(SerieRepository serieRepository) {
        this.serieRepository = serieRepository;
    }

    @Override
    public List<Serie> getListByGenre(String genre) {
        return serieRepository.findAllByGenre(genre);
    }

    @Override
    public Serie save(Serie serie) {
        return serieRepository.save(serie);
    }
}
