package com.election.electionsystem.requests;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Builder
@Setter
@Getter
public class VoterRequest {
    private String firstname;
    private String lastname;
    private AddressRequest address;
    private String email;
    private String password;
    private LocalDate DOB;
    private ContactInfoRequest infoRequest;
}
