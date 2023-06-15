package com.telstra.phoneservice.repository;

import com.telstra.phoneservice.model.PhoneStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class PhoneStatusConverter implements AttributeConverter<PhoneStatus, String> {

    @Override
    public String convertToDatabaseColumn(PhoneStatus phoneStatus) {
        if (phoneStatus == null) {
            return null;
        }
        return phoneStatus.getKey();
    }

    @Override
    public PhoneStatus convertToEntityAttribute(String key) {
        return PhoneStatus.getPhoneStatusByKey(key);
    }
}
