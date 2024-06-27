package com.election.electionsystem.data.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

import static jakarta.persistence.GenerationType.IDENTITY;

@Setter
@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "candidates")
public class Candidate{
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @OneToMany
    private Set<Vote> votes;
}
