package com.romansholokh.epidemicsimulation.backendspringboot.util.engine;

import com.romansholokh.epidemicsimulation.backendspringboot.controller.GeneratedValuesController;
import com.romansholokh.epidemicsimulation.backendspringboot.entity.GeneratedValues;
import com.romansholokh.epidemicsimulation.backendspringboot.entity.UserData;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SimulationEngineImpl implements SimulationEngine {

    private final GeneratedValuesController generatedValuesController;
    private GeneratedValues generatedValues;

    @Override
    public void simulating(UserData userData) {

        long i = userData.getInitialNumberOfInfected();
        long r = userData.getInfectiousness();
        long m = userData.getMortality();
        long tm = userData.getDaysUntilDeath();
        long ti = userData.getDaysUntilRecovery();



        long presentDay = 0;

        long pi;
        long pv;
        long pm;
        long pr;

//        while(presentDay <= userData.getSimulationDays()) {
            if (presentDay == 0) {
                pi = i;
                pv = i * r;
                pm = 0;
                pr = 0;
                this.sendToDB(pi, pv, pm, pr, presentDay, userData);
                presentDay++;
                System.out.println("success simulating!");
            }
//        }
    }

    private void sendToDB(long pi, long pv, long pm, long pr, long presentDay, UserData userData) {
        generatedValues.setNumberOfInfected(pi);
        generatedValues.setNumberOfHealthySusceptibleToInfection(pv);
        generatedValues.setDeathToll(pm);
        generatedValues.setNumberOfRecoveredAndAcquiredImmunity(pr);
        generatedValues.setDay(presentDay);
        System.out.println("success sendToDB!");
        generatedValues.setUserData(userData);
        generatedValuesController.add(generatedValues);
    }
}
