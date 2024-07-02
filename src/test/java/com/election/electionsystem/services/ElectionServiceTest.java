package com.election.electionsystem.services;

import com.election.electionsystem.data.models.Election;
import com.election.electionsystem.dtos.requests.ElectionRequest;
import com.election.electionsystem.dtos.requests.RescheduleRequest;
import com.election.electionsystem.dtos.requests.Long;
import com.election.electionsystem.dtos.response.ScheduleResponse;
import com.election.electionsystem.dtos.response.ViewElectionResponse;
import com.election.electionsystem.services.abstractClasses.ElectionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static com.election.electionsystem.data.enums.Office.PRESIDENCY;
import static com.election.electionsystem.data.enums.Status.NOT_STARTED;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ElectionServiceTest {
    @Autowired
    private ElectionService electionService;
    @Test
    void testElectionCanBeScheduled(){
        ElectionRequest request = ElectionRequest.builder().electionOffice(PRESIDENCY)
                .startDate(LocalDateTime.parse("2024-12-03T10:00:00"))
                .endTime(LocalDateTime.parse("2024-12-03T18:00:00")).description("Elite election").build();
        ScheduleResponse response = electionService.scheduleElection(request);
        assertEquals(NOT_STARTED,response.getStatus());
        assertNotNull(response.getDescription());
        assertThat(response.getId()).isEqualTo(  1L);
    }
    @Test
        void testElectionCanBeRescheduled(){
            ElectionRequest request = ElectionRequest.builder().electionOffice(PRESIDENCY)
                    .startDate(LocalDateTime.parse("2024-12-03T10:00:00"))
                    .endTime(LocalDateTime.parse("2024-12-03T18:00:00")).description("Elite election12").build();
            ScheduleResponse response = electionService.scheduleElection(request);
            assertEquals(NOT_STARTED,response.getStatus());
            assertNotNull(response.getDescription());
            assertThat(response.getId()).isEqualTo(  1L);
            RescheduleRequest rescheduleRequest = RescheduleRequest.builder()
                    .id(response.getId())
                    .startDate(LocalDateTime.parse("2024-10-09T05:00:00"))
                    .endDate(LocalDateTime.parse("2024-10-10T09:00:00")).build();
            response =  electionService.rescheduleElection(rescheduleRequest);
            Election election = electionService.findElectionById(response.getId());
            assertEquals(LocalDateTime.parse("2024-10-09T05:00:00"),election.getStartDate());
            assertEquals(LocalDateTime.parse("2024-10-10T09:00:00"), election.getEndDate());
        }
        @Test
        void testElectionResultCanBeViewed(){
            ElectionRequest request = ElectionRequest.builder().electionOffice(PRESIDENCY)
                    .startDate(LocalDateTime.parse("2024-12-03T10:00:00"))
                    .endTime(LocalDateTime.parse("2024-12-03T18:00:00")).description("Elite election12").build();
           ScheduleResponse response = electionService.scheduleElection(request);
            Long viewRequest = Long.builder()
                                              .electionId(response.getId()).build();
            ViewElectionResponse result  = electionService.getElectionWinner(viewRequest);
            assertEquals(0, result.getNumberOfVotes());
            assertEquals(NOT_STARTED,result.getStatus());
            assertEquals(LocalDateTime.parse("2024-12-03T10:00:00"),result.getStartDate());
            assertEquals(LocalDateTime.parse("2024-12-03T18:00:00"),result.getEndDate());
        }
}
