package com.romansholokh.epidemicsimulation.backendspringboot.util.engine;

import com.romansholokh.epidemicsimulation.backendspringboot.controller.GeneratedValuesController;
import com.romansholokh.epidemicsimulation.backendspringboot.entity.GeneratedValues;
import com.romansholokh.epidemicsimulation.backendspringboot.entity.UserData;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@AllArgsConstructor
public class SimulatingEngineImpl implements SimulationEngine{
    private final GeneratedValuesController generatedValuesController;
    private GeneratedValues generatedValues;
    private ArrayList<GeneratedValues> generatedValuesList;
    private Map<Long, Long> newInfectedThisDay;

    @Override
    public void simulating(UserData userData) {

        long populationSize = userData.getPopulationSize(); // p
        long initialNumberOfInfected = userData.getInitialNumberOfInfected(); // i
        long infectiousness = userData.getInfectiousness(); // r
        double mortality = userData.getMortality(); // m
        long daysUntilDeath = userData.getDaysUntilDeath(); // tm
        long daysUntilRecovery = userData.getDaysUntilRecovery(); // ti

        long presentDay = 0;

        long numberOfInfected = 0; // pi
        long numberOfHealthySusceptibleToInfection = 0; // pv
        long deathToll = 0; // pm
        long numberOfRecoveredAndAcquiredImmunity = 0; // pr

        while (presentDay <= userData.getSimulationDays()) {
            //START DAY
            if (presentDay == 0) {
                numberOfInfected = this.calcOfNumberOfInfected(initialNumberOfInfected, populationSize, presentDay);

                numberOfHealthySusceptibleToInfection = this.calcOfNumberOfHealthySusceptibleToInfection(numberOfInfected,
                        infectiousness, populationSize);

                deathToll = 0;

                numberOfRecoveredAndAcquiredImmunity = 0;
                //SENDING TO LIST
                this.storeInList(numberOfInfected, numberOfHealthySusceptibleToInfection,
                        deathToll, numberOfRecoveredAndAcquiredImmunity,
                        presentDay, userData);

                System.out.println("success simulate day" + presentDay);
                presentDay++;
            }
            //OTHER DAYS
            numberOfInfected = this.calcOfNumberOfInfected(numberOfInfected,
                    numberOfHealthySusceptibleToInfection, populationSize, presentDay);

            deathToll = this.calcOfDeathTall(presentDay,daysUntilDeath, daysUntilRecovery,
                    mortality, populationSize);
            if (deathToll > 0) {
                numberOfInfected = numberOfInfected - deathToll < 0 ? 0 : numberOfInfected - deathToll;
                populationSize = populationSize - deathToll < 0 ? 0 : populationSize - deathToll;
            }

            numberOfRecoveredAndAcquiredImmunity = this.calcNumberOfRecoveredAndAcquiredImmunity(presentDay, daysUntilDeath,
                    daysUntilRecovery, mortality, populationSize);
            if (numberOfRecoveredAndAcquiredImmunity > 0) {
                numberOfInfected = numberOfInfected - numberOfRecoveredAndAcquiredImmunity < 0 ? 0 : numberOfInfected - numberOfRecoveredAndAcquiredImmunity;
                populationSize = populationSize - numberOfRecoveredAndAcquiredImmunity < 0 ? 0 : populationSize - numberOfRecoveredAndAcquiredImmunity;
            }

            numberOfHealthySusceptibleToInfection = this.calcOfNumberOfHealthySusceptibleToInfection(numberOfInfected,
                    infectiousness, populationSize);

            //SENDING TO LIST
            this.storeInList(numberOfInfected, numberOfHealthySusceptibleToInfection,
                    deathToll, numberOfRecoveredAndAcquiredImmunity,
                    presentDay, userData);

            System.out.println("success simulate day" + presentDay);
            presentDay++;
        }
        //AFTER COMPLETING SIMULATION
        this.sendListToDatabase(generatedValuesList);
    }

    private long calcOfNumberOfInfected(long initialNumberOfInfected, long populationSize, long presentDay) {

        newInfectedThisDay.put(presentDay, Math.min(initialNumberOfInfected, populationSize));
        return Math.min(initialNumberOfInfected, populationSize);
    }

    private long calcOfNumberOfInfected(long numberOfInfected,
                                        long numberOfHealthySusceptibleToInfection,
                                        long populationSize, long presentDay) {

        newInfectedThisDay.put(presentDay, numberOfHealthySusceptibleToInfection);

        if (numberOfInfected + numberOfHealthySusceptibleToInfection <= 0) {
            return 0;
        } else {
            return Math.min((numberOfInfected + numberOfHealthySusceptibleToInfection),
                    populationSize);
        }
    }

    private long calcOfNumberOfHealthySusceptibleToInfection(long numberOfInfected, long infectiousness,
                                                             long populationSize) {

        long numberOfHealthySusceptibleToInfection = numberOfInfected * infectiousness;

        if (numberOfHealthySusceptibleToInfection <= 0) {
           return 0;
        } else if (numberOfHealthySusceptibleToInfection >= populationSize) {
            return populationSize - numberOfInfected;
        } else {
            return numberOfHealthySusceptibleToInfection;
        }
    }

    private long calcOfDeathTall(long presentDay, long daysUntilDeath, long daysUntilRecovery,
                                 double mortality, long populationSize) {

        long dayOfOnsetOfIllness = presentDay - daysUntilDeath;

        return Math.min( numberOfDeathsThatDay(dayOfOnsetOfIllness, daysUntilDeath, daysUntilRecovery, mortality),
                    populationSize );

    }

    private long calcNumberOfRecoveredAndAcquiredImmunity(long presentDay, long daysUntilDeath, long daysUntilRecovery,
                                                          double mortality, long populationSize) {

        long dayOfOnsetOfIllness = presentDay - daysUntilRecovery;

        return numberOfRecoveredThatDay(dayOfOnsetOfIllness, daysUntilDeath,daysUntilRecovery, mortality);

    }

    private long numberOfDeathsThatDay(long dayOfOnsetOfIllness, long daysUntilDeath,
                                       long daysUntilRecovery, double mortality) {

        if (dayOfOnsetOfIllness < 0 || daysUntilRecovery < daysUntilDeath) {
            return 0;
        }

        long numberOfNewInfectedThatDay = newInfectedThisDay.get(dayOfOnsetOfIllness);

        return (long) (numberOfNewInfectedThatDay * mortality);
    }

    private long numberOfRecoveredThatDay(long dayOfOnsetOfIllness, long daysUntilDeath,
                                       long daysUntilRecovery, double mortality) {

        if (dayOfOnsetOfIllness < 0) {
            return 0;
        } else if (daysUntilRecovery < daysUntilDeath) {
            return newInfectedThisDay.get(dayOfOnsetOfIllness);
        } else {
            return newInfectedThisDay.get(dayOfOnsetOfIllness) - this.numberOfDeathsThatDay(dayOfOnsetOfIllness, daysUntilDeath,
                    daysUntilRecovery, mortality);
        }
    }

    private void storeInList(long numberOfInfected, long numberOfHealthySusceptibleToInfection,
                             long deathToll, long numberOfRecoveredAndAcquiredImmunity,
                             long presentDay, UserData userData) {

        generatedValues = new GeneratedValues();

        generatedValues.setNumberOfInfected(numberOfInfected);
        generatedValues.setNumberOfHealthySusceptibleToInfection(numberOfHealthySusceptibleToInfection);
        generatedValues.setDeathToll(deathToll);
        generatedValues.setNumberOfRecoveredAndAcquiredImmunity(numberOfRecoveredAndAcquiredImmunity);
        generatedValues.setDay(presentDay);
        generatedValues.setUserData(userData);

        generatedValuesList.add((int) presentDay, generatedValues);
    }

    private void sendListToDatabase(List<GeneratedValues> generatedValuesList) {
        generatedValuesController.addAll(generatedValuesList);
    }
}
