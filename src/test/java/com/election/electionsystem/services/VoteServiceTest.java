package com.election.electionsystem.services;

import com.election.electionsystem.dtos.requests.VoteRequest;
import com.election.electionsystem.dtos.response.VoteResponse;
import com.election.electionsystem.services.abstractClasses.VoteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.election.electionsystem.data.enums.Status.NOT_STARTED;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class VoteServiceTest {
    @Autowired
    private VoteService voteService;
    @Test
    void testVoterCanVote(){
        VoteRequest voteRequest = VoteRequest.builder().voterId(1L).candidateId(1L).build();
        VoteResponse response = voteService.castVote(voteRequest);
        assertEquals(NOT_STARTED,response.getElectionStatus());
        assertNotNull(response.getVoterId());
        assertNotNull(response.getElectionId());
    }
}
