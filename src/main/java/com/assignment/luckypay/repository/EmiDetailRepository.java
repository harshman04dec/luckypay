package com.assignment.luckypay.repository;

import com.assignment.luckypay.model.entity.EmiDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmiDetailRepository
        extends JpaRepository<EmiDetail, Long> {
}
