package com.election.electionsystem.services;

import com.election.electionsystem.dtos.requests.AddressRequest;
import com.election.electionsystem.dtos.requests.AdminRegister;
import com.election.electionsystem.dtos.requests.ContactInfoRequest;
import com.election.electionsystem.dtos.requests.VoterRequest;
import com.election.electionsystem.dtos.response.AdminResponse;
import com.election.electionsystem.services.abstractClasses.AdminService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class AdminServiceTest {
    @Autowired
    private AdminService adminService;
    @Test
    void testAdminCanRegisterAsAdmin(){
        AdminRegister admin = AdminRegister.builder().
                voterRequest(VoterRequest.builder()
                        .firstname("firstname").lastname("lastname")
                        .DOB(LocalDate.parse("1989-12-19"))
                        .email("use12r@example.com ")
                        .password("Password12,.")
                        .address(AddressRequest.builder().houseNumber("13")
                                .State("lagos").area("meiran").street("lagos").build())
                        .infoRequest(ContactInfoRequest.builder().localGovernment("alimosho")
                                .phoneNumber("1290239012").stateOfOrigin("Lagos")
                                .build())
                        .build())
                .build();
       AdminResponse response = adminService.registerAsAdmin(admin);
       assertThat(response.getId()).isNotNull();
    }

}
