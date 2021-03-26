package com.index.api.mapper.impl;

import com.index.api.dto.request.CityRequestDto;
import com.index.api.dto.response.CityResponseDto;
import com.index.api.mapper.Mapper;
import com.index.api.model.City;
import com.index.api.service.impl.CountryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CityMapper implements Mapper<City, CityRequestDto, CityResponseDto> {
    private final CountryServiceImpl countryService;

    @Override
    public City getEntity(CityRequestDto dto) {
        City city = new City().setId(dto.getId()).setName(dto.getName());
        if (dto.getCountryId() != null) {
            city.setCountry(countryService.getById(dto.getCountryId()));
        }
        return city;
    }

    @Override
    public CityResponseDto getDto(City entity) {
        CityResponseDto cityResponseDto = new CityResponseDto()
                .setId(entity.getId()).setName(entity.getName());
        if (cityResponseDto.getCountryId() != null) {
            cityResponseDto.setCountryId(entity.getCountry().getId());
        }
        return cityResponseDto;
    }
}
