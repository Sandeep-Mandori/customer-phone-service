package com.telstra.phoneservice.repository;

import com.telstra.phoneservice.CustomerPhoneServiceApplication;
import com.telstra.phoneservice.domain.PhoneNumber;
import com.telstra.phoneservice.model.PhoneStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@SpringBootTest(classes = CustomerPhoneServiceApplication.class)
@AutoConfigureTestDatabase
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class PhoneNumberRepositoryTest {
    @Autowired
    private PhoneNumberRepository phoneNumberRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void testFindAllByCustomerId() {

        List<PhoneNumber> phoneNumberList = phoneNumberRepository.findAllByCustomerId(Long.valueOf(1));
        //Returns data for already stored numbers
        Assertions.assertNotNull(phoneNumberList);
        Assertions.assertEquals(1, phoneNumberList.get(0).getCustomerId());

        phoneNumberList = phoneNumberRepository.findAllByCustomerId(Long.valueOf(3));
        Assertions.assertNotNull(phoneNumberList);
        Assertions.assertEquals(3, phoneNumberList.get(0).getCustomerId());
    }

    @Test
    public void testFindAllByCustomerIdWithNoResults() {
        List<PhoneNumber> phoneNumberList = phoneNumberRepository.findAllByCustomerId(Long.valueOf(300));
        //Returns empty for any random number
        Assertions.assertTrue(phoneNumberList.isEmpty());

    }

    @Test
    @Transactional
    public void testActivateNumber() {

        List<PhoneNumber> phoneNumberList = phoneNumberRepository.findAllByCustomerId(Long.valueOf(1));
        PhoneNumber customerPhoneNumber = phoneNumberList.get(0);
        String simNumber = customerPhoneNumber.getAssociatedSimNumber();
        int updateRowCount = phoneNumberRepository.updatePhoneNumberStatus(PhoneStatus.ACTIVATED, simNumber);
        Assertions.assertEquals(1, updateRowCount);
        phoneNumberList = phoneNumberRepository.findAllByCustomerId(Long.valueOf(1));
    }


}
