package com.election.electionsystem.dtos.response;

import com.election.electionsystem.data.enums.Status;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VoteResponse {
    private Long voterId;
    private Long electionId;
    @JsonProperty("status")
    private Status electionStatus;
}
