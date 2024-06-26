package com.election.electionsystem.dtos.requests;

import com.election.electionsystem.annotation.annotation.AfterDateValidator;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RescheduleRequest {
    private Long id;
    @NotNull
    @Future
    private LocalDateTime startDate;
    @AfterDateValidator(eventStartStart = "startDate")
    private LocalDateTime endDate;
}
