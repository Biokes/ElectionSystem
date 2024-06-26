package com.election.electionsystem.services.abstractClasses;

import com.election.electionsystem.dtos.requests.CandidateRegisterRequest;
import com.election.electionsystem.dtos.response.RegisterCandidateResponse;
import org.springframework.stereotype.Service;

@Service
public interface CandidateService {
    RegisterCandidateResponse registerCandidate(CandidateRegisterRequest request);
}
