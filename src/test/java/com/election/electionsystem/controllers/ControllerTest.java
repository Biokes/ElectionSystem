package com.election.electionsystem.controllers;

import com.election.electionsystem.dtos.requests.AdminLoginRequest;
import com.election.electionsystem.dtos.requests.VoterRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest
public class ControllerTest{
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Test
    void testAdminCanRegister(){
        try{
            AdminLoginRequest login = AdminLoginRequest.builder().email("use1r@example.com").password("Password12,.").build();
            mockMvc.perform(post("api/v1/auth/login/")
                    .content(objectMapper.writeValueAsBytes(login))
                    .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print());
        }
        catch(Exception exception){
            assertNull(exception);
        }
    }
//    @Test
//    void testAdminCanRegisterCandidate(){
//        try {
//            ObjectMapper mapper = new ObjectMapper();
//            VoterRequest voterRequest = VoterRequest.builder().build();
//            mockMvc.perform(post("/api/v1/register-voter")
//                            .contentType(MediaType.APPLICATION_JSON)
//                            .content(mapper.writeValueAsBytes(voterRequest)))
//                    .andExpect(status().isCreated());
//        }catch(Exception error){}
//    }

}
