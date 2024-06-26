package com.election.electionsystem.dtos.response;

import com.election.electionsystem.data.enums.RegisterationStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class UpdateProfileResponse {
    private RegisterationStatus status;
    private Long id;
    private String email;

}
