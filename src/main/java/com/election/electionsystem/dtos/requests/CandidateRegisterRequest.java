package com.election.electionsystem.dtos.requests;

import com.election.electionsystem.data.enums.Office;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Builder
@Setter
@Getter
public class CandidateRegisterRequest {
    @NotNull
    private VoterRequest registerRequest;
    @NotNull
    private MultipartFile affidavit;
    @NotNull
     private Office contestedOffice;
    @NotNull
    private Long eletionId;
}
