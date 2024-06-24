package com.election.electionsystem.services.implementations;

import com.election.electionsystem.repo.VoterRepository;
import com.election.electionsystem.requests.VoterRequest;
import com.election.electionsystem.response.VoterResponse;
import com.election.electionsystem.services.VoterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ElectionVoterService implements VoterService {
    @Autowired
    private VoterRepository voterRepository;
    @Override
    public VoterResponse registerVoter(VoterRequest voterRequest) {
        return null;
    }
}
