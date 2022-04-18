package com.swivel.util.service;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.*;
import com.swivel.util.domain.request.MailRequestDto;
import com.swivel.util.exception.UtilServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * SesService
 */
@Service
@Slf4j
public class SesService {

    private static final String CHAR_SET = "UTF-8";
    private final String sender;
    //milliseconds for a logical request/response round-trip to AWS.
    private final int requestTimeoutInMilliseconds;
    private final AmazonSimpleEmailService emailService;

    @Autowired
    public SesService(
            AmazonSimpleEmailService emailService,
            @Value("${email.sender}") String sender,
            @Value("${aws.config.requestTimeout}") int requestTimeoutInMilliseconds) {
        this.emailService = emailService;
        this.sender = sender;
        this.requestTimeoutInMilliseconds = requestTimeoutInMilliseconds;
    }


    /**
     * This method use to send an email
     *
     * @param mailRequestDto mailRequestDto
     */
    public void sendEmail(MailRequestDto mailRequestDto) {
        try {
            SendEmailRequest request = new SendEmailRequest()
                    .withDestination(
                            new Destination().withToAddresses(mailRequestDto.getRecipientAddress()))
                    .withMessage(new Message()
                            .withBody(new Body()
                                    .withText(new Content()
                                            .withCharset(CHAR_SET).withData(mailRequestDto.getContent())))
                            .withSubject(new Content()
                                    .withCharset(CHAR_SET).withData(mailRequestDto.getTitle())))
                    .withSource(sender).withSdkRequestTimeout(requestTimeoutInMilliseconds);
            emailService.sendEmail(request);
        } catch (AmazonSimpleEmailServiceException e) {
            throw new UtilServiceException("Error occurred while sending email, please try again later ", e);
        }
    }
}
