package com.assignment.luckypay.repository;

import com.assignment.luckypay.model.entity.LoanAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanAccountRepository
        extends JpaRepository<LoanAccount, Long> {
}
