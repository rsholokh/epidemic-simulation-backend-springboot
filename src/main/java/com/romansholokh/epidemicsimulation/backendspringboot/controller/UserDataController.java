package com.romansholokh.epidemicsimulation.backendspringboot.controller;

import com.romansholokh.epidemicsimulation.backendspringboot.entity.UserData;
import com.romansholokh.epidemicsimulation.backendspringboot.service.UserDataService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/user-data")
@CrossOrigin(origins = "http://localhost:4200")
public class UserDataController {

    private final UserDataService userDataService;

    @PostMapping("/add")
    public ResponseEntity<UserData> add(@RequestBody UserData userData) {
        if (userData.getId() != 0) {
            return new ResponseEntity("redundant param: id MUST NOT exist or MUST be 0", HttpStatus.NOT_ACCEPTABLE);
        } else if (userData.getSimulationName() == null || userData.getSimulationName().trim().length() == 0) {
            return new ResponseEntity("missed param: simulationName", HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(userDataService.add(userData));
    }
}
