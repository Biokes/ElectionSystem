package com.election.electionsystem.services.abstractClasses;

import com.election.electionsystem.data.models.Admin;
import com.election.electionsystem.dtos.requests.*;
import com.election.electionsystem.dtos.response.AdminResponse;
import com.election.electionsystem.dtos.response.RegisterCandidateResponse;
import com.election.electionsystem.dtos.response.ScheduleResponse;
import com.election.electionsystem.dtos.response.VoterResponse;
import org.springframework.stereotype.Service;

@Service
public interface AdminService  {
    RegisterCandidateResponse registerCandidate(CandidateRegisterRequest candidateToBeRegistered);
    AdminResponse registerAsAdmin(AdminRegister request);
    VoterResponse registerVoter(VoterRequest voterRequest);
     ScheduleResponse scheduleElection(ElectionRequest request);
    ScheduleResponse rescheduleElection(RescheduleRequest request);

    Admin getAdminByUsername(String email);
}
