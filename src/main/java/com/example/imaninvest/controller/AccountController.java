package com.example.imaninvest.controller;

import com.example.imaninvest.dto.AccountDto;
import com.example.imaninvest.dto.ResponseDto;
import com.example.imaninvest.service.AccountService;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.events.Event;

@RestController
@RequestMapping("account")
public class AccountController {
    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/create")
    public ResponseDto<AccountDto> createAccount(@RequestBody AccountDto accountDto) {
        return accountService.create(accountDto);
    }

    @GetMapping("/get")
    public ResponseDto<AccountDto> getAccount(@RequestParam (value = "id")Integer id) {
        return accountService.get(id);
    }

    @PutMapping("/update")
    public ResponseDto<AccountDto> updateAccount(@RequestBody AccountDto accountDto, Integer id) {
        return accountService.update(accountDto, id);
    }

    @DeleteMapping("/delete")
    public ResponseDto<AccountDto> deleteAccount(@RequestParam Integer id) {
        return accountService.delete(id);
    }
}
