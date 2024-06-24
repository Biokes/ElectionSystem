package com.election.electionsystem.services.implementations;

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
import org.springframework.stereotype.Service;

import static com.election.electionsystem.exceptions.ExceptionMessages.INVALID_DETAILS;
import static com.election.electionsystem.utils.Validator.validate;

@Service
@AllArgsConstructor
@Slf4j
public class ElectionVoterService implements VoterService {
    private VoterRepository voterRepository;
    private ModelMapper modelMapper;
//    @Qualifier("mapResponse")
//    private ModelMapper mapVoter;
    @Override
    public VoterResponse registerVoter(VoterRequest voterRequest) {
        try {
            validate(voterRequest);
            Address address = modelMapper.map(voterRequest.getAddress(), Address.class);
            log.info("Address ----------->>{}",address);
            ContactInformation contactInfo = modelMapper.map(voterRequest.getInfoRequest(), ContactInformation.class);
            log.info("contactInfo ----------->>{}",contactInfo);
            Voter voter = voterRepository.save(mapper(contactInfo, address, voterRequest));
            log.info("voter ----------->>{}",voter);
            VoterResponse response = modelMapper.map(voter, VoterResponse.class);
            log.info("response ----------->>{}",response);
            response.setStatus(RegisterationStatus.APPROVED);
            return response;
        }catch(ConstraintViolationException error){
            throw new ElectionException(INVALID_DETAILS.getMessage());
        }
    }
    private Voter mapper(ContactInformation contactInfo,
                         Address address, VoterRequest voterRequest) {
        Voter voter = modelMapper.map(voterRequest, Voter.class);
        contactInfo.setAddress(address);
        voter.setInfoRequest(contactInfo);
        return voter;
    }

}
