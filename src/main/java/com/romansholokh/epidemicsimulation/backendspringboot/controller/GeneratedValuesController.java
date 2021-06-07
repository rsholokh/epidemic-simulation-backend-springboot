package com.romansholokh.epidemicsimulation.backendspringboot.controller;

import com.romansholokh.epidemicsimulation.backendspringboot.entity.GeneratedValues;
import com.romansholokh.epidemicsimulation.backendspringboot.service.GeneratedValuesService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/generated-values")
@CrossOrigin(origins = "http://localhost:4200")
public class GeneratedValuesController {

    private final GeneratedValuesService generatedValuesService;

    public void add(GeneratedValues generatedValues) {
        generatedValuesService.add(generatedValues);
    }

    public void addAll(List<GeneratedValues> generatedValuesList) {
        generatedValuesService.addAll(generatedValuesList);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<GeneratedValues> getById(@PathVariable Long id) {
        GeneratedValues generatedValues = null;
        Optional<GeneratedValues> optional = generatedValuesService.getById(id);
        if (optional.isPresent()) {
            generatedValues = optional.get();
        } else {
            return new ResponseEntity("id = " + id + " not found", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(generatedValues);
    }

}
