package com.assignment.luckypay.integration;

import com.assignment.luckypay.exception.ExternalServiceException;
import com.assignment.luckypay.exception.LoanNotFoundException;
import com.assignment.luckypay.model.dto.ExternalLoanResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
@Component
public class LoanClient {

    private static final Logger log =
            LoggerFactory.getLogger(LoanClient.class);

    private final WebClient webClient;

    public LoanClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public ExternalLoanResponse fetchLoan(String loanAccountNumber) {

        log.info("Calling external loan API for account {}",
                loanAccountNumber);

        try {
            ExternalLoanResponse response = webClient.get()
                    .uri("/loanaccount/{id}", loanAccountNumber)
                    .retrieve()
                    .bodyToMono(ExternalLoanResponse.class)
                    .block();

            log.debug("External API response received for account {}",
                    loanAccountNumber);

            return response;

        } catch (WebClientResponseException.NotFound ex) {
            log.warn("Loan account {} not found in external API",
                    loanAccountNumber);
            throw new LoanNotFoundException(
                    "Loan account not found: " + loanAccountNumber, ex);

        } catch (WebClientResponseException ex) {
            throw new ExternalServiceException(
                    "External loan service error", ex);
        }
    }
}
