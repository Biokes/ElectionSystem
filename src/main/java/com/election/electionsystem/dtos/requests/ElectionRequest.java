package com.election.electionsystem.dtos.requests;

import com.election.electionsystem.data.enums.Office;
import com.election.electionsystem.data.enums.Status;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@Builder
public class ElectionRequest {
    private Office electionOffice;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endTime;
}
