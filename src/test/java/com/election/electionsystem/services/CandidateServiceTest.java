package com.election.electionsystem.services;

import com.election.electionsystem.dtos.requests.CandidateRegisterRequest;
import com.election.electionsystem.dtos.requests.ContactInfoRequest;
import com.election.electionsystem.dtos.requests.VoterRequest;
import com.election.electionsystem.services.abstractClasses.CandidateService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class CandidateServiceTest {

    @Autowired
    private CandidateService candidateService;
    @Test
    void testCandidatesCanRegister(){
        CandidateRegisterRequest registerRequest = CandidateRegisterRequest.builder()
                .registerRequest(VoterRequest.builder().firstname("sam")
                        .lastname("sammy").password("Password1234;.").DOB(LocalDate.parse("1975-12-10"))
                        .email("email@email.com").infoRequest(ContactInfoRequest.builder()
                        .phoneNumber("90890987659").build()).build()).build();
    }
}
