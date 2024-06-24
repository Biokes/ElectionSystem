package com.election.electionsystem;

import com.election.electionsystem.dtos.requests.AddressRequest;
import com.election.electionsystem.dtos.requests.ContactInfoRequest;
import com.election.electionsystem.dtos.requests.VoterRequest;
import com.election.electionsystem.dtos.response.VoterResponse;
import com.election.electionsystem.services.abstractClasses.VoterService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static com.election.electionsystem.models.RegisterationStatus.APPROVED;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class VoterServiceTest {
    @Autowired
    private VoterService voterService;
    @Test
    void testVoterCanRegister(){
        VoterRequest voterRequest = VoterRequest.builder()
                .firstname("firstname").lastname("lastname").DOB(LocalDate.parse("1989-12-19"))
                .email("user@example.com ")
                .address(AddressRequest.builder().houseNumber("12")
                .State("lagos").area("meiran").street("lagos").build())
                .infoRequest(ContactInfoRequest.builder().localGovernment("alimosho")
                .phoneNumber("129023901").stateOfOrigin("Lagos").build()).build();
        VoterResponse response = voterService.registerVoter(voterRequest);
        assertEquals(APPROVED,response.getStatus());
        assertThat(response.getId()).isEqualTo(1L);
        assertNotNull(response.getEmail());
    }
}
