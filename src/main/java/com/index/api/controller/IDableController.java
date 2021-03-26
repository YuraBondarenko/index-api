package com.index.api.controller;

import com.index.api.dto.request.CityRequestDto;
import com.index.api.dto.request.CountryRequestDto;
import com.index.api.dto.request.HumanRequestDto;
import com.index.api.dto.response.CityResponseDto;
import com.index.api.dto.response.CountryResponseDto;
import com.index.api.dto.response.HumanResponseDto;
import com.index.api.mapper.Mapper;
import com.index.api.model.City;
import com.index.api.model.Country;
import com.index.api.model.Human;
import com.index.api.model.IDable;
import com.index.api.service.IDableService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping
@RestController
@RequiredArgsConstructor
public class IDableController {
    private static final String HUMAN = "Human";
    private static final String CITY = "City";
    private static final String COUNTRY = "Country";
    private static final String SERVICE_NAME = "ServiceImpl";
    private static final String MAPPER_NAME = "Mapper";
    private final Map<String, IDableService<? extends IDable>> servicesMap = new HashMap<>();
    private final Map<String, Mapper<? extends IDable, ?, ?>> mappersMap = new HashMap<>();
    private final List<IDableService<? extends IDable>> idableServices;
    private final List<Mapper<? extends IDable, ?, ?>> mappers;

    @PostConstruct
    public void fillingMaps() {
        for (IDableService<? extends IDable> idableService : idableServices) {
            servicesMap.put(idableService.getClass().getSimpleName(), idableService);
        }
        for (Mapper<? extends IDable, ?, ?> mapper : mappers) {
            mappersMap.put(mapper.getClass().getSimpleName(), mapper);
        }
    }

    @PostMapping("/country")
    public synchronized ResponseEntity<CountryResponseDto> saveCountry(
            @RequestBody CountryRequestDto countryRequestDto) {
        Mapper<Country, CountryRequestDto, CountryResponseDto> countryMapper = getCountryMapper();
        return new ResponseEntity<>(countryMapper.getDto(getCountryService()
                .save(countryMapper.getEntity(countryRequestDto))), HttpStatus.CREATED);
    }

    @PostMapping("/city")
    public synchronized ResponseEntity<CityResponseDto> saveCity(
            @RequestBody CityRequestDto cityRequestDto) {
        Mapper<City, CityRequestDto, CityResponseDto> cityMapper = getCityMapper();
        return new ResponseEntity<>(cityMapper.getDto(getCityService()
                .save(cityMapper.getEntity(cityRequestDto))), HttpStatus.CREATED);
    }

    @PostMapping("/human")
    public synchronized ResponseEntity<HumanResponseDto> saveHuman(
            @RequestBody HumanRequestDto humanRequestDto) {
        Mapper<Human, HumanRequestDto, HumanResponseDto> humanMapper = getHumanMapper();
        return new ResponseEntity<>(humanMapper.getDto(getHumanService().save(getHumanMapper()
                .getEntity(humanRequestDto))), HttpStatus.CREATED);
    }

    @GetMapping("/country/{id}")
    public ResponseEntity<CountryResponseDto> getCountry(@PathVariable Long id) {
        return new ResponseEntity<>(getCountryMapper()
                .getDto(getCountryService().getById(id)), HttpStatus.OK);
    }

    @GetMapping("/city/{id}")
    public ResponseEntity<CityResponseDto> getCity(@PathVariable Long id) {
        return new ResponseEntity<>(getCityMapper()
                .getDto(getCityService().getById(id)), HttpStatus.OK);
    }

    @GetMapping("/human/{id}")
    public ResponseEntity<HumanResponseDto> getHuman(@PathVariable Long id) {
        return new ResponseEntity<>(getHumanMapper()
                .getDto(getHumanService().getById(id)), HttpStatus.OK);
    }

    @GetMapping("/country/all")
    public ResponseEntity<List<CountryResponseDto>> getAllCountry(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int limit,
            @RequestParam(required = false, defaultValue = "id") String sortBy) {
        return new ResponseEntity<>(getCountryService().getAll(page, limit, sortBy).stream()
                .map(s -> getCountryMapper().getDto(s))
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/city/all")
    public ResponseEntity<List<CityResponseDto>> getAllCity(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int limit,
            @RequestParam(required = false, defaultValue = "id") String sortBy) {
        return new ResponseEntity<>(getCityService().getAll(page, limit, sortBy).stream()
                .map(s -> getCityMapper().getDto(s))
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/human/all")
    public ResponseEntity<List<HumanResponseDto>> getAllHuman(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int limit,
            @RequestParam(required = false, defaultValue = "id") String sortBy) {
        return new ResponseEntity<>(getHumanService().getAll(page, limit, sortBy).stream()
                .map(s -> getHumanMapper().getDto(s))
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    private IDableService<Human> getHumanService() {
        return (IDableService<Human>) servicesMap.get(HUMAN + SERVICE_NAME);
    }

    private IDableService<Country> getCountryService() {
        return (IDableService<Country>) servicesMap.get(COUNTRY + SERVICE_NAME);
    }

    private IDableService<City> getCityService() {
        return (IDableService<City>) servicesMap.get(CITY + SERVICE_NAME);
    }

    private Mapper<City, CityRequestDto, CityResponseDto> getCityMapper() {
        return (Mapper<City, CityRequestDto, CityResponseDto>) mappersMap.get(CITY + MAPPER_NAME);
    }

    private Mapper<Country, CountryRequestDto, CountryResponseDto> getCountryMapper() {
        return (Mapper<Country, CountryRequestDto, CountryResponseDto>)
                mappersMap.get(COUNTRY + MAPPER_NAME);
    }

    private Mapper<Human, HumanRequestDto, HumanResponseDto> getHumanMapper() {
        return (Mapper<Human, HumanRequestDto, HumanResponseDto>)
                mappersMap.get(HUMAN + MAPPER_NAME);
    }
}
