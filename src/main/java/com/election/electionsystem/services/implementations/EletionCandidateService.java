package com.election.electionsystem.services.implementations;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.election.electionsystem.data.models.Candidate;
import com.election.electionsystem.data.models.Voter;
import com.election.electionsystem.dtos.requests.CandidateRegisterRequest;
import com.election.electionsystem.dtos.response.RegisterCandidateResponse;
import com.election.electionsystem.exceptions.ElectionException;
import com.election.electionsystem.repo.CandidateRepo;
import com.election.electionsystem.services.abstractClasses.CandidateService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

import static com.election.electionsystem.exceptions.ExceptionMessages.SOMETHING_WENT_WRONG;

@Service
public class EletionCandidateService implements CandidateService{
    private Cloudinary cloudinary;
    private ModelMapper mapper;
    private CandidateRepo repo;
    @Autowired
    public EletionCandidateService(ModelMapper mapper,CandidateRepo repository){
        this.mapper = mapper;
        this.repo= repository;
    }
    @Override
    @Transactional
    public RegisterCandidateResponse registerCandidate(CandidateRegisterRequest request) {
        Candidate candidate = mapper.map(request,Candidate.class);
        candidate.setVoter(mapper.map(request.getRegisterRequest(), Voter.class));
        String url = uploadMedia(request.getAffidavit());
        repo.save(candidate);
        RegisterCandidateResponse response = mapper.map(candidate,RegisterCandidateResponse.class);
        return null;
    }

    private String uploadMedia(MultipartFile affidavit) {
        try {
            Map<?,?> map = cloudinary.uploader().upload(affidavit.getBytes(),
                    ObjectUtils.asMap("resource_type","auto"));
            return null;
        }catch(IOException error){
            throw new ElectionException(SOMETHING_WENT_WRONG.getMessage());
        }
    }
}