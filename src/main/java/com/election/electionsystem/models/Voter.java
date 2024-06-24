package com.election.electionsystem.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Setter
@Getter
@Entity
@Table(name="voters")
public class Voter {
    @Id
    @GeneratedValue(strategy = SEQUENCE)
    private Long id;
}
