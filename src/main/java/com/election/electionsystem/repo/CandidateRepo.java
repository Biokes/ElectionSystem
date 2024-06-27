package com.election.electionsystem.repo;

import com.election.electionsystem.data.models.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepo extends JpaRepository<Candidate, Long> {
}
