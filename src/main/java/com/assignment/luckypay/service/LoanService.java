package com.assignment.luckypay.service;

import com.assignment.luckypay.integration.LoanClient;
import com.assignment.luckypay.model.dto.EmiDetailDto;
import com.assignment.luckypay.model.dto.ExternalLoanResponse;
import com.assignment.luckypay.model.dto.LoanResponse;
import com.assignment.luckypay.model.entity.EmiDetail;
import com.assignment.luckypay.model.entity.LoanAccount;
import com.assignment.luckypay.repository.EmiDetailRepository;
import com.assignment.luckypay.repository.LoanAccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class LoanService {

    private final LoanClient client;
    private final LoanAccountRepository loanRepo;
    private final EmiDetailRepository emiRepo;

    public LoanService(LoanClient client,
                       LoanAccountRepository loanRepo,
                       EmiDetailRepository emiRepo) {
        this.client = client;
        this.loanRepo = loanRepo;
        this.emiRepo = emiRepo;
    }
    private static final Logger log =
            LoggerFactory.getLogger(LoanService.class);


        public LoanResponse getLoan(String loanAccountNumber) {

            log.info("Processing loan request for account {}",
                    loanAccountNumber);

            ExternalLoanResponse response =
                    client.fetchLoan(loanAccountNumber);

            log.info("Persisting loan data for account {}",
                    loanAccountNumber);

            List<EmiDetailDto> dueEmis = response.getEmiDetails().stream()
                    .filter(e -> e.isDueStatus() && !e.isPaidStatus()).toList();

        EmiDetailDto dueEmi = dueEmis.stream().findFirst().orElseThrow();
        BigDecimal totalDueAmount = response.getEmiDetails().stream()
                .filter(e -> e.isDueStatus() && !e.isPaidStatus())
                .map(EmiDetailDto::getEmiAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

            LoanAccount loan = new LoanAccount();
        loan.setLoanAccountNumber(response.getLoanAccountNumber());
        loan.setEmiAmount(totalDueAmount);
        loan.setDueDate(parseMonth(dueEmi.getMonth()));

        loanRepo.save(loan);

        for (EmiDetailDto dto : response.getEmiDetails()) {
            EmiDetail emi = new EmiDetail();
            emi.setMonth(dto.getMonth());
            emi.setEmiAmount(dto.getEmiAmount());
            emi.setPaidStatus(dto.isPaidStatus());
            emi.setDueStatus(dto.isDueStatus());
            emi.setLoanAccount(loan);
            emiRepo.save(emi);
        }

        return new LoanResponse(
                loan.getLoanAccountNumber(),
                loan.getDueDate(),
                loan.getEmiAmount()
        );
    }

    private LocalDate parseMonth(String month) {
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("MMMM yyyy");
        return YearMonth.parse(month, formatter).atEndOfMonth();
    }
}
