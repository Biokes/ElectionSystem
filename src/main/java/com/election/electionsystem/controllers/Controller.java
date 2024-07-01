package com.election.electionsystem.controllers;

import com.election.electionsystem.dtos.requests.AdminLoginRequest;
import com.election.electionsystem.dtos.requests.AdminRegister;
import com.election.electionsystem.services.abstractClasses.AdminService;
import com.election.electionsystem.services.abstractClasses.VoterService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
//@AllArgsConstructor
@RequestMapping("api/v1/election")
public class Controller{
    private final AdminService adminService;
    @Autowired
    public Controller(AdminService adminService){
        this.adminService= adminService;
    }
    @PostMapping("/admin/register")
    public ResponseEntity<?> RegisterAsAdmin(@RequestBody AdminRegister request){
        return  ResponseEntity.status(CREATED).body(adminService.registerAsAdmin(request));
    }
    @PostMapping("/admin/login")
    public ResponseEntity<?> loginAsAdmin(@RequestBody AdminLoginRequest loginRequest){

    }
}
