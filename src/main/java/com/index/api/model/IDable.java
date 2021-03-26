package com.index.api.model;

import org.springframework.data.solr.core.mapping.SolrDocument;

@SolrDocument(collection = "id_able")
public interface IDable {
    Long getId();
}
