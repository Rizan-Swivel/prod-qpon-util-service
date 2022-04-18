package com.swivel.util.controller;

import com.swivel.util.configuration.Translator;
import com.swivel.util.domain.request.MailRequestDto;
import com.swivel.util.enums.ErrorResponseStatusType;
import com.swivel.util.enums.SuccessResponseStatusType;
import com.swivel.util.exception.UtilServiceException;
import com.swivel.util.service.SesService;
import com.swivel.util.util.Validator;
import com.swivel.util.wrapper.ResponseWrapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Email controller
 */
@CrossOrigin
@Slf4j
@RestController
@Validated
@RequestMapping("api/v1/email")
public class EmailController extends Controller {

    private final SesService sesService;
    private final Validator validator;
    private final Logger logger = LoggerFactory.getLogger(EmailController.class);


    @Autowired
    public EmailController(SesService sesService, Validator validator, Translator translator) {
        super(translator);
        this.sesService = sesService;
        this.validator = validator;
    }

    /**
     * Email send
     *
     * @param timeZone       timeZone
     * @param mailRequestDto mailRequestDto
     * @return ResponseEntity
     */
    @PostMapping("/send")
    public ResponseEntity<ResponseWrapper> sendEmail(
            @RequestHeader(name = TIME_ZONE_HEADER) String timeZone,
            @RequestBody MailRequestDto mailRequestDto
    ) {
        try {

            if (!isValidTimeZone(timeZone)) {
                logger.debug("Invalid time zone, Time zone: {}", timeZone);
                return getErrorResponse(ErrorResponseStatusType.INVALID_TIMEZONE);
            }
            if (!mailRequestDto.isRequiredAvailable()) {
                return getErrorResponse(ErrorResponseStatusType.MISSING_REQUIRED_FIELDS);
            }
            if (!validator.isValidEmail(mailRequestDto.getRecipientAddress())) {
                return getErrorResponse(ErrorResponseStatusType.INVALID_EMAIL);
            }
            sesService.sendEmail(mailRequestDto);
            logger.debug("Successfully sent the email to: {}", mailRequestDto.getRecipientAddress());
            return getSuccessResponse(null, SuccessResponseStatusType.EMAIL_SUCCESS_MESSAGE);
        } catch (UtilServiceException e) {
            logger.error("Sending email was failed to: {} ", mailRequestDto.getRecipientAddress(), e);
            return getInternalServerError();
        }
    }
}
