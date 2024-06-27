package com.election.electionsystem.services.abstractClasses;

import com.election.electionsystem.dtos.requests.VoteRequest;
import com.election.electionsystem.dtos.response.VoteResponse;
import org.springframework.stereotype.Service;

@Service
public interface VoteService {
    VoteResponse castVote(VoteRequest voteRequest);
}
