package com.election.electionsystem.dtos.requests;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class VoteRequest {
    @NotNull
    private Long candidateId;
    @NotNull
    private Long electionId;
    @NotNull
    private Long voterId;
}
