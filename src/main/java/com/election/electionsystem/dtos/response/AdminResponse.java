package com.election.electionsystem.dtos.response;

import com.election.electionsystem.data.enums.AdminRole;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminResponse {
    private Long id;
    private String email;
    private AdminRole role;
}
