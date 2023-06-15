package com.telstra.phoneservice.model;

import com.telstra.phoneservice.exception.SystemException;

import java.util.HashMap;
import java.util.Map;

public enum PhoneStatus {
    CREATED("created"), ASSIGNED("assigned"), ACTIVATED("activated"), DEACTIVATED("deactivated"), EXPIRED("expired");
   private static final Map<String, PhoneStatus> phoneStatusKeyMap = new HashMap<>();
    static {
        for (PhoneStatus status : PhoneStatus.values()) {
            phoneStatusKeyMap.put(status.getKey(), status);
        };
    }
    private String key;
    PhoneStatus(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    /**
     * Returns enum {@code PhoneStatus} if valid key is supplied otherwise throws an exception.
     * @param key
     * @return
     */
    public static PhoneStatus getPhoneStatusByKey(String key) {
        PhoneStatus status;
        if (key == null || (status = phoneStatusKeyMap.get(key)) == null) {
            throw new SystemException(String.format("Phone status key=%s is invalid", key));
        }
        return status;
    }

}
