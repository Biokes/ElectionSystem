package com.election.electionsystem.services.implementations;

import com.election.electionsystem.models.Address;
import com.election.electionsystem.models.data.ContactInformation;
import com.election.electionsystem.repo.VoterRepository;
import com.election.electionsystem.requests.VoterRequest;
import com.election.electionsystem.response.VoterResponse;
import com.election.electionsystem.services.abstractClasses.VoterService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.election.electionsystem.utils.Validator.validate;

@Service
public class ElectionVoterService implements VoterService {
    @Autowired
    private VoterRepository voterRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public VoterResponse registerVoter(VoterRequest voterRequest) {
        validate(voterRequest);
        Address address = modelMapper.map(voterRequest.getAddress(),Address.class);
        ContactInformation contactInfo = modelMapper.map(voterRequest.getInfoRequest(),ContactInformation.class);
        return null;
    }
}
