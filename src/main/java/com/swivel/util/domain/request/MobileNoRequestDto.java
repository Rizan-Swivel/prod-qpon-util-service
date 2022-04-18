package com.swivel.util.domain.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MobileNoRequestDto extends RequestDto {

    private String countryCode;
    private String localNumber;

    /**
     * This method checks all required fields are available.
     *
     * @return true/ false
     */
    @Override
    public boolean isRequiredAvailable() {
        return isNonEmpty(countryCode) && isNonEmpty(localNumber);
    }

    /**
     * This method converts this object to json string for logging purpose.
     * All fields are obfuscated.
     *
     * @return json string
     */
    @Override
    public String toLogJson() {
        return toJson();
    }

    /**
     * This method converts mobileNoRequestDto to below format.
     * Eg: +94-713321911
     *
     * @return formatted mobile no
     */
    public String getNo() {
        return isNonEmpty(countryCode) && isNonEmpty(localNumber) ?
                countryCode.trim() + localNumber.trim()
                : null;
    }
}
