package com.romansholokh.epidemicsimulation.backendspringboot.service;

import com.romansholokh.epidemicsimulation.backendspringboot.entity.GeneratedValues;
import com.romansholokh.epidemicsimulation.backendspringboot.repo.GeneratedValuesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class GeneratedValuesService {

    private final GeneratedValuesRepository generatedValuesRepository;

    public GeneratedValues add(GeneratedValues generatedValues) {
        return generatedValuesRepository.save(generatedValues);
    }

    public void addAll(List<GeneratedValues> generatedValuesList) {
        generatedValuesRepository.saveAll(generatedValuesList);
    }

    public Optional<GeneratedValues> getById(Long id) {
        return generatedValuesRepository.findById(id);
    }

    public List<GeneratedValues> getAllByUserDataId(Long id) {
        return generatedValuesRepository.findAllByUserDataId(id);

    }

    public boolean existsByUserDataId(long id) {
        return generatedValuesRepository.existsByUserDataId(id);

    }
}
