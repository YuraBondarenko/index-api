package com.index.api.dto.request;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CountryRequestDto {
    private Long id;
    private String name;
}
