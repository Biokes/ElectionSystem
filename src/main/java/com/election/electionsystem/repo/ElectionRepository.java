package com.election.electionsystem.repo;

import com.election.electionsystem.data.models.Election;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElectionRepository extends JpaRepository<Election, Long> {
}
