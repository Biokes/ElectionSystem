package com.election.electionsystem.dtos.response;

import com.election.electionsystem.data.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class ViewElectionResponse {
    private String description;
    @JsonIgnore
    private Long numberOfVotes;
    @JsonProperty("id")
    private Long id;
    private Status status;
    @JsonSerialize(using= LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDateTime startDate;
    @JsonSerialize(using= LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonProperty("endTime")
    private LocalDateTime endDate;

}
