package com.election.electionsystem.controllers;

import com.election.electionsystem.dtos.requests.VoterRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest
public class ControllerTest{
    @Autowired
    private MockMvc mockMvc;
    @Test
    void testVoterCanRegister(){
        try {
            ObjectMapper mapper = new ObjectMapper();
            VoterRequest voterRequest = VoterRequest.builder().build();
            mockMvc.perform(post("/api/v1/register-voter")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(mapper.writeValueAsBytes(voterRequest)))
                    .andExpect(status().isCreated());
        }catch(Exception error){}
    }

}
