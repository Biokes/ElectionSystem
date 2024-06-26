package com.election.electionsystem.services.abstractClasses;


import com.election.electionsystem.dtos.requests.SuspendVoterRequest;
import com.election.electionsystem.dtos.requests.UpdateProfileRequest;
import com.election.electionsystem.dtos.requests.VoterRequest;
import com.election.electionsystem.dtos.response.UpdateProfileResponse;
import com.election.electionsystem.dtos.response.VoterResponse;
import com.election.electionsystem.data.models.Voter;
import org.springframework.stereotype.Service;

@Service
public interface VoterService {
    VoterResponse registerVoter(VoterRequest voterRequest);
    Voter findVoterById(long id);
    void suspendVoter(SuspendVoterRequest suspendVoter);
    UpdateProfileResponse updateVoterProfile(UpdateProfileRequest updateRequest);
    UpdateProfileResponse approveVoter(Long voterId);
}
