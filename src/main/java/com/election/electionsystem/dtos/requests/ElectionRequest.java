package com.election.electionsystem.dtos.requests;

import com.election.electionsystem.annotation.annotation.AfterDateValidator;
import com.election.electionsystem.data.enums.Office;
import com.election.electionsystem.data.enums.Status;
import jakarta.validation.constraints.Future;
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
    @Future
    private LocalDateTime startDate;
    @AfterDateValidator(eventStartStart = "startDate")
    private LocalDateTime endTime;
}
