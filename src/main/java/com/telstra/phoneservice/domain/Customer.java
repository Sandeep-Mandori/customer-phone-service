package com.telstra.phoneservice.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "customer")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    @Id
    @Column(name = "customer_id")
    private long customerId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @OneToMany(mappedBy="customerId")
    @JsonManagedReference
    private Set<PhoneNumber> phoneNumbers;

    public Customer(long customerId, String firstName, String lastName) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

}
