package com.election.electionsystem.services;

import com.election.electionsystem.dtos.requests.CandidateRegisterRequest;
import com.election.electionsystem.dtos.requests.ContactInfoRequest;
import com.election.electionsystem.dtos.requests.VoterRequest;
import com.election.electionsystem.dtos.response.RegisterCandidateResponse;
import com.election.electionsystem.services.abstractClasses.CandidateService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

import static com.election.electionsystem.data.enums.Office.PRESIDENCY;
import static com.election.electionsystem.data.enums.Status.REGISTERED;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CandidateServiceTest {

    @Autowired
    private CandidateService candidateService;
    @Test
    void testCandidatesCanRegister(){
        Path path = Paths.get("C:\\Users\\DELL\\Videos\\Facebook_1688966238069.mp4");
        try(InputStream inputStream = Files.newInputStream(path)) {
            MultipartFile file = new MockMultipartFile("user affidavit",inputStream);
            CandidateRegisterRequest registerRequest = CandidateRegisterRequest.builder().affidavit(file)
                    .registerRequest(VoterRequest.builder().firstname("sam").office(PRESIDENCY)
                            .lastname("sammy").password("Password1234;.").DOB(LocalDate.parse("1975-12-10"))
                            .email("email12@email.com").infoRequest(ContactInfoRequest.builder()
                                    .phoneNumber("90890987659").build()).build()).build();
            RegisterCandidateResponse response = candidateService.registerCandidate(registerRequest);
            assertNotNull(response);
            assertEquals(REGISTERED, response.getStatus());
            assertEquals(PRESIDENCY, response.getOffice());
        }catch(IOException error){
            assertNull(error);
        }
    }
}
