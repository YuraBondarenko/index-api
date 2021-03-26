package com.index.api.dto.response;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CountryResponseDto {
    private Long id;
    private String name;
}
