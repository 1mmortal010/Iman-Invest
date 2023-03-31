package com.example.imaninvest.service;

import com.example.imaninvest.dto.AccountDto;
import com.example.imaninvest.dto.ResponseDto;


import com.example.imaninvest.dto.UserDto;
import com.example.imaninvest.module.Account;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.boot.autoconfigure.jms.JmsProperties;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class AccountService {
    private UserService userService;
    private CurrencyService currencyService;
    private List<Account> accountList;
    private Integer index;

    public AccountService(UserService userService, CurrencyService currencyService) {
        this.userService = userService;
        this.currencyService = currencyService;
        this.accountList = new ArrayList<>();
        this.index = 0;
    }

    public ResponseDto<AccountDto> create(AccountDto accountDto) {
        if (this.userService.getUserAcc(accountDto.getUser_id()).getDate() == null) {
            return ResponseDto.<AccountDto>builder()
                    .code(-1)
                    .message("User not found!")
                    .build();
        }
        if (this.currencyService.getCurrencyAcc(accountDto.getCurrency_id()).getDate() == null) {
            return ResponseDto.<AccountDto>builder()
                    .code(-1)
                    .message("Currency  not found!")
                    .build();
        }
        Account account = toEntity(accountDto);
        account.setId(++this.index);
        account.setCreated_at(LocalDateTime.now());
        this.accountList.add(account);
        return ResponseDto.<AccountDto>builder()
                .message("User successful created!")
                .success(true)
                .code(0)
                .build();
    }
    public ResponseDto<AccountDto>get(Integer id){
        for (Account account : this.accountList) {
            if(Objects.equals(account.getId(), id)){
                return ResponseDto.<AccountDto>builder()
                        .message("Account was found")
                        .success(true)
                        .code(0)
                        .date(toDto(account))
                        .build();
            }
        }
        return ResponseDto.<AccountDto>builder()
                .message("Account not found")
                .success(false)
                .code(-1)
                .build();
    }
    public ResponseDto<AccountDto>update(AccountDto accountDto, Integer id){
        if (this.userService.getUserAcc(accountDto.getUser_id()).getDate() == null) {
            return ResponseDto.<AccountDto>builder()
                    .code(-1)
                    .message("User not found!")
                    .build();
        }
        if (this.currencyService.getCurrencyAcc(accountDto.getCurrency_id()).getDate() == null) {
            return ResponseDto.<AccountDto>builder()
                    .code(-1)
                    .message("Currency  not found!")
                    .build();
        }
        for (Account account : this.accountList) {
            if (account.getId().equals(id)) {
                account = toEntity(accountDto);
                account.setUpdated_at(LocalDateTime.now());
                return ResponseDto.<AccountDto>builder()
                        .message("Account was updated")
                        .success(true)
                        .code(0)
                        .build();
            }
        }
        return ResponseDto.<AccountDto>builder()
                .message("Account not found")
                .success(false)
                .code(-1)
                .build();


    }
    public  ResponseDto<AccountDto>delete(Integer id){
        for(Account account:accountList){
            if(account.getId().equals(id)){
                accountList.remove(account);
                return ResponseDto.<AccountDto>builder()
                        .message("Account was deleted")
                        .success(true)
                        .code(0)
                        .build();
            }
        }
        return ResponseDto.<AccountDto>builder()
                .message("Account not found")
                .success(false)
                .code(-1)
                .build();

    }


    private Account toEntity(AccountDto accountDto) {
        Account account = new Account();
        account.setCurrency_id(accountDto.getCurrency_id());
        account.setUser_id(accountDto.getUser_id());
        account.setCreated_at(accountDto.getCreated_at());
        account.setUpdated_at(accountDto.getUpdated_at());
        return account;
    }

    private AccountDto toDto(Account account) {
        AccountDto accountDto = new AccountDto();
        accountDto.setId(account.getId());
        accountDto.setUser_id(account.getUser_id());
        accountDto.setCurrency_id(account.getCurrency_id());
        accountDto.setCreated_at(account.getCreated_at());
        accountDto.setUpdated_at(account.getUpdated_at());
        return accountDto;
    }



}
