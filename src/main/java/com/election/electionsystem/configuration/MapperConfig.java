package com.election.electionsystem.configuration;

import com.election.electionsystem.dtos.requests.VoterRequest;
import com.election.electionsystem.dtos.response.VoterResponse;
import com.election.electionsystem.models.data.Voter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
//    @Bean
//    @Qualifier("mapResponse")
//    public ModelMapper mapVoterToResponse(){
//        TypeMap<Voter, VoterResponse> voter = modelMapper().createTypeMap(Voter.class, VoterRequest.class)
//    }
}
