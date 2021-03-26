package com.index.api.service.impl;

import com.index.api.model.Human;
import com.index.api.repository.HumanRepository;
import com.index.api.service.IDableService;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HumanServiceImpl implements IDableService<Human> {
    private final HumanRepository humanRepository;

    @Override
    public Human save(Human model) {
        return humanRepository.save(model);
    }

    @Override
    public Human getById(Long id) {
        return humanRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cannot find human by id " + id));
    }

    @Override
    public List<Human> getAll(int page, int limit, String sortBy) {
        return humanRepository.findAll(PageRequest.of(page, limit, Sort.by(sortBy))).getContent();
    }

    @Override
    public void deleteAll() {
        humanRepository.deleteAll();
    }

}
