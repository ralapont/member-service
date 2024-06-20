package com.example.members.feign.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
@JsonRootName(value = "state")
public class State {
    @JsonProperty(value = "codigo")
    private String codigo;

    @JsonProperty(value = "state")
    private String state;

    @JsonProperty(value = "capital")
    private String capital;

    @JsonProperty(value = "region")
    private String region;

    @JsonProperty(value = "flag")
    private byte[] flag;
}
