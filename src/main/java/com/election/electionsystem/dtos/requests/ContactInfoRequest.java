package com.election.electionsystem.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class ContactInfoRequest {
    @NotBlank
    private String phoneNumber;
    @NotBlank
    private String stateOfOrigin;
    @NotBlank
    private String localGovernment;
}
