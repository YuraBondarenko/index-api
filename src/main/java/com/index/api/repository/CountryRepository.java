package com.index.api.repository;

import com.index.api.model.Country;
import org.springframework.data.solr.repository.SolrCrudRepository;

public interface CountryRepository extends SolrCrudRepository<Country, Long> {
}
