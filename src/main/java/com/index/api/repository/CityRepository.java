package com.index.api.repository;

import com.index.api.model.City;
import org.springframework.data.solr.repository.SolrCrudRepository;

public interface CityRepository extends SolrCrudRepository<City, Long> {
}
