package com.telstra.phoneservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhoneActivationRequest {
    @JsonProperty("simNumber")
    private String simNumber;

}