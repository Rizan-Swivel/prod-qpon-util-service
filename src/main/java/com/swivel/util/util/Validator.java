package com.swivel.util.util;

import org.springframework.stereotype.Component;

/**
 * Validation Class
 */
@Component
public class Validator {

    private static final String EMAIL_REGEX = "^[\\w-_.+]*[\\w-_.]@([\\w]+\\.)+[\\w]+[\\w]$";
    private static final String MOBILE_NO_REGEX = "^\\+(\\d{1,5})\\d{6,14}$";

    /**
     * This method validates a given email.
     *
     * @param email email address
     * @return true/ false
     */
    public boolean isValidEmail(String email) {
        return email.matches(EMAIL_REGEX);
    }

    /**
     * This method validates a given mobile no.
     *
     * @param mobileNo mobile no
     * @return true/ false
     */
    public boolean isValidMobileNo(String mobileNo) {
        return mobileNo.matches(MOBILE_NO_REGEX);
    }

}
