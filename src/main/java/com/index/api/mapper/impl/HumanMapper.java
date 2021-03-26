package com.index.api.mapper.impl;

import com.index.api.dto.request.HumanRequestDto;
import com.index.api.dto.response.HumanResponseDto;
import com.index.api.mapper.Mapper;
import com.index.api.model.Human;
import com.index.api.service.impl.CityServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HumanMapper implements Mapper<Human, HumanRequestDto, HumanResponseDto> {
    private final CityServiceImpl cityService;

    @Override
    public Human getEntity(HumanRequestDto dto) {
        Human human = new Human().setId(dto.getId()).setName(dto.getName())
                .setAge(dto.getAge());
        if (dto.getCityId() != null) {
            human.setCity(cityService.getById(dto.getCityId()));
        }
        return human;
    }

    @Override
    public HumanResponseDto getDto(Human entity) {
        HumanResponseDto humanResponseDto = new HumanResponseDto().setId(entity.getId())
                .setName(entity.getName()).setAge(entity.getAge());
        if (entity.getCity() != null) {
            humanResponseDto.setCityId(entity.getId());
        }
        return humanResponseDto;
    }
}
