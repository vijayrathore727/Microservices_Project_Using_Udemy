package com.example.accounts.api.service;

import com.example.accounts.api.dto.CustomerDto;

public interface IAccountService {



    void  createAccount(CustomerDto customerDto);
    CustomerDto fetchAccount(String mobileNumber);

}
