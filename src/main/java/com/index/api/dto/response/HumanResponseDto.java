package com.index.api.dto.response;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class HumanResponseDto {
    private Long id;
    private String name;
    private int age;
    private Long cityId;
}
