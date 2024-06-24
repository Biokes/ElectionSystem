package com.election.electionsystem;

import com.election.electionsystem.requests.AddressRequest;
import com.election.electionsystem.requests.ContactInfoRequest;
import com.election.electionsystem.requests.VoterRequest;
import com.election.electionsystem.response.VoterResponse;
import com.election.electionsystem.services.VoterService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static com.election.electionsystem.models.RegisterationStatus.PENDING;
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
                .address(AddressRequest.builder().houseNumber("12")
                .State("lagos").area("meiran").street("lagos").build())
                .infoRequest(ContactInfoRequest.builder().localGovernment("alimosho")
                .phoneNumber("129023901").stateOfOrigin("Lagos").build()).build();
        VoterResponse response = voterService.registerVoter(voterRequest);
        assertEquals(PENDING,response.getStatus());
        assertThat(response.getId()).isNotEqualTo(0);
        assertNotNull(response.getUsername());
    }
}
