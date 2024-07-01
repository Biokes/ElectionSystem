package com.election.electionsystem.dtos.requests;

import com.election.electionsystem.data.enums.AdminRole;
import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AdminLoginRequest {
    private String email;
    private String password;
    private AdminRole role;
}
