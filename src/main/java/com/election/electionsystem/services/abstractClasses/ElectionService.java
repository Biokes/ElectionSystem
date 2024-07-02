package com.election.electionsystem.services.abstractClasses;


import com.election.electionsystem.data.models.Election;
import com.election.electionsystem.dtos.requests.ElectionRequest;
import com.election.electionsystem.dtos.requests.RescheduleRequest;
import com.election.electionsystem.dtos.response.ScheduleResponse;
import com.election.electionsystem.dtos.response.ViewElectionResponse;
import org.springframework.stereotype.Service;

@Service
public interface ElectionService {
    ScheduleResponse scheduleElection(ElectionRequest electionRequest);
    Election findElectionById(java.lang.Long id);
    ScheduleResponse rescheduleElection(RescheduleRequest rescheduleRequest);
    ViewElectionResponse getElectionWinner(Long electionId);
    Election save(Election election);
}
