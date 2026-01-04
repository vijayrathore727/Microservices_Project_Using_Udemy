package com.example.accounts.api.service.impl;


import com.example.accounts.api.constants.AccountContants;
import com.example.accounts.api.dto.AccountsDto;
import com.example.accounts.api.dto.CustomerDto;
import com.example.accounts.api.entity.Accounts;
import com.example.accounts.api.entity.Customer;
import com.example.accounts.api.exception.CustomerAlreadyExistsException;
import com.example.accounts.api.exception.ResourceNotFoundException;
import com.example.accounts.api.mapper.AccountMappper;
import com.example.accounts.api.mapper.CustomerMapper;
import com.example.accounts.api.repository.AccountsRepository;
import com.example.accounts.api.repository.CustomerRepository;
import com.example.accounts.api.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public  class AccountServiceImpl implements IAccountService {

    private AccountsRepository accountsRepository;

    private CustomerRepository customerRepository;

    /**
     * @param customerDto - CustomerDto Object
     */
    @Override
    public void createAccount(CustomerDto customerDto) {
        System.out.println("Line -------32");
        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());

        if(optionalCustomer.isPresent()) {
            throw new CustomerAlreadyExistsException("Customer already registered with given mobileNumber "
                    +customerDto.getMobileNumber());
        }

        customer.setCreatedAt(LocalDate.now());
        customer.setCreatedBy("Admin");
        Customer savedCustomer = customerRepository.save(customer);
        accountsRepository.save(createNewAccount(savedCustomer));
    }



    /**
     * @param customer - Customer Object
     * @return the new account details
     */
    private Accounts createNewAccount(Customer customer) {
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountContants.SAVINGS);
        newAccount.setBranchAddress(AccountContants.ADDRESS);

        newAccount.setCreatedAt(LocalDate.now());
        newAccount.setCreatedBy("Admin");
        return newAccount;
    }



    public CustomerDto fetchAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );
        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
        customerDto.setAccountsDto(AccountMappper.mapToAccountsDto(accounts, new AccountsDto()));
        return customerDto;
    }




}
