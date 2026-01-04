package com.example.accounts.api.repository;


import com.example.accounts.api.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {


    Optional<Customer> findByMobileNumber(String mobileNumber);
}
