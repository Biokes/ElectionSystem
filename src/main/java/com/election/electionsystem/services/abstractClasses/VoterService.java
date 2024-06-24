package com.election.electionsystem.services.abstractClasses;


import com.election.electionsystem.dtos.requests.VoterRequest;
import com.election.electionsystem.dtos.response.VoterResponse;
import org.springframework.stereotype.Service;

@Service
public interface VoterService {
    VoterResponse registerVoter(VoterRequest voterRequest);
}
