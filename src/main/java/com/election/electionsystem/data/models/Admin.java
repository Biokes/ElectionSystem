package com.election.electionsystem.data.models;

import com.election.electionsystem.data.enums.AdminRole;
import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name= "admins")
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Admin {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "admin_voter_id")
    private Voter voter;
    @Enumerated(EnumType.STRING)
    private AdminRole role;

}
