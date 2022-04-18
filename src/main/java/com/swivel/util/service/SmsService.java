package com.swivel.util.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.swivel.util.domain.request.SmsRequestDto;
import com.swivel.util.domain.response.SendSmsResponseDto;
import com.swivel.util.exception.UtilServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 *
 */
@Service
@Slf4j
public class SmsService {

    private static final String TO = "&to=";
    private static final String TEXT = "&text=";
    private final String url;
    private final RestTemplate restTemplate;

    @Autowired
    public SmsService(
            @Value("${sms.baseUrl}") String baseUrl,
            @Value("${sms.uri}") String uri,
            RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.url = baseUrl + uri;
    }

    /**
     * This method usd to send SMS
     *
     * @param smsRequestDto smsRequestDto
     */
    public SendSmsResponseDto sendSms(SmsRequestDto smsRequestDto) {
        try {
            var responseEntity =
                    restTemplate.exchange(url + TO + smsRequestDto.getRecipientNo().getNo() + TEXT +
                                    smsRequestDto.getMessage(),
                            HttpMethod.POST,
                            null,
                            String.class);
            return new ObjectMapper()
                    .readValue(responseEntity.getBody(), SendSmsResponseDto.class);
        } catch (HttpClientErrorException e) {
            throw new UtilServiceException("Send SMS was failed.", e);
        } catch (JsonProcessingException e) {
            throw new UtilServiceException("Converting SMS response to SendSmsResponseDto was failed.", e);
        }
    }
}
