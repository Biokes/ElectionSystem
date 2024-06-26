package com.election.electionsystem.dtos.response;

import com.election.electionsystem.data.enums.Status;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleResponse {
    private Status status;
    private String description;
    private Long id;
}
