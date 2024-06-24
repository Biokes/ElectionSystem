package com.election.electionsystem.models.data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Entity
@ToString
@Table(name = "contactInformations")
public class ContactInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String phoneNumber;
    private String stateOfOrigin;
    private String localGovernment;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_information_address_id")
    private Address address;
}
