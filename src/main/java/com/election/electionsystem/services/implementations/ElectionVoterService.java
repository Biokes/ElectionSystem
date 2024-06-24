package com.election.electionsystem.services.implementations;

import com.election.electionsystem.dtos.requests.SuspendVoterRequest;
import com.election.electionsystem.dtos.requests.UpdateProfileRequest;
import com.election.electionsystem.dtos.response.UpdateProfileResponse;
import com.election.electionsystem.exceptions.ElectionException;
import com.election.electionsystem.models.RegisterationStatus;
import com.election.electionsystem.models.data.Address;
import com.election.electionsystem.models.data.ContactInformation;
import com.election.electionsystem.models.data.Voter;
import com.election.electionsystem.repo.VoterRepository;
import com.election.electionsystem.dtos.requests.VoterRequest;
import com.election.electionsystem.dtos.response.VoterResponse;
import com.election.electionsystem.services.abstractClasses.VoterService;
import jakarta.validation.ConstraintViolationException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.election.electionsystem.exceptions.ExceptionMessages.INVALID_DETAILS;
import static com.election.electionsystem.exceptions.ExceptionMessages.VOTER_NOT_FOUND;
import static com.election.electionsystem.models.RegisterationStatus.APPROVED;
import static com.election.electionsystem.models.RegisterationStatus.SUSPENDED;
import static com.election.electionsystem.utils.Validator.validate;

@Service
@AllArgsConstructor
@Slf4j
public class ElectionVoterService implements VoterService {
    private VoterRepository voterRepository;
    private ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public VoterResponse registerVoter(VoterRequest voterRequest) {
        try {
            validate(voterRequest);
            Address address = modelMapper.map(voterRequest.getAddress(), Address.class);
            ContactInformation contactInfo = modelMapper.map(voterRequest.getInfoRequest(), ContactInformation.class);
            Voter voter  = mapper(contactInfo, address, voterRequest);
            voter.setRegisterationStatus(APPROVED);
            voter = voterRepository.save(voter);
            log.info("Voter after Saved--------->>>{}",voter);
            return mapVoter(voter);
        }catch(ConstraintViolationException error){
            throw new ElectionException(INVALID_DETAILS.getMessage());
        }
    }

    @Override
    public Voter findVoterById(long id) {
        return voterRepository.findById(id)
                .orElseThrow(()->new ElectionException(VOTER_NOT_FOUND.getMessage()));
    }

    @Override
    public void suspendVoter(SuspendVoterRequest suspendVoter) {
        Voter voterFound = findVoterById(suspendVoter.getVoterId());
        voterFound.setRegisterationStatus(SUSPENDED);
        voterRepository.save(voterFound);
    }

    @Override
    public UpdateProfileResponse updateVoterProfile(UpdateProfileRequest updateRequest) {
        return null;
    }

    private VoterResponse mapVoter(Voter voter) {
        return VoterResponse.builder()
                .id(voter.getId())
                .email(voter.getEmail())
                .status(voter.getRegisterationStatus())
                .build();
    }

    private Voter mapper(ContactInformation contactInfo, Address address, VoterRequest voterRequest) {
        contactInfo.setAddress(address);
        Voter voter = modelMapper.map(voterRequest, Voter.class);
        voter.setPassword(passwordEncoder.encode(voterRequest.getPassword()));
        voter.setInfoRequest(contactInfo);
        return voter;
    }

}
