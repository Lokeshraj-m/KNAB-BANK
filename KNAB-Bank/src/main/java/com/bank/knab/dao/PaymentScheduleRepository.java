package com.bank.knab.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.knab.entity.PaymentSchedule;

@Repository
public interface PaymentScheduleRepository extends JpaRepository<PaymentSchedule, Integer> {

}
