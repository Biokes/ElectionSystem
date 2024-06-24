package com.election.electionsystem.requests;

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
