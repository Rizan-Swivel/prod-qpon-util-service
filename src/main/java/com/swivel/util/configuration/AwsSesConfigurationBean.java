package com.swivel.util.configuration;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * AWS Simple Email Service Configuration Bean class
 */
@Configuration
public class AwsSesConfigurationBean {

    private final String region;

    public AwsSesConfigurationBean(@Value("${email.region}") String region) {
        this.region = region;
    }

    /**
     * Build the aws ses client with default configuration
     *
     * @return AmazonSimpleEmailServiceClientBuilder
     */
    @Bean
    public AmazonSimpleEmailService amazonSimpleEmailService() {
        return AmazonSimpleEmailServiceClientBuilder.standard()
                .withRegion(region).build();
    }

}
