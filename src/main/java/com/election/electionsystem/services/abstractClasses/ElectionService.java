package com.election.electionsystem.services.abstractClasses;

import com.election.electionsystem.data.models.Election;
import com.election.electionsystem.dtos.requests.ElectionRequest;
import com.election.electionsystem.dtos.response.ElectionResponse;

public interface ElectionService {
    ElectionResponse scheduleElection(ElectionRequest electionRequest);
//    Election findElectionByCandidateId(Long candidateId);
//    Election addElection(Election election);

//    RescheduleElectionResponse rescheduleElection(RescheduleElectionRequest request);
//
//    ViewElectionResultResponse viewElectionResult(ViewElectionResultRequest viewResult);
}
