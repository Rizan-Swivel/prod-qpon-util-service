package com.swivel.util.util;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * This class tests the  {@link Validator}
 */
public class ValidatorTest {

    private static final String EMAIL = "rendy@tokoin.io";
    private static final String MOBILE_NO = "+62-81234567890";
    private Validator validator = new Validator();

    @Test
    void Should_ReturnTrue_When_EmailIsValid() {
        assertTrue(validator.isValidEmail(EMAIL));
    }

    @Test
    void Should_ReturnTrue_When_MobileNoIsValid() {
        assertTrue(validator.isValidMobileNo(MOBILE_NO));
    }

    @Test
    void Should_ReturnFalse_When_MobileNoWithoutCountryCode() {
        assertFalse(validator.isValidMobileNo("081123123"));
    }

    @Test
    void Should_ReturnFalse_When_MobileNoWithoutAllDigits() {
        assertFalse(validator.isValidMobileNo("+6281"));
    }

    @Test
    void Should_ReturnFalse_When_MobileNoHasSpaceInTheMiddle() {
        assertFalse(validator.isValidMobileNo("+947233 45345"));
    }

    @Test
    void Should_ReturnTrue_For_NumberWithFiveDigitCountryCode() {
        assertTrue(validator.isValidMobileNo("+62345-808733998"));
    }

    @Test
    void Should_ReturnFalse_For_NumberWithMoreThanFiveDigitCountryCode() {
        assertFalse(validator.isValidMobileNo("+623452-808733998"));
    }

    @Test
    void Should_ReturnFalse_When_EmailIsInvalid() {
        assertFalse(validator.isValidEmail("notvalidmail.com"));
    }

    @Test
    void Should_ReturnFalse_When_MobileNoIsInvalid() {
        assertFalse(validator.isValidMobileNo("06209863467"));
    }

}
