package com.dh.catalogservice.domain.repository;

import com.dh.catalogservice.domain.dto.CatalogWS;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CatalogRepository extends MongoRepository<CatalogWS, Long> {
    Optional<CatalogWS> findByGenre(String genre);
}
