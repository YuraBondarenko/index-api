package com.index.api.service.impl;

import com.index.api.model.City;
import com.index.api.repository.CityRepository;
import com.index.api.service.IDableService;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CityServiceImpl implements IDableService<City> {
    private final CityRepository cityRepository;

    @Override
    public City save(City model) {
        return cityRepository.save(model);
    }

    @Override
    public City getById(Long id) {
        return cityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cannot find city by id " + id));
    }

    @Override
    public List<City> getAll(int page, int limit, String sortBy) {
        return cityRepository.findAll(PageRequest.of(page, limit, Sort.by(sortBy))).getContent();
    }

    @Override
    public void deleteAll() {
        cityRepository.deleteAll();
    }
}
