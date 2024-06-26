package com.election.electionsystem.services.abstractClasses;


import com.election.electionsystem.data.models.Election;
import com.election.electionsystem.dtos.requests.ElectionRequest;
import com.election.electionsystem.dtos.requests.RescheduleRequest;
import com.election.electionsystem.dtos.response.ScheduleResponse;
import org.springframework.stereotype.Service;

@Service
public interface ElectionService {
    ScheduleResponse scheduleElection(ElectionRequest electionRequest);
    Election findElectionById(Long id);

    ScheduleResponse rescheduleElection(RescheduleRequest rescheduleRequest);
//    Election findElectionByCandidateId(Long candidateId);
//    Election addElection(Election election);

//    RescheduleElectionResponse rescheduleElection(RescheduleElectionRequest request);
//
//    ViewElectionResultResponse viewElectionResult(ViewElectionResultRequest viewResult);
}
