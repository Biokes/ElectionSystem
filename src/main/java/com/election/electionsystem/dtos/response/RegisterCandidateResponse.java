package com.election.electionsystem.dtos.response;

import com.election.electionsystem.data.enums.Office;
import com.election.electionsystem.data.enums.Status;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterCandidateResponse {
    private Status status;
    private Long id;
    private Office office;
}
