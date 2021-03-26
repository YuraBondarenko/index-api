package com.index.api.dto.response;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CityResponseDto {
    private Long id;
    private String name;
    private Long countryId;
}
