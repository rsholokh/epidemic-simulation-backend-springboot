package com.romansholokh.epidemicsimulation.backendspringboot.service;

import com.romansholokh.epidemicsimulation.backendspringboot.entity.UserData;
import com.romansholokh.epidemicsimulation.backendspringboot.repo.UserDataRepository;
import com.romansholokh.epidemicsimulation.backendspringboot.util.engine.SimulationEngine;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class UserDataService {

    private final UserDataRepository userDataRepository;
    private final SimulationEngine simulationEngine;

    public UserData add(UserData userData) {
        return userDataRepository.save(userData);
    }

    public Optional<UserData> getById(Long id) {
        return userDataRepository.findById(id);
    }

    public Boolean existById(Long id) {
        return userDataRepository.existsById(id);
    }

    public void simulateByUserDataId(UserData userData){
        simulationEngine.simulating(userData);
    }
}
