package com.example.accounts.api.service.impl;


import com.example.accounts.api.dto.CustomerDto;
import com.example.accounts.api.repository.AccountsRepository;
import com.example.accounts.api.repository.CustomerRepository;
import com.example.accounts.api.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public  class AccountServiceImpl implements IAccountService {

    private AccountsRepository accountsRepository;

    private CustomerRepository customerRepository;




    @Override
    public void createAccount(CustomerDto customerDto) {

    }
}
