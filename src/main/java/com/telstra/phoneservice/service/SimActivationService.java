package com.telstra.phoneservice.service;

import com.telstra.phoneservice.dto.ActivationResult;
import com.telstra.phoneservice.dto.PhoneActivationRequest;
import com.telstra.phoneservice.exception.BusinessException;
import com.telstra.phoneservice.model.PhoneStatus;
import com.telstra.phoneservice.repository.PhoneNumberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Acts as a placeholder to surround and contain the sim activation logic with valid business rule sets.
 * e.g. sim cant be activated because it is currently in use by other user or phone number not ported or something else.
 *
 */
@Service
@Transactional
public class SimActivationService {

    @Autowired
    private PhoneNumberRepository phoneNumberRepository;
    /**
     * //TODO: Right now just returning boolean, but this method can be extended to return some data structure to hold the
     * validation errors and feedback. This layer is supposed to be integrated with other systems for orchestration.
     * @return true if validated successfully
     */
    boolean validate(PhoneActivationRequest activationRequest) {
        if (activationRequest == null || activationRequest.getSimNumber() == null) {
            throw new BusinessException("Activation request is invalid or sim number provided empty");
        }
        if (activationRequest == null || activationRequest.getSimNumber() == null) {
            throw new BusinessException("Activation request is invalid or sim number provided empty");
        }
        //TODO: validate sequence of events for activation, potentially a new service can be build for workflow management
        //TODO: activate a flow for customer documents and take through the journey
        return true;
    }

    public ActivationResult activatePhoneNumber(PhoneActivationRequest activationRequest) {
       if (validate(activationRequest)) {
            int updatedRowCount = phoneNumberRepository.updatePhoneNumberStatus(PhoneStatus.ACTIVATED, activationRequest.getSimNumber());
            if (updatedRowCount > 0) {
                return new ActivationResult(activationRequest.getSimNumber(), PhoneStatus.ACTIVATED);
            }
       }
        throw new BusinessException("Activation request is failed, please return a call to our call center.");
    }

}
