package com.romansholokh.epidemicsimulation.backendspringboot.controller;

import com.romansholokh.epidemicsimulation.backendspringboot.entity.GeneratedValues;
import com.romansholokh.epidemicsimulation.backendspringboot.service.GeneratedValuesService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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


}
