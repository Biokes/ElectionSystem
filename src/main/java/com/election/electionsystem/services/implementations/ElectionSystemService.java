package com.election.electionsystem.services.implementations;

import com.election.electionsystem.data.models.Election;
import com.election.electionsystem.data.models.Voter;
import com.election.electionsystem.dtos.requests.ElectionRequest;
import com.election.electionsystem.dtos.requests.RescheduleRequest;
import com.election.electionsystem.dtos.response.ScheduleResponse;
import com.election.electionsystem.dtos.response.ViewElectionResponse;
import com.election.electionsystem.exceptions.ElectionException;
import com.election.electionsystem.repo.ElectionRepository;
import com.election.electionsystem.services.abstractClasses.ElectionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.stream.IntStream;

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
    public Election findElectionById(java.lang.Long id){
        return electionRepository.findById(id)
                .orElseThrow(()->new ElectionException(INVALID_NUMBER.getMessage()));
    }
    @Override
    public ScheduleResponse rescheduleElection(RescheduleRequest rescheduleRequest){
        Election election = findElectionById(rescheduleRequest.getId());
        election.setStartDate(rescheduleRequest.getStartDate());
        election.setEndDate(rescheduleRequest.getEndDate());
        return modelMapper.map(electionRepository.save(election),ScheduleResponse.class);
    }

    @Override
    @Transactional
    public ViewElectionResponse getElectionWinner(Long viewRequest){
        Election election = findElectionById(viewRequest);
        ViewElectionResponse response = modelMapper.map(election,ViewElectionResponse.class);
        Voter winner = election.getCandidates().stream().max(Comparator.comparingInt(candidate->
                candidate.getVotes().size())).get().getVoter();
        response.setWinner(winner.getFirstname()+ " "+ winner.getLastname());
        response.setNumberOfVotes((long) (election.getCandidates().stream().flatMapToInt(candidate ->
                IntStream.of(candidate.getVotes().size())).max()).getAsInt());
        return response;
    }

    @Override
    public Election save(Election election) {
        return electionRepository.save(election);
    }

}
