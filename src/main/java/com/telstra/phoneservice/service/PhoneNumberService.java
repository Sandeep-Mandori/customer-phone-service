package com.telstra.phoneservice.service;

import com.telstra.phoneservice.domain.PhoneNumber;
import com.telstra.phoneservice.exception.SystemException;
import com.telstra.phoneservice.repository.PhoneNumberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class PhoneNumberService {
    @Autowired
    private PhoneNumberRepository phoneNumberRepository;

    public List<PhoneNumber> getAllPhoneNumbers() {
        Iterable<PhoneNumber> itr = phoneNumberRepository.findAll();
        List<PhoneNumber> result = new ArrayList<>();
        itr.forEach(result::add);
        return result;
    }

    public List<PhoneNumber> getCustomerPhoneNumbers(Long customerId) {
        return phoneNumberRepository.findAllByCustomerId(customerId);
    }
    public List<PhoneNumber> getAllPhoneNumbers(Pageable pageable) {
        Page<PhoneNumber> pageResult = phoneNumberRepository.findAll(pageable);
        if(pageResult.hasContent()) {
            return pageResult.getContent();
        } else {
            return Collections.emptyList();
        }
    }
    public List<PhoneNumber> getAllPhoneNumbers(Long customerId, Pageable pageable) {
        if (customerId != null) {
            return phoneNumberRepository.findAllByCustomerId(customerId, pageable);
        }
        throw new SystemException("Supplied customer id is null");
    }
}
