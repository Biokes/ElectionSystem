package com.election.electionsystem.repo;


import com.election.electionsystem.data.models.Voter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoterRepository extends JpaRepository<Voter, Long> {
}
