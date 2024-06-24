package com.election.electionsystem.services;


import com.election.electionsystem.requests.VoterRequest;
import com.election.electionsystem.response.VoterResponse;
import org.springframework.stereotype.Service;

@Service
public interface VoterService {
    VoterResponse registerVoter(VoterRequest voterRequest);
}
