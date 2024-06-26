package com.election.electionsystem.data.models;

import com.election.electionsystem.data.enums.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="elections")
@Entity
public class Election {
    @Id
    private Long id;
    @NotNull
    private String description;
    @NotNull
    private Status status;
}
