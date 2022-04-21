package com.dh.catalogservice.api.service;

import com.dh.catalogservice.domain.dto.CatalogWS;

public interface CatalogService {
    CatalogWS getCatalogByGenre(String genre);
    void updateCatalogByGenre(String genre);
}
