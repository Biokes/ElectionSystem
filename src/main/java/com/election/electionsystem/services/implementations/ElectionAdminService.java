package com.election.electionsystem.services.implementations;

import com.election.electionsystem.dtos.requests.*;
import com.election.electionsystem.dtos.response.AdminResponse;
import com.election.electionsystem.dtos.response.RegisterCandidateResponse;
import com.election.electionsystem.dtos.response.ScheduleResponse;
import com.election.electionsystem.dtos.response.VoterResponse;
import com.election.electionsystem.services.abstractClasses.AdminService;
import org.springframework.stereotype.Service;

@Service
public class ElectionAdminService implements AdminService {
    @Override
    public RegisterCandidateResponse registerCandidate(CandidateRegisterRequest candidateToBeRegistered) {
        return null;
    }

    @Override
    public AdminResponse registerAsAdmin(AdminRegister request) {
        return null;
    }

    @Override
    public VoterResponse registerVoter(VoterRequest voterRequest) {
        return null;
    }

    @Override
    public ScheduleResponse scheduleElection(ElectionRequest request) {
        return null;
    }

    @Override
    public ScheduleResponse rescheduleElection(RescheduleRequest request) {
        return null;
    }
}
