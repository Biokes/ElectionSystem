package com.election.electionsystem.services.implementations;

import com.election.electionsystem.dtos.requests.ElectionRequest;
import com.election.electionsystem.dtos.response.ScheduleResponse;
import com.election.electionsystem.services.abstractClasses.ElectionService;
import org.springframework.stereotype.Service;

@Service
public class ElectionSystemService implements ElectionService {
    @Override
    public ScheduleResponse scheduleElection(ElectionRequest electionRequest) {
        return null;
    }
}
