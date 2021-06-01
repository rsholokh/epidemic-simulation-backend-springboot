package com.romansholokh.epidemicsimulation.backendspringboot.entity;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Setter
@EqualsAndHashCode
public class UserData {

    private long id;
    private String simulationName;
    private long populationSize;
    private long initialNumberOfInfected;
    private long infectiousness;
    private long mortality;
    private long daysUntilRecovery;
    private long daysUntilDeath;
    private long simulationDays;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    @Basic
    @Column(name = "simulation_name", nullable = false, length = 100)
    public String getSimulationName() {
        return simulationName;
    }

    @Basic
    @Column(name = "population_size", nullable = false)
    public long getPopulationSize() {
        return populationSize;
    }

    @Basic
    @Column(name = "initial_number_of_infected", nullable = false)
    public long getInitialNumberOfInfected() {
        return initialNumberOfInfected;
    }

    @Basic
    @Column(name = "infectiousness", nullable = false)
    public long getInfectiousness() {
        return infectiousness;
    }

    @Basic
    @Column(name = "mortality", nullable = false)
    public long getMortality() {
        return mortality;
    }

    @Basic
    @Column(name = "days_until_recovery", nullable = false)
    public long getDaysUntilRecovery() {
        return daysUntilRecovery;
    }

    @Basic
    @Column(name = "days_until_death", nullable = false)
    public long getDaysUntilDeath() {
        return daysUntilDeath;
    }

    @Basic
    @Column(name = "simulation_days", nullable = false)
    public long getSimulationDays() {
        return simulationDays;
    }
}