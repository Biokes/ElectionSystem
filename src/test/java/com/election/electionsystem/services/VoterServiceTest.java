package com.election.electionsystem.services;

import com.election.electionsystem.dtos.requests.*;
import com.election.electionsystem.dtos.response.UpdateProfileResponse;
import com.election.electionsystem.dtos.response.VoterResponse;
import com.election.electionsystem.data.models.Voter;
import com.election.electionsystem.services.abstractClasses.VoterService;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static com.election.electionsystem.data.enums.RegisterationStatus.APPROVED;
import static com.election.electionsystem.data.enums.RegisterationStatus.SUSPENDED;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
public class VoterServiceTest {
    @Autowired
    private VoterService voterService;
    @Test
    void testVoterCanRegister(){
        VoterRequest voterRequest = VoterRequest.builder()
                .firstname("firstname").lastname("lastname").DOB(LocalDate.parse("1989-12-19"))
                .email("user@example.com ")
                .password("Password12,.")
                .address(AddressRequest.builder().houseNumber("12")
                .State("lagos").area("meiran").street("lagos").build())
                .infoRequest(ContactInfoRequest.builder().localGovernment("alimosho")
                .phoneNumber("129023901").stateOfOrigin("Lagos").build()).build();
        VoterResponse response = voterService.registerVoter(voterRequest);
        assertEquals(APPROVED,response.getStatus());
        assertThat(response.getId()).isEqualTo(1L);
        assertNotNull(response.getEmail());
    }
    @Test
    void testVoterCanBeSuspended(){
        SuspendVoterRequest suspendVoter = SuspendVoterRequest.builder()
                .voterId(1L).build();
        voterService.suspendVoter(suspendVoter);
        assertEquals(SUSPENDED,voterService.findVoterById(1L).getRegisterationStatus());
    }
    @Test
    void testVoterProfileCanBeUpdated(){
            UpdateProfileRequest updateRequest = UpdateProfileRequest.builder().id(1L)
                                                 .lastname("new lastName").oldPassword("Password128,")
                                                 .password("Password128,.").build();
            voterService.updateVoterProfile(updateRequest);
            Voter voter = voterService.findVoterById(1L);
            assertTrue("new lastName".equalsIgnoreCase(voter.getLastname()));
            assertThat(voter.getPassword()).isNotBlank();
            assertEquals(APPROVED,voter.getRegisterationStatus());
    }
    @Test
    void testVoterCanBeApprovedAfterSuspension(){
        UpdateProfileResponse updateResponse = voterService.approveVoter(1L);
        assertEquals("user@example.com ",updateResponse.getEmail());
        assertEquals(APPROVED,updateResponse.getStatus());
    }


}
