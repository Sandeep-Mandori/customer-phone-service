package com.telstra.phoneservice.repository;


import com.telstra.phoneservice.domain.PhoneNumber;
import com.telstra.phoneservice.model.PhoneStatus;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PhoneNumberRepository extends PagingAndSortingRepository<PhoneNumber, Long> {

    List<PhoneNumber> findAllByCustomerId(Long customerId, Pageable pageable);

    @Query("SELECT phone FROM PhoneNumber phone WHERE phone.customerId.customerId = :customerId")
    List<PhoneNumber> findAllByCustomerId(Long customerId);
    @Modifying
    @Query("update PhoneNumber phone set phone.status = :newStatus where phone.associatedSimNumber = :simNumber")
    int updatePhoneNumberStatus(@Param("newStatus") PhoneStatus newStatus, @Param("simNumber") String simNumber);
}
