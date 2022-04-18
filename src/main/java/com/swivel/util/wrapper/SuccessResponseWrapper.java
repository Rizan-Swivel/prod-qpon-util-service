package com.swivel.util.wrapper;

import com.swivel.util.domain.response.ResponseDto;
import com.swivel.util.enums.ResponseStatusType;
import com.swivel.util.enums.SuccessResponseStatusType;
import lombok.Getter;

/**
 * Success Response Wrapper
 */
@Getter
public class SuccessResponseWrapper extends ResponseWrapper {

    private final int statusCode;

    public SuccessResponseWrapper(ResponseStatusType status,
                                  SuccessResponseStatusType successResponseStatusType,
                                  ResponseDto responseDto,
                                  String displayMessage) {
        super(status, successResponseStatusType.getMessage(), responseDto, displayMessage);
        this.statusCode = successResponseStatusType.getCode();
    }
}
