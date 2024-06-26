package com.election.electionsystem.dtos.requests;

import lombok.Builder;

@Builder
public class CandidateRegisterRequest {
    private VoterRequest registerRequest;
}
