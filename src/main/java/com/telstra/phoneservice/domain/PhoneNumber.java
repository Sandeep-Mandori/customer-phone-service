package com.telstra.phoneservice.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.telstra.phoneservice.model.PhoneStatus;
import lombok.*;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "phone_number")
@Getter
public class PhoneNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @NaturalId()
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "phone_number_type")
    private String phoneNumberType;
    @Column(name = "sim_number")
    private String associatedSimNumber;
    @Column(name = "status")
    private PhoneStatus status;
    @Column(name = "creation_time")
    @EqualsAndHashCode.Exclude
    private Instant creationTime = Instant.now();
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    @ToString.Exclude
    @JsonBackReference
    private Customer customerId;


    public PhoneNumber() {
        creationTime = Instant.now();
    }

    public PhoneNumber(String phoneNumber, String phoneNumberType, Customer owner, String associatedSimNumber, PhoneStatus status) {
        creationTime = Instant.now();
        this.associatedSimNumber = associatedSimNumber;
        this.customerId = owner;
        this.phoneNumber = phoneNumber;
        this.phoneNumberType = phoneNumberType;
        this.status = status;

    }


    @JsonProperty
    public Long getCustomerId() {
        return customerId == null ? null : customerId.getCustomerId();
    }

}
