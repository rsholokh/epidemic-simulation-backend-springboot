package com.romansholokh.epidemicsimulation.backendspringboot.service;

import com.romansholokh.epidemicsimulation.backendspringboot.entity.UserData;
import com.romansholokh.epidemicsimulation.backendspringboot.repo.UserDataRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class UserDataService {

    private final UserDataRepository userDataRepository;

    public UserData add(UserData userData)
    {
        return userDataRepository.save(userData);
    }
}
