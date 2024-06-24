package com.election.electionsystem.dtos.requests;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class AddressRequest {
    @NotBlank
    private String street;
    @NotBlank
    private String area;
    @NotBlank
    private String State;
    @NotBlank
    private String houseNumber;
}
