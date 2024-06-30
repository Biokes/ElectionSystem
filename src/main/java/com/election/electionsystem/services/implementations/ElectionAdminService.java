package com.election.electionsystem.services.implementations;

import com.election.electionsystem.dtos.requests.*;
import com.election.electionsystem.dtos.response.AdminResponse;
import com.election.electionsystem.dtos.response.RegisterCandidateResponse;
import com.election.electionsystem.dtos.response.ScheduleResponse;
import com.election.electionsystem.dtos.response.VoterResponse;
import com.election.electionsystem.repo.ElectionRepository;
import com.election.electionsystem.repo.VoterRepository;
import com.election.electionsystem.services.abstractClasses.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ElectionAdminService implements AdminService {
    private ElectionService electionService;
    private CandidateService candidateService;
    private VoterService voterService;
    @Autowired
    public ElectionAdminService(ElectionService electionService,CandidateService candidateService,
                                VoterService voterService, VoteService voteService){
        this.candidateService = candidateService;
        this.voterService = voterService;
        this.electionService = electionService;
        }
    @Override
    public RegisterCandidateResponse registerCandidate(CandidateRegisterRequest candidateToBeRegistered) {
        return candidateService.registerCandidate(candidateToBeRegistered);
    }

    @Override
    public AdminResponse registerAsAdmin(AdminRegister request) {
        return null;
    }

    @Override
    public VoterResponse registerVoter(VoterRequest voterRequest) {
        return voterService.registerVoter(voterRequest);
    }

    @Override
    public ScheduleResponse scheduleElection(ElectionRequest request) {
        return electionService.scheduleElection(request);
    }

    @Override
    public ScheduleResponse rescheduleElection(RescheduleRequest request) {
        return electionService.rescheduleElection(request);
    }
}
