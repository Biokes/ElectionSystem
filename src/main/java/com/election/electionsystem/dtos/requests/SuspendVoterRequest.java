package com.election.electionsystem.dtos.requests;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class SuspendVoterRequest {
    private Long voterId;
}