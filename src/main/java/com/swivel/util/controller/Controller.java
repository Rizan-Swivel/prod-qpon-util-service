package com.swivel.util.controller;

import com.swivel.util.configuration.Translator;
import com.swivel.util.domain.response.ResponseDto;
import com.swivel.util.enums.ErrorResponseStatusType;
import com.swivel.util.enums.ResponseStatusType;
import com.swivel.util.enums.SuccessResponseStatusType;
import com.swivel.util.wrapper.ErrorResponseWrapper;
import com.swivel.util.wrapper.ResponseWrapper;
import com.swivel.util.wrapper.SuccessResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.TimeZone;

/**
 * Controller
 */
public class Controller {

    protected static final String APPLICATION_JSON = "application/json";
    protected static final String TIME_ZONE_HEADER = "Time-Zone";


    private final Translator translator;

    @Autowired
    public Controller(Translator translator) {
        this.translator = translator;
    }

    /**
     * This method creates the data response for success request.
     *
     * @param responseDto responseDto
     * @return response entity
     */
    protected ResponseEntity<ResponseWrapper> getSuccessResponse(ResponseDto responseDto,
                                                                 SuccessResponseStatusType successResponseStatusType) {

        var successResponseWrapper = new SuccessResponseWrapper(ResponseStatusType.SUCCESS,
                successResponseStatusType, responseDto,
                translator.toLocale(successResponseStatusType.getCodeString(successResponseStatusType.getCode())));
        return new ResponseEntity<>(successResponseWrapper, HttpStatus.OK);
    }

    /**
     * This method creates the internal server error response.
     *
     * @return response entity
     */
    protected ResponseEntity<ResponseWrapper> getInternalServerError() {
        var errorResponseWrapper = new ErrorResponseWrapper(ResponseStatusType.ERROR,
                ErrorResponseStatusType.INTERNAL_SERVER_ERROR.getMessage(), null,
                translator.toLocale(ErrorResponseStatusType.
                        getCodeString(ErrorResponseStatusType.INTERNAL_SERVER_ERROR.getCode())),
                ErrorResponseStatusType.INTERNAL_SERVER_ERROR.getCode());
        return new ResponseEntity<>(errorResponseWrapper, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * This method creates the empty data response for bad request.
     *
     * @param errorsResponseStatusType errorResponseStatusType
     * @return bad request error response
     */
    protected ResponseEntity<ResponseWrapper> getErrorResponse(ErrorResponseStatusType errorsResponseStatusType) {
        var errorResponseWrapper = new ErrorResponseWrapper(ResponseStatusType.ERROR,
                errorsResponseStatusType.getMessage(), null,
                translator.toLocale(ErrorResponseStatusType.getCodeString(errorsResponseStatusType.getCode())),
                errorsResponseStatusType.getCode());
        return new ResponseEntity<>(errorResponseWrapper, HttpStatus.BAD_REQUEST);
    }

    /**
     * Validate the time zone
     *
     * @param timeZone timeZone
     * @return true / false
     */
    protected boolean isValidTimeZone(String timeZone) {
        for (String tzId : TimeZone.getAvailableIDs()) {
            if (tzId.equals(timeZone)) {
                return true;
            }
        }
        return false;
    }

}
