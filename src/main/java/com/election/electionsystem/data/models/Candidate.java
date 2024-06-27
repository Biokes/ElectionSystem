package com.election.electionsystem.data.models;

import com.election.electionsystem.data.enums.Office;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    private Long id;
    @OneToOne
    @JoinColumn(name="candidate_voter")
   private Voter voter;
    @OneToMany
    private Set<Vote> votes;
    @JsonProperty("affidavit")
    private String documentUrl;
    private Office office;
}
