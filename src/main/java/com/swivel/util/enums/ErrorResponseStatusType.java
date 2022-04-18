package com.swivel.util.enums;

import lombok.Getter;

/**
 * Error response status type
 */
@Getter
public enum ErrorResponseStatusType {

    MISSING_REQUIRED_FIELDS(4000, "Required fields are missing"),
    INVALID_MOBILE_NO(4101, "Invalid mobile number"),
    INVALID_EMAIL(4102, "Invalid email address"),
    INVALID_TIMEZONE(4103, "Invalid time zone."),
    WEB_SMS_ERROR(4104, "SMS send failed."),
    INTERNAL_SERVER_ERROR(5000, "Failed due to an internal server error");

    private final int code;
    private final String message;

    ErrorResponseStatusType(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * Return String value of code to match with resource-message property key
     *
     * @param code code
     * @return code
     */
    public static String getCodeString(int code) {
        return String.valueOf(code);
    }
}
