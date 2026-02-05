package com.assignment.luckypay.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "loan_account")
@Getter
@Setter
public class LoanAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String loanAccountNumber;
    private LocalDate dueDate;
    private BigDecimal emiAmount;

    // getters & setters
}
