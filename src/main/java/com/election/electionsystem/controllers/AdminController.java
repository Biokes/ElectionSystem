package com.election.electionsystem.controllers;

import com.election.electionsystem.dtos.requests.*;
import com.election.electionsystem.services.abstractClasses.AdminService;
import com.election.electionsystem.services.abstractClasses.VoterService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/admin/election")
@CrossOrigin("*")
public class AdminController{
    private final AdminService adminService;
    @PostMapping("/register")
    public ResponseEntity<?> RegisterAsAdmin(@RequestBody AdminRegister request){
        return  ResponseEntity.status(CREATED).body(adminService.registerAsAdmin(request));
    }
    @PostMapping("/login")
    public ResponseEntity<?> registerCandidate(@RequestBody CandidateRegisterRequest request){
        return ResponseEntity.status(OK).body(adminService.registerCandidate(request));
    }
    @PostMapping("/register/voter")
    public ResponseEntity<?> registerVoter(@RequestBody VoterRequest voterRequest){
        return ResponseEntity.status(CREATED).body(adminService.registerVoter(voterRequest));
    }
    @PostMapping("/schedule/election")
    public ResponseEntity<?> scheduleElection(@RequestBody ElectionRequest request){
        return ResponseEntity.status(CREATED).body(adminService.scheduleElection(request));
    }
    @PutMapping("/reschedule-election")
    public ResponseEntity<?> rescheduleElection(@RequestBody RescheduleRequest request){
        return ResponseEntity.status(OK).body(adminService.rescheduleElection(request));
    }
}
