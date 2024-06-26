package com.election.electionsystem.services;

import com.election.electionsystem.dtos.requests.VoteRequest;
import com.election.electionsystem.services.abstractClasses.VoteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class VoteServiceTest {
    @Autowired
    private VoteService voteService;
    @Test
    void testVoterCanVote(){
        VoteRequest votetRequest = VoteRequest.builder().build();
    }
}
