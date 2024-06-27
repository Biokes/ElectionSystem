package com.election.electionsystem.data.models;

import com.election.electionsystem.data.enums.Office;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

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
    @OneToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name="candidate_voter")
    private Voter voter;
    @JoinColumn(name="candidate_election")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Election election;
    @JsonProperty("affidavit")
    private String documentUrl;
    @Enumerated(EnumType.STRING)
    private Office office;
    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Vote> votes;
}
