package com.election.electionsystem.data.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import static jakarta.persistence.GenerationType.IDENTITY;

@Setter
@Getter
@ToString
@Entity
@Table(name= "voters_address")
public class Address {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String street;
    private String area;
    private String State;
    private String houseNumber;
}
