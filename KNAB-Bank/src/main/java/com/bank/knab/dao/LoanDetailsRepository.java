package com.bank.knab.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.knab.entity.LoanDetails;


@Repository
public interface LoanDetailsRepository extends JpaRepository<LoanDetails, Integer> {

}
