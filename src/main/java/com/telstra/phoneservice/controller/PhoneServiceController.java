package com.telstra.phoneservice.controller;

import com.telstra.phoneservice.domain.PhoneNumber;
import com.telstra.phoneservice.dto.ActivationResult;
import com.telstra.phoneservice.dto.PhoneActivationRequest;
import com.telstra.phoneservice.service.PhoneNumberService;
import com.telstra.phoneservice.service.SimActivationService;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Key Service controller responsible to represent all interface endpoints and handling all requests.
 */
@RestController
@RequestMapping("/api")
public class PhoneServiceController {
    @Autowired
    private PhoneNumberService phoneNumberService;

    @Autowired
    private SimActivationService simActivationService;

    @GetMapping("/")
    public String test() {
        return "Welcome to Customer Phone Service";
    }

    @GetMapping(path= "/v1/customers/accounts", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<PhoneNumber> getAllPhoneNumbers() {
        return phoneNumberService.getAllPhoneNumbers();
    }

    @GetMapping(path= "/v1/customers/{customer-id}/accounts", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<PhoneNumber> getCustomerPhoneNumbers(@PathVariable("customer-id") Long customerId) {
        return phoneNumberService.getCustomerPhoneNumbers(customerId);
    }

    @GetMapping(path= "/v1/paginated/customers/accounts", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<PhoneNumber> getAllPhoneNumbers(@ParameterObject Pageable pageable) {
        return phoneNumberService.getAllPhoneNumbers(pageable);
    }

    @GetMapping(path= "/v1/paginated/customers/{customer-id}/accounts", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<PhoneNumber> getCustomerPhoneNumbers(@PathVariable("customer-id") Long customerId,
                                                @ParameterObject Pageable pageable) {
        return phoneNumberService.getAllPhoneNumbers(customerId, pageable);
    }

    @PatchMapping("/v1/accounts/activate")
    public ActivationResult activatePhoneNumber(@RequestBody PhoneActivationRequest activationRequest) {
        ActivationResult activationResult= simActivationService.activatePhoneNumber(activationRequest);
        return activationResult;
    }

}
