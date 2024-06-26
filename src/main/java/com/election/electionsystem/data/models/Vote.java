package com.election.electionsystem.data.models;

import com.election.electionsystem.data.enums.PartyAffiliation;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Table(name= "votes")
@Entity
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JoinColumn(name= "voter_id")
    @OneToOne
    private Voter voter;
    @Setter(AccessLevel.NONE)
    private LocalDateTime timeVoted;
    private PartyAffiliation partyAffiliation;
    @ManyToOne
    @JoinColumn(name="election_id")
    private Election election;
    @PrePersist
    public void setTimeVoted(){
        timeVoted= LocalDateTime.now();
    }
}
