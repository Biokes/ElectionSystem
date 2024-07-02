package com.election.electionsystem.services.implementations;

import com.election.electionsystem.data.enums.RegisterationStatus;
import com.election.electionsystem.data.models.Address;
import com.election.electionsystem.data.models.Admin;
import com.election.electionsystem.data.models.Voter;
import com.election.electionsystem.dtos.requests.*;
import com.election.electionsystem.dtos.response.AdminResponse;
import com.election.electionsystem.dtos.response.RegisterCandidateResponse;
import com.election.electionsystem.dtos.response.ScheduleResponse;
import com.election.electionsystem.dtos.response.VoterResponse;
import com.election.electionsystem.exceptions.ElectionException;
import com.election.electionsystem.repo.AdminRepository;
import com.election.electionsystem.repo.ElectionRepository;
import com.election.electionsystem.repo.VoterRepository;
import com.election.electionsystem.services.abstractClasses.*;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.election.electionsystem.exceptions.ExceptionMessages.INVALID_DETAILS;

@Service
@Slf4j
public class ElectionAdminService implements AdminService {
    private final ElectionService electionService;
    private final CandidateService candidateService;
    private final VoterService voterService;
    private final ModelMapper modelmapper;
    private final PasswordEncoder encoder;
    private AdminRepository adminRepository;
    @Autowired
    public ElectionAdminService(ElectionService electionService,CandidateService candidateService,
                                AdminRepository adminRepository, VoterService voterService,
                                ModelMapper modelMapper, PasswordEncoder encoder){
        this.candidateService = candidateService;
        this.voterService = voterService;
        this.electionService = electionService;
        this.modelmapper = modelMapper;
        this.adminRepository= adminRepository;
        this.encoder = encoder;
        }
    @Override
    public RegisterCandidateResponse registerCandidate(CandidateRegisterRequest candidateToBeRegistered) {
        return candidateService.registerCandidate(candidateToBeRegistered);
    }

    @Override
    public AdminResponse registerAsAdmin(AdminRegister request) {
//        Voter voter = modelmapper.map(request,Voter.class);
//        voterService.save(voter);
//        Admin admin = new Admin();
//        admin.setVoter(voter);
//        admin.setRole(request.getRole());
//        admin = adminRepository.save(admin);
//        return modelmapper.map(admin, AdminResponse.class);
        Admin admin = modelmapper.map(request, Admin.class);
        admin.getVoter().getInfoRequest().setAddress(modelmapper
                .map(request.getVoterRequest().getAddress(), Address.class));
        admin.getVoter().setRegisterationStatus(RegisterationStatus.APPROVED);
        admin.setRole(request.getRole());
        admin.getVoter().setPassword(encoder.encode(admin.getVoter().getPassword()));
        admin=adminRepository.save(admin);
        AdminResponse response =  modelmapper.map(admin, AdminResponse.class);
        response.setEmail(admin.getVoter().getEmail());
                log.info("Admin ------------------------->{}",response);
        return response;
    }

    @Override
    public VoterResponse registerVoter(VoterRequest voterRequest) {
        return voterService.registerVoter(voterRequest);
    }

    @Override
    public ScheduleResponse scheduleElection(ElectionRequest request) {
        return electionService.scheduleElection(request);
    }

    @Override
    public ScheduleResponse rescheduleElection(RescheduleRequest request) {
        return electionService.rescheduleElection(request);
    }

    @Override
    public Admin getAdminByUsername(String email) {
        return (Admin) adminRepository.findAll().stream()
                .filter(admin -> admin.getVoter().getEmail().equalsIgnoreCase(email));
    }
}
