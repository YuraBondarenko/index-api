package com.index.api.dto.request;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CityRequestDto {
    private Long id;
    private String name;
    private Long countryId;
}
