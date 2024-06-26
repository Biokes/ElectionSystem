package com.election.electionsystem.dtos.response;

import com.election.electionsystem.data.enums.Status;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ScheduleResponse {
    private Status status;
    private String description;
    private Long id;
}
