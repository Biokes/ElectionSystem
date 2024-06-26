package com.election.electionsystem.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class ContactInfoRequest {
    @NotBlank
    @Pattern(regexp = "\\+?[0-9]{11,}$")
    private String phoneNumber;
    @NotBlank
    private String stateOfOrigin;
    @NotBlank
    private String localGovernment;
}
