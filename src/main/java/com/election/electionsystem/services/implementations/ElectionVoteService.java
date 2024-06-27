package com.election.electionsystem.services.implementations;
import com.election.electionsystem.data.models.Candidate;
import com.election.electionsystem.data.models.Election;
import com.election.electionsystem.data.models.Vote;
import com.election.electionsystem.data.models.Voter;
import com.election.electionsystem.dtos.requests.VoteRequest;
import com.election.electionsystem.dtos.response.VoteResponse;
import com.election.electionsystem.exceptions.ElectionException;
import com.election.electionsystem.repo.CandidateRepo;
import com.election.electionsystem.repo.ElectionRepository;
import com.election.electionsystem.repo.VoteRepository;
import com.election.electionsystem.repo.VoterRepository;
import com.election.electionsystem.services.abstractClasses.CandidateService;
import com.election.electionsystem.services.abstractClasses.ElectionService;
import com.election.electionsystem.services.abstractClasses.VoteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.election.electionsystem.exceptions.ExceptionMessages.ALREADY_VOTED;
import static com.election.electionsystem.exceptions.ExceptionMessages.INVALID_DETAILS;
@Service
public class ElectionVoteService implements VoteService {
    private final VoteRepository repo;
    private final VoterRepository voterRepository;
    private final PasswordEncoder passwordEncoder;
    private final CandidateRepo candidateRepo;
    private final ModelMapper modelMapper;
    private final ElectionService electionService;
    @Autowired
    public ElectionVoteService(VoteRepository repo, VoterRepository voterRepo, PasswordEncoder encoder,
                               ElectionService electionService,CandidateRepo candidateRepo,ModelMapper modelMapper)
    {
        this.repo = repo;
        voterRepository = voterRepo;
        this.passwordEncoder = encoder;
        this.candidateRepo = candidateRepo;
        this.modelMapper = modelMapper;
        this.electionService= electionService;
    }
    @Override
    public VoteResponse castVote(VoteRequest voteRequest){
       Object[] data =  validate(voteRequest);
        Vote vote = Vote.builder().candidate((Candidate)data[0]).voter((Voter)data[1]).build();
        Long electionId = ((Candidate) data[0]).getElection().getId();
        Election election = electionService.findElectionById(electionId);
        ((Candidate) data[0]).getVotes().add(vote);
        electionService.save(election);
        return modelMapper.map(vote, VoteResponse.class);
    }
    private Object[] validate(VoteRequest voteRequest){
        Optional<Vote> vote = repo.findVoteByCandidate_IdAndVoter(voteRequest.getVoterId(), voteRequest.getCandidateId());
        if(vote.isPresent())throw new ElectionException(ALREADY_VOTED.getMessage());
        Voter voter =voterRepository.findById(voteRequest.getVoterId()).orElseThrow(()->new ElectionException(INVALID_DETAILS.getMessage()));
        if(!voter.getEmail().equals(voteRequest.getEmail())|| !passwordEncoder.matches(voteRequest.getPassword(), voter.getPassword()))
            throw new ElectionException(INVALID_DETAILS.getMessage());
        Candidate candidate =candidateRepo.findById(voteRequest.getCandidateId()).orElseThrow(()->new ElectionException(INVALID_DETAILS.getMessage()));
        return new Object[]{candidate, voter};
    }
}
