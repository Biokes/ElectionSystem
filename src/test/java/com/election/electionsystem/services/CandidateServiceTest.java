package com.election.electionsystem.services;

import com.election.electionsystem.dtos.requests.CandidateRegisterRequest;
import com.election.electionsystem.services.abstractClasses.CandidateService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CandidateServiceTest {

    @Autowired
    private CandidateService candidateService;
    @Test
    void testCandidatesCanRegister(){
        CandidateRegisterRequest registerRequest;
    }
}
