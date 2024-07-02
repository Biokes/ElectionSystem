package com.election.electionsystem.controllers;

import com.election.electionsystem.dtos.requests.UpdateProfileRequest;
import com.election.electionsystem.dtos.requests.VoterRequest;
import com.election.electionsystem.services.abstractClasses.ElectionService;
import com.election.electionsystem.services.abstractClasses.VoterService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@CrossOrigin("*")
@AllArgsConstructor
@RequestMapping("/api/v1/user/")
public class VoterController {
    private VoterService voterService;
    private ElectionService electionService;
    @PostMapping("/register?vote")
    public ResponseEntity<?> registerAsVoter(@RequestParam VoterRequest voterRequest){
        return ResponseEntity.status(CREATED).body(voterService.registerVoter(voterRequest));
    }
    @PatchMapping("update/profile/")
    public ResponseEntity<?> updateProfile(@RequestBody UpdateProfileRequest updateProfileRequest){
        return ResponseEntity.status(OK).body(voterService.updateVoterProfile(updateProfileRequest));
    }
    @GetMapping("view/result/{id}")
    public ResponseEntity<?> viewElectionResult(@PathVariable Long id){
        return ResponseEntity.status(OK).body(electionService.getElectionWinner(id));
    }

}
