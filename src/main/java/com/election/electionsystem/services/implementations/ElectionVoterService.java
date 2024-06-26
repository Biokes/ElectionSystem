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
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.github.fge.jsonpatch.diff.JsonDiff;
import jakarta.validation.ConstraintViolationException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.election.electionsystem.exceptions.ExceptionMessages.*;
import static com.election.electionsystem.models.RegisterationStatus.APPROVED;
import static com.election.electionsystem.models.RegisterationStatus.SUSPENDED;
import static com.election.electionsystem.utils.Validator.validate;

@Service
@AllArgsConstructor
@Slf4j
public class ElectionVoterService implements VoterService {
    private VoterRepository voterRepository;
    private ModelMapper modelMapper;
    private final ObjectMapper objectMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public VoterResponse registerVoter(VoterRequest voterRequest) {
        try {
            Address address = modelMapper.map(voterRequest.getAddress(), Address.class);
            ContactInformation contactInfo = modelMapper.map(voterRequest.getInfoRequest(), ContactInformation.class);
            Voter voter  = mapper(contactInfo, address, voterRequest);
            voter.setRegisterationStatus(APPROVED);
            voter.setPassword(passwordEncoder.encode(voterRequest.getPassword()));
            voter = voterRepository.save(voter);
            return mapVoter(voter);
        }
        catch(ConstraintViolationException error){
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
    public UpdateProfileResponse updateVoterProfile(UpdateProfileRequest updateRequest){
        try {
            validate(updateRequest);
            Voter voter = findVoterById(updateRequest.getId());
            JsonPatch jsonPatch = createJsonPatchFromRequest(updateRequest);
            JsonNode voterNode = objectMapper.convertValue(voter, JsonNode.class);
            voterNode = jsonPatch.apply(voterNode);
            voter = voterRepository.save(objectMapper.convertValue(voterNode, Voter.class));
            return modelMapper.map(voter, UpdateProfileResponse.class);
            }
        catch(JsonPatchException | JsonProcessingException errors){
                throw new ElectionException(SOMETHING_WENT_WRONG.getMessage());
        }
    }
    private void validate(UpdateProfileRequest request){
        if(request.getOldPassword()!= null && passwordEncoder.matches(request.getOldPassword(),findVoterById(request.getId()).getPassword()))
                throw new ElectionException(INVALID_DETAILS.getMessage());
    }
    @Override
    public UpdateProfileResponse approveVoter(Long voterId) {
        Voter voter = findVoterById(voterId);
        voter.setRegisterationStatus(APPROVED);
        return modelMapper.map(voterRepository.save(voter),UpdateProfileResponse.class);
    }
     private JsonPatch createJsonPatchFromRequest(UpdateProfileRequest updateRequest) throws JsonProcessingException {
        JsonNode updatedNode = objectMapper.convertValue(updateRequest, JsonNode.class);
        List<String> operations = new ArrayList<>();
        updatedNode.fields().forEachRemaining(entry ->{
            if(!entry.getValue().isNull()){
                operations.add("{\"op\": \"replace\", \"path\": \"/" + entry.getKey() + "\", \"value\": " + entry.getValue().toString() + "}");
            }
        });
        return objectMapper.readValue("[" + String.join(",", operations) + "]", JsonPatch.class);
    }
    private VoterResponse mapVoter(Voter voter){
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
