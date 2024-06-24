package com.election.electionsystem.requests;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class AddressRequest {
    private String street;
    private String area;
    private String State;
    private String houseNumber;
}
