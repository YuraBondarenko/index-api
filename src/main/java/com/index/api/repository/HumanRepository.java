package com.index.api.repository;

import com.index.api.model.Human;
import org.springframework.data.solr.repository.SolrCrudRepository;

public interface HumanRepository extends SolrCrudRepository<Human, Long> {
}
