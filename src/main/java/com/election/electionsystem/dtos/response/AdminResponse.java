package com.election.electionsystem.dtos.response;

import com.election.electionsystem.data.enums.AdminRole;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class AdminResponse {
    private Long id;
    private String email;
    private AdminRole role;
}
