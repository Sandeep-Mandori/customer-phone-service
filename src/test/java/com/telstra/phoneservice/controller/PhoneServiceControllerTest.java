package com.telstra.phoneservice.controller;

import com.telstra.phoneservice.domain.Customer;
import com.telstra.phoneservice.domain.PhoneNumber;
import com.telstra.phoneservice.dto.ActivationResult;
import com.telstra.phoneservice.dto.PhoneActivationRequest;
import com.telstra.phoneservice.exception.SystemException;
import com.telstra.phoneservice.model.PhoneStatus;
import com.telstra.phoneservice.service.PhoneNumberService;
import com.telstra.phoneservice.service.SimActivationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
public class PhoneServiceControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PhoneNumberService phoneNumberService;

    @MockBean
    private SimActivationService simActivationService;


    private final Customer customer1 = new Customer(201, "TestUser", "TestLastName");
    private final Customer customer2 = new Customer(202, "TestUser2", "TestLastName2");

    private final List<Customer> customerList = List.of(customer1, customer2);

    private PhoneNumber phoneNumber1 = new PhoneNumber("234235353435", "mobile", customer1, "23232323232323232334", PhoneStatus.CREATED);
    private PhoneNumber phoneNumber2 = new PhoneNumber("334235353435", "mobile", customer2, "33232323232323232334", PhoneStatus.CREATED);
    private PhoneNumber phoneNumber3 = new PhoneNumber("434235353435", "mobile", customer1, "43232323232323232334", PhoneStatus.CREATED);
    private PhoneNumber phoneNumber4 = new PhoneNumber("534235353435", "mobile", customer2, "53232323232323232334", PhoneStatus.CREATED);
    private final List<PhoneNumber> phoneNumbers = List.of( phoneNumber1, phoneNumber2, phoneNumber3, phoneNumber4);

    @Test
    public void testGetAllPhoneNumbers() throws Exception {
        BDDMockito.given(phoneNumberService.getAllPhoneNumbers()).willReturn(phoneNumbers);


        mvc.perform(MockMvcRequestBuilders.get("/api/v1/customers/accounts")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[0].phoneNumber", is("234235353435")))
                        .andExpect(jsonPath("$[0].associatedSimNumber", is("23232323232323232334")))
                                .andExpect(jsonPath("$[0].status", is(PhoneStatus.CREATED.toString()))
                );


    }

    @Test
    public void testGetPhoneNumbersByCustomerId() throws Exception {
        BDDMockito.given(phoneNumberService.getCustomerPhoneNumbers(Long.valueOf("201"))).willReturn(List.of(phoneNumber1, phoneNumber3));


        mvc.perform(MockMvcRequestBuilders.get("/api/v1/customers/{customerId}/accounts", Long.valueOf("201"))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].phoneNumber", is("234235353435")))
                .andExpect(jsonPath("$[0].associatedSimNumber", is("23232323232323232334")))
                .andExpect(jsonPath("$[0].status", is(PhoneStatus.CREATED.toString())))
                        .andExpect(jsonPath("$[1].phoneNumber", is("434235353435")))
                        .andExpect(jsonPath("$[1].associatedSimNumber", is("43232323232323232334")))
                        .andExpect(jsonPath("$[1].status", is(PhoneStatus.CREATED.toString()))
                );


    }

    @Test
    public void testGetPaginatedPhoneNumbersByCustomerId() throws Exception {
        BDDMockito.given(phoneNumberService.getAllPhoneNumbers(Long.valueOf("201"), Pageable.ofSize(1)))
                .willReturn(List.of(phoneNumber1));


        mvc.perform(MockMvcRequestBuilders.get("/api/v1/paginated/customers/{customerId}/accounts", Long.valueOf("201"))
                        .contentType(MediaType.APPLICATION_JSON_VALUE).param("page","0").param("size", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].phoneNumber", is("234235353435")))
                .andExpect(jsonPath("$[0].associatedSimNumber", is("23232323232323232334")))
                .andExpect(jsonPath("$[0].status", is(PhoneStatus.CREATED.toString())));
    }

    @Test
    public void testGetPaginatedAllPhoneNumbers() throws Exception {
        BDDMockito.given(phoneNumberService.getAllPhoneNumbers(Pageable.ofSize(3)))
                .willReturn(List.of(phoneNumber1, phoneNumber2, phoneNumber3));


        mvc.perform(MockMvcRequestBuilders.get("/api/v1/paginated/customers/accounts", Long.valueOf("201"))
                        .contentType(MediaType.APPLICATION_JSON_VALUE).param("page","0").param("size", "3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].phoneNumber", is("234235353435")))
                .andExpect(jsonPath("$[0].associatedSimNumber", is("23232323232323232334")))
                .andExpect(jsonPath("$[0].status", is(PhoneStatus.CREATED.toString())))
                .andExpect(jsonPath("$[2].phoneNumber", is("434235353435")))
                .andExpect(jsonPath("$[2].associatedSimNumber", is("43232323232323232334")))
                .andExpect(jsonPath("$[2].status", is(PhoneStatus.CREATED.toString())));
    }

    @Test
    public void testActivatePhoneNumbers() throws Exception {
        BDDMockito.given(simActivationService.activatePhoneNumber(new PhoneActivationRequest("23232323232323232334")))
                .willReturn(new ActivationResult("23232323232323232334", PhoneStatus.ACTIVATED));


        mvc.perform(MockMvcRequestBuilders.patch("/api/v1/accounts/activate").content("{\n" +
                                "  \"simNumber\": \"23232323232323232334\"\n" +
                                "}")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.simNumber", is("23232323232323232334")))
                .andExpect(jsonPath("$.phoneStatus", is(PhoneStatus.ACTIVATED.toString())));
    }

    @Test
    public void testActivateInvalidPhoneNumbers() throws Exception {
        SystemException exception = new SystemException("Invalid sim card");

        BDDMockito.willThrow(exception)
                .given(simActivationService)
                .activatePhoneNumber(new PhoneActivationRequest("dummyinvalidsim232"));
        try {
            mvc.perform(MockMvcRequestBuilders.patch("/api/v1/accounts/activate").content("{\n" +
                                    "  \"simNumber\": \"dummyinvalidsim232\"\n" +
                                    "}")
                            .contentType(MediaType.APPLICATION_JSON_VALUE))
                    .andExpect(status().is5xxServerError());
        } catch(Exception e) {
            //skipping for now, ideally should be logged or
            Assertions.assertEquals(exception.getMessage(), e.getCause().getMessage());
        }

    }


}
