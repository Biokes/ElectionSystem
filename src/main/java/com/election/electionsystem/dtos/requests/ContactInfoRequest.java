package com.election.electionsystem.dtos.requests;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class ContactInfoRequest {
    private String phoneNumber;
    private String stateOfOrigin;
    private String localGovernment;
}
