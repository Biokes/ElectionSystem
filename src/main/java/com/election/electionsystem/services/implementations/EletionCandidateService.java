package com.election.electionsystem.services.implementations;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.election.electionsystem.data.models.Candidate;
import com.election.electionsystem.data.models.Election;
import com.election.electionsystem.data.models.Voter;
import com.election.electionsystem.dtos.requests.CandidateRegisterRequest;
import com.election.electionsystem.dtos.response.RegisterCandidateResponse;
import com.election.electionsystem.exceptions.ElectionException;
import com.election.electionsystem.repo.CandidateRepo;
import com.election.electionsystem.services.abstractClasses.CandidateService;
import com.election.electionsystem.services.abstractClasses.ElectionService;
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
    private final Cloudinary cloudinary;
    private final ModelMapper mapper;
    private final ElectionService electionService;
    private final CandidateRepo repo;
    @Autowired
    public EletionCandidateService(ModelMapper mapper,CandidateRepo repository,
                                   Cloudinary cloudinary, ElectionService electionService){
        this.mapper = mapper;
        this.repo= repository;
        this.cloudinary= cloudinary;
        this.electionService= electionService;
    }
    @Override
    @Transactional
    public RegisterCandidateResponse registerCandidate(CandidateRegisterRequest request){
        Candidate candidate = mapper.map(request,Candidate.class);
        candidate.setVoter(mapper.map(request.getRegisterRequest(), Voter.class));
        String url = uploadMedia(request.getAffidavit());
        Election election = electionService.findElectionById(request.getEletionId());
        candidate.setElection(election);
        candidate.setDocumentUrl(url);
        candidate.setOffice(request.getContestedOffice());
        repo.save(candidate);
        return  mapper.map(candidate,RegisterCandidateResponse.class);
    }

    private String uploadMedia(MultipartFile affidavit) {
        try {
            Map<?,?> map = cloudinary.uploader().upload(affidavit.getBytes(),
                    ObjectUtils.asMap("resource_type","auto"));
            return map.get("url").toString();
        }catch(IOException error){
            throw new ElectionException(SOMETHING_WENT_WRONG.getMessage());
        }
    }
}