package com.election.electionsystem.data.models;

import com.election.electionsystem.data.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import static jakarta.persistence.GenerationType.AUTO;

@Setter
@Getter
@Table(name="elections")
@Entity
public class Election {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    @NotNull
    private String description;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;
}
