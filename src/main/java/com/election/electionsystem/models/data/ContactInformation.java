package com.election.electionsystem.models.data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "contactInformations")
public class ContactInformation {
    @Id
    private Long id;
    private String phoneNumber;
    private String stateOfOrigin;
    private String localGovernment;
}
