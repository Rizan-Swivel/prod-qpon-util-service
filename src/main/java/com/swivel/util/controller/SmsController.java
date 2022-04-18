package com.swivel.util.controller;

import com.swivel.util.configuration.Translator;
import com.swivel.util.domain.request.SmsRequestDto;
import com.swivel.util.enums.ErrorResponseStatusType;
import com.swivel.util.enums.SuccessResponseStatusType;
import com.swivel.util.exception.UtilServiceException;
import com.swivel.util.service.SmsService;
import com.swivel.util.util.Validator;
import com.swivel.util.wrapper.ResponseWrapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * SMS controller
 */
@CrossOrigin
@Slf4j
@RestController
@Validated
@RequestMapping("api/v1/sms")
public class SmsController extends Controller {

    private final Validator validator;
    private final Logger logger = LoggerFactory.getLogger(SmsController.class);
    private final SmsService smsService;

    public SmsController(Translator translator, Validator validator, SmsService smsService) {
        super(translator);
        this.validator = validator;
        this.smsService = smsService;
    }

    /**
     * SMS send
     *
     * @param timeZone      timeZone
     * @param smsRequestDto smsRequestDto
     * @return ResponseEntity
     */
    @PostMapping("/send")
    public ResponseEntity<ResponseWrapper> sendSms(
            @RequestHeader(name = TIME_ZONE_HEADER) String timeZone,
            @RequestBody SmsRequestDto smsRequestDto
    ) {
        try {
            if (!isValidTimeZone(timeZone)) {
                logger.debug("Invalid time zone, Time zone: {}", timeZone);
                return getErrorResponse(ErrorResponseStatusType.INVALID_TIMEZONE);
            }
            if (!smsRequestDto.isRequiredAvailable()) {
                return getErrorResponse(ErrorResponseStatusType.MISSING_REQUIRED_FIELDS);
            }
            if (!validator.isValidMobileNo(smsRequestDto.getRecipientNo().getNo())) {
                return getErrorResponse(ErrorResponseStatusType.INVALID_MOBILE_NO);
            }
            var response = smsService.sendSms(smsRequestDto);
            String objectToJson = smsRequestDto.getRecipientNo().toLogJson();
            logger.debug("Successfully sent the SMS to: {}, response: {}", objectToJson, response);
            return getSuccessResponse(null, SuccessResponseStatusType.SMS_SUCCESS_MESSAGE);
        } catch (UtilServiceException e) {
            logger.error("Sending sms was failed to: {}", smsRequestDto.getRecipientNo(), e);
            return getInternalServerError();
        }
    }
}
