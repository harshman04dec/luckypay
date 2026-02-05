package com.assignment.luckypay.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "emi_detail")
@Getter
@Setter
public class EmiDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String month;
    private BigDecimal emiAmount;
    private boolean paidStatus;
    private boolean dueStatus;

    @ManyToOne
    @JoinColumn(name = "loan_account_id")
    private LoanAccount loanAccount;

    // getters & setters
}
