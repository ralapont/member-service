package com.example.members.model.input;


import com.example.members.serializer.LocalDateDeSerializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonRootName(value = "member")
public class MemberRequestDto {

    @NotBlank(message = "Last name is required")
    @Size(min = 3, max = 20, message = "Last name should be between 3 and 20 characters")
    @JsonProperty(value = "lastName")
    private String lastName;

    @NotBlank(message = "First name is required")
    @Size(min = 3, max = 20, message = "First name should be between 3 and 20 characters")
    @JsonProperty(value = "firstName")
    private String firstName;

    @JsonProperty(value = "suffix")
    private String suffix;

    @JsonDeserialize(using = LocalDateDeSerializer.class)
    @JsonProperty(value = "expiration")
    private LocalDate expiration;

    @NotBlank(message = "Email is required")
    @Email(message = "Email is invalid")
    @JsonProperty(value = "email")
    private String email;

    @NotBlank(message = "Street is required")
    @Size(min = 3, max = 20, message = "Street name should be between 3 and 20 characters")
    @JsonProperty(value = "street")
    private String street;

    @NotBlank(message = "City is required")
    @Size(min = 3, max = 20, message = "City name should be between 3 and 20 characters")
    @JsonProperty(value = "city")
    private String city;

    @NotBlank(message = "State is required")
    @Size(min = 2, max = 2, message = "State name should be 2 characters")
    @JsonProperty(value = "state")
    private String state;

    @NotBlank(message = "Zip is required")
    @Size(min = 5, max = 5, message = "Zip name should be 5 characters")
    @JsonProperty(value = "zip")
    private String zip;

    @NotBlank(message = "Phone is required")
    @Size(min = 9, max = 9, message = "Phone name should be 9 characters")
    @JsonProperty(value = "phone")
    private String phone;

    @JsonProperty(value = "interests")
    private String interests;

}
