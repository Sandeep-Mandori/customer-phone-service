package com.telstra.phoneservice.dto;

import com.telstra.phoneservice.model.PhoneStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ActivationResult {
    private String simNumber;

    private PhoneStatus phoneStatus;

}
