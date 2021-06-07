package com.romansholokh.epidemicsimulation.backendspringboot.entity;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Entity
@NoArgsConstructor
@Setter
@EqualsAndHashCode
public class UserData {

    private long id;
    @NotEmpty(message = "simulationName should not be empty")
    private String simulationName;
    @Min(value = 1, message = "populationSize should be greater than 0")
    private long populationSize;
    @Min(value = 1, message = "initialNumberOfInfected should be greater than 0")
    private long initialNumberOfInfected;
    @Min(value = 1, message = "infectiousness should be greater than 0")
    private long infectiousness;
    @Min(value = 0, message = "mortality should be greater than 0")
    private double mortality;
    @Min(value = 1, message = "daysUntilRecovery should be greater than 0")
    private long daysUntilRecovery;
    @Min(value = 1, message = "daysUntilDeath should be greater than 0")
    private long daysUntilDeath;
    @Min(value = 1, message = "simulationDays should be greater than 0")
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
    public double getMortality() {
        return mortality;
    }

    @Basic
    @Column(name = "days_until_death", nullable = false)
    public long getDaysUntilDeath() {
        return daysUntilDeath;
    }

    @Basic
    @Column(name = "days_until_recovery", nullable = false)
    public long getDaysUntilRecovery() {
        return daysUntilRecovery;
    }

    @Basic
    @Column(name = "simulation_days", nullable = false)
    public long getSimulationDays() {
        return simulationDays;
    }
}