package com.election.electionsystem.response;

import com.election.electionsystem.models.RegisterationStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class VoterResponse {
    private RegisterationStatus status;
    private Long id;
    private String username;
}
