package com.election.electionsystem.repo;

import com.election.electionsystem.data.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long>{
}
