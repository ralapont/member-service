package com.example.members.model.input;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
@JsonRootName(value = "member")
public class MemberRequestDto {
    @JsonProperty(value = "lastName")
    private String lastName;

    @JsonProperty(value = "firstName")
    private String firstName;

    @JsonProperty(value = "suffix")
    private String suffix;

    @JsonProperty(value = "expiration")
    private LocalDate expiration;

    @JsonProperty(value = "email")
    private String email;

    @JsonProperty(value = "street")
    private String street;

    @JsonProperty(value = "city")
    private String city;

    @JsonProperty(value = "state")
    private String state;

    @JsonProperty(value = "zip")
    private String zip;

    @JsonProperty(value = "phone")
    private String phone;

    @JsonProperty(value = "interests")
    private String interests;

}
