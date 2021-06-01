package com.romansholokh.epidemicsimulation.backendspringboot.repo;

import com.romansholokh.epidemicsimulation.backendspringboot.entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDataRepository extends JpaRepository<UserData, Long> {
}
