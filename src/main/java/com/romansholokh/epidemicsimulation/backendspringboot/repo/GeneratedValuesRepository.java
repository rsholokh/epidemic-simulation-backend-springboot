package com.romansholokh.epidemicsimulation.backendspringboot.repo;

import com.romansholokh.epidemicsimulation.backendspringboot.entity.GeneratedValues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GeneratedValuesRepository extends JpaRepository<GeneratedValues, Long> {

    List<GeneratedValues> findAllByUserDataId(long id);

    boolean existsByUserDataId(long id);
}
