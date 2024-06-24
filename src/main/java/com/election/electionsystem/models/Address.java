package com.election.electionsystem.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name= "voters_address")
public class Address {
    @Id
    private Long id;
    private String street;
    private String area;
    private String State;
    private String houseNumber;
}
