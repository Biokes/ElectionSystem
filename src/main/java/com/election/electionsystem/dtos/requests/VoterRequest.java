package com.election.electionsystem.dtos.requests;

import com.election.electionsystem.annotation.ValidateMail;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDate;


@Builder
@Setter
@Getter
public class VoterRequest {
    @NotBlank
    private String firstname;
    @NotBlank
    private String lastname;
    @NotNull
    private AddressRequest address;
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    @ValidateMail(regex = "^(?=[a-zA-Z])[a-zA-Z]+([0-9]*)([_+!`]*)+@(?=[a-zA-Z])([a-zA-Z]+)([0-9]*)([a-zA-Z0-9._!~+-]*)+\\.[a-zA-Z]{2,}$")
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @NotNull
    private LocalDate DOB;
    @NotNull
    private ContactInfoRequest infoRequest;
}
