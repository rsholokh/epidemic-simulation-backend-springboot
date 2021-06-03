package com.romansholokh.epidemicsimulation.backendspringboot.util.engine;

import com.romansholokh.epidemicsimulation.backendspringboot.entity.UserData;

public interface SimulationEngine {

    public void simulating(UserData userData);
}
