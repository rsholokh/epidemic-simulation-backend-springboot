package com.romansholokh.epidemicsimulation.backendspringboot.controller;

import com.romansholokh.epidemicsimulation.backendspringboot.entity.GeneratedValues;
import com.romansholokh.epidemicsimulation.backendspringboot.service.GeneratedValuesService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/generated-values")
@CrossOrigin(origins = "http://localhost:4200")
public class GeneratedValuesController {

    private final GeneratedValuesService generatedValuesService;

    public void add(GeneratedValues generatedValues) {
        generatedValuesService.add(generatedValues);
    }
}
