package com.example.accounts.api.controller;


import com.example.accounts.api.constants.AccountContants;
import com.example.accounts.api.dto.CustomerDto;
import com.example.accounts.api.dto.ResponseDto;
import com.example.accounts.api.repository.AccountsRepository;
import com.example.accounts.api.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1",produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
public class AccountsController {


    private IAccountService iAccountService;

        @PostMapping("/create")
    public ResponseEntity<ResponseDto>createAccount(@RequestBody CustomerDto customerDto){

            iAccountService.createAccount(customerDto);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ResponseDto(AccountContants.STATUS_201,AccountContants.MESSAGE_201));
    }

    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fatchAccountDetails(@RequestParam String mobileNumber){

        CustomerDto customerDto = iAccountService.fetchAccount(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }

}
