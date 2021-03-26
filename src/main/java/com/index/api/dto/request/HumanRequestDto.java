package com.index.api.dto.request;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class HumanRequestDto {
    private Long id;
    private String name;
    private int age;
    private Long cityId;
}
