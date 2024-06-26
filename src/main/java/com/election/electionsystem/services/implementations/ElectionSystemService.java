package com.election.electionsystem.services.implementations;

import com.election.electionsystem.data.models.Election;
import com.election.electionsystem.dtos.requests.ElectionRequest;
import com.election.electionsystem.dtos.requests.RescheduleRequest;
import com.election.electionsystem.dtos.response.ScheduleResponse;
import com.election.electionsystem.exceptions.ElectionException;
import com.election.electionsystem.repo.ElectionRepository;
import com.election.electionsystem.services.abstractClasses.ElectionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.election.electionsystem.data.enums.Status.NOT_STARTED;
import static com.election.electionsystem.exceptions.ExceptionMessages.INVALID_NUMBER;

@Service
public class ElectionSystemService implements ElectionService {
    private final ModelMapper modelMapper;
    private final ElectionRepository electionRepository;
    @Autowired
    public ElectionSystemService(ModelMapper modelMapper, ElectionRepository electionRepo){
        this.electionRepository= electionRepo;
        this.modelMapper = modelMapper;
    }
    @Override
    public ScheduleResponse scheduleElection(ElectionRequest electionRequest) {
        Election election = modelMapper.map(electionRequest, Election.class);
        election.setStatus(NOT_STARTED);
        election = electionRepository.save(election);
        return modelMapper.map(election,ScheduleResponse.class);
    }

    @Override
    public Election findElectionById(Long id){
        return electionRepository.findById(id)
                .orElseThrow(()->new ElectionException(INVALID_NUMBER.getMessage()));
    }

    @Override
    public ScheduleResponse rescheduleElection(RescheduleRequest rescheduleRequest){
        return null;
    }
}
