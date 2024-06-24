package com.election.electionsystem.dtos.response;

import com.election.electionsystem.models.RegisterationStatus;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateProfileResponse {
    private RegisterationStatus status;
    private Long id;
    private String email;

}
