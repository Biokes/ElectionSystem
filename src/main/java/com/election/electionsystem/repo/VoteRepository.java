package com.election.electionsystem.repo;
import com.election.electionsystem.data.models.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface VoteRepository extends  JpaRepository<Vote, Long>{
    @Query("select m from Vote m where m.candidate.id =: candidateId and m.voter.id =: voterId ")
    Optional<Vote> findVoteByCandidate_IdAndVoter(Long voterId, Long candidateId);
}
