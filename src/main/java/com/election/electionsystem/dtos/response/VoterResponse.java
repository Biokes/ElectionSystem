package com.election.electionsystem.dtos.response;

import com.election.electionsystem.data.enums.RegisterationStatus;
import lombok.*;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VoterResponse {
    private RegisterationStatus status;
    private Long id;
    private String email;
}
