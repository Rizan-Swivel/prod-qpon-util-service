package com.swivel.util.domain.response;

import lombok.Getter;
import lombok.Setter;

/**
 * This class use as send sms response dto
 */
@Getter
@Setter
public class SendSmsResponseDto extends ResponseDto {
    private String request;
    private String status;
    private String group_id;
    private String date;

    @Override
    public String toLogJson() {
        return toJson();
    }
}
