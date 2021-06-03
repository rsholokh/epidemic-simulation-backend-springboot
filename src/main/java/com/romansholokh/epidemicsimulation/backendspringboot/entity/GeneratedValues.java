package com.romansholokh.epidemicsimulation.backendspringboot.entity;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Component
@NoArgsConstructor
@Setter
@EqualsAndHashCode
public class GeneratedValues {

    private long id;
    private long day;
    private long numberOfInfected;
    private long numberOfHealthySusceptibleToInfection;
    private long deathToll;
    private long numberOfRecoveredAndAcquiredImmunity;
    private UserData userData;

    @Id
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    @Basic
    @Column(name = "day", nullable = false)
    public long getDay() {
        return day;
    }

    @Basic
    @Column(name = "number_of_infected", nullable = false)
    public long getNumberOfInfected() {
        return numberOfInfected;
    }

    @Basic
    @Column(name = "number_of_healthy_susceptible_to_infection", nullable = false)
    public long getNumberOfHealthySusceptibleToInfection() {
        return numberOfHealthySusceptibleToInfection;
    }

    @Basic
    @Column(name = "death_toll", nullable = false)
    public long getDeathToll() {
        return deathToll;
    }

    @Basic
    @Column(name = "number_of_recovered_and_acquired_immunity", nullable = false)
    public long getNumberOfRecoveredAndAcquiredImmunity() {
        return numberOfRecoveredAndAcquiredImmunity;
    }

    @ManyToOne
    @JoinColumn(name = "user_data_id", referencedColumnName = "id", nullable = false)
    public UserData getUserData() {
        return userData;
    }

}
