package com.election.electionsystem.data.models;

import com.election.electionsystem.data.enums.PartyAffiliation;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Table(name= "votes")
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JoinColumn(name= "voter_id")
    @OneToOne
    private Voter voter;
    @Setter(AccessLevel.NONE)
    private LocalDateTime timeVoted;
    @JoinColumn(name="candidate_vote_id")
    @ManyToOne
    private Candidate candidate;
    @PrePersist
    public void setTimeVoted(){
        timeVoted= LocalDateTime.now();
    }
}
