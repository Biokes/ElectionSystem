package com.election.electionsystem.repo;


import com.election.electionsystem.models.data.Voter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoterRepository extends JpaRepository<Voter, Long> {
}
