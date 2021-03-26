package com.index.api.mapper.impl;

import com.index.api.dto.request.CountryRequestDto;
import com.index.api.dto.response.CountryResponseDto;
import com.index.api.mapper.Mapper;
import com.index.api.model.Country;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CountryMapper implements Mapper<Country, CountryRequestDto, CountryResponseDto> {
    private final ModelMapper modelMapper;

    @Override
    public Country getEntity(CountryRequestDto dto) {
        return modelMapper.map(dto, Country.class);
    }

    @Override
    public CountryResponseDto getDto(Country entity) {
        return new CountryResponseDto().setId(entity.getId()).setName(entity.getName());
    }
}
