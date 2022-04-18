package com.swivel.util.domain.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * SMS request Dto
 */
@AllArgsConstructor
@Setter
@Getter
public class SmsRequestDto extends RequestDto {

    private final MobileNoRequestDto recipientNo;
    private final String message;

    @Override
    public boolean isRequiredAvailable() {
        return isNonEmpty(message) && recipientNo.isRequiredAvailable();
    }

    @Override
    public String toLogJson() {
        return toJson();
    }
}
