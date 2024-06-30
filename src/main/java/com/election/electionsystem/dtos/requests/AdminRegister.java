package com.election.electionsystem.dtos.requests;

import com.election.electionsystem.data.enums.AdminRole;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class AdminRegister {
    private VoterRequest voterRequest;
    @NotNull
    private AdminRole role;
}
