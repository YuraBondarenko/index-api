package com.index.api.model;

import javax.persistence.Id;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.solr.core.mapping.SolrDocument;

@Data
@Accessors(chain = true)
@SolrDocument(collection = "cities")
public class City implements IDable {
    @Field
    @Id
    private Long id;
    @Field
    private String name;
    @Field
    private Country country;
}
