package com.telstra.phoneservice.repository;


import com.telstra.phoneservice.domain.Customer;
import com.telstra.phoneservice.domain.PhoneNumber;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
