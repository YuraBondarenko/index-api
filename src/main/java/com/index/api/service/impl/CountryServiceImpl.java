package com.index.api.service.impl;

import com.index.api.model.Country;
import com.index.api.repository.CountryRepository;
import com.index.api.service.IDableService;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements IDableService<Country> {
    private final CountryRepository countryRepository;

    @Override
    public Country save(Country model) {
        return countryRepository.save(model);
    }

    @Override
    public Country getById(Long id) {
        return countryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cannot find country by id " + id));
    }

    @Override
    public List<Country> getAll(int page, int limit, String sortBy) {
        return countryRepository.findAll(PageRequest.of(page, limit, Sort.by(sortBy))).getContent();
    }

    @Override
    public void deleteAll() {
        countryRepository.deleteAll();
    }
}
