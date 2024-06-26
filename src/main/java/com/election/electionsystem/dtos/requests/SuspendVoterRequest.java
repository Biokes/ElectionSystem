package com.election.electionsystem.dtos.requests;

import com.election.electionsystem.models.RegisterationStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class SuspendVoterRequest {
    private Long voterId;
    private RegisterationStatus status;
}
