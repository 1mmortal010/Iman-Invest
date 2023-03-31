package com.example.imaninvest.service;

import com.example.imaninvest.controller.CurrencyController;
import com.example.imaninvest.dto.CurrencyDto;
import com.example.imaninvest.dto.ResponseDto;
import com.example.imaninvest.dto.UserDto;
import com.example.imaninvest.module.Currency;
import org.springframework.stereotype.Service;

import javax.swing.plaf.PanelUI;
import java.util.ArrayList;
import java.util.List;

@Service
public class CurrencyService {
    private List<Currency> currencyList;
    private Integer index;

    public CurrencyService() {
        this.currencyList = new ArrayList<>();
        this.index = 0;
    }

    public ResponseDto<CurrencyDto> create(CurrencyDto currencyDto) {
        if (currencyDto.getUsd_difference() <= 0) {
            return ResponseDto.<CurrencyDto>builder()
                    .message("The USD difference can not be negative or zero number")
                    .success(false)
                    .code(-1)
                    .build();
        }
        Currency currency = toEntity(currencyDto);
        currency.setId(++this.index);
        this.currencyList.add(currency);
        return ResponseDto.<CurrencyDto>builder()
                .message("Currency was created")
                .success(true)
                .code(0)
                .date(currencyDto)
                .build();
    }

    public ResponseDto<CurrencyDto> get(Integer id) {
        for (Currency currency : this.currencyList) {
            if (currency.getId().equals(id)) {
                CurrencyDto currencyDto = toDto(currency);
                return ResponseDto.<CurrencyDto>builder()
                        .message("Currency was found")
                        .success(true)
                        .code(0)
                        .date(currencyDto)
                        .build();
            }
        }
        return ResponseDto.<CurrencyDto>builder()
                .message("Currency was not found")
                .success(false)
                .code(-1)
                .build();
    }

    public ResponseDto<CurrencyDto> update(CurrencyDto currencyDto, Integer id) {
        if (currencyDto.getUsd_difference() <= 0) {
            return ResponseDto.<CurrencyDto>builder()
                    .message("The USD difference can not be negative or zero number")
                    .success(false)
                    .code(-1)
                    .build();
        }
        for (Currency currency : this.currencyList) {
            if (currency.getId().equals(id)) {
                currency = toEntity(currencyDto);
                this.currencyList.add(currency);
                return ResponseDto.<CurrencyDto>builder()
                        .message("Currency was updated")
                        .success(true)
                        .code(0)
                        .build();
            }
        }
        return ResponseDto.<CurrencyDto>builder()
                .message("Currency was not found")
                .success(false)
                .code(-1)
                .build();


    }

    public ResponseDto<CurrencyDto> delete(Integer id) {
        for (Currency currency : this.currencyList) {
            if (currency.getId().equals(id)) {
                this.currencyList.remove(currency);
                return ResponseDto.<CurrencyDto>builder()
                        .message("Currency was deleted")
                        .success(true)
                        .code(0)
                        .build();
            }
        }
        return ResponseDto.<CurrencyDto>builder()
                .message("Currency was not found")
                .success(false)
                .code(-1)
                .build();

    }

    public ResponseDto<List<CurrencyDto>> getAllCurrency() {
        if (currencyList.size() == 0) {
            return ResponseDto.<List<CurrencyDto>>builder()
                    .message("Currency list is empty")
                    .success(false)
                    .code(0)
                    .build();
        }
        List<CurrencyDto> currencyDtoList = this.currencyList
                .stream()
                .map(this::toDto)
                .toList();
        return ResponseDto.<List<CurrencyDto>>builder()
                .message("Ok")
                .code(0)
                .success(true)
                .date(currencyDtoList)
                .build();
    }

    public ResponseDto<CurrencyDto> getCurrencyAcc(Integer id) {
        for (Currency currency : this.currencyList) {
            if (currency.getId().equals(id)) {
                return ResponseDto.<CurrencyDto>builder()
                        .message("ok")
                        .code(0)
                        .success(true)
                        .date(toDto(currency))
                        .build();
            }
        }
        return ResponseDto.<CurrencyDto>builder()
                .message("Currency is not found!")
                .code(-1)
                .build();
    }

    public CurrencyDto toDto(Currency currency) {
        CurrencyDto currencyDto = new CurrencyDto();
        currencyDto.setId(currency.getId());
        currencyDto.setName(currency.getName());
        currencyDto.setShort_name(currency.getShort_name());
        currencyDto.setUsd_difference(currency.getUsd_difference());
        return currencyDto;

    }

    public Currency toEntity(CurrencyDto currencyDto) {
        Currency currency = new Currency();
        currencyDto.setName(currency.getName());
        currencyDto.setShort_name(currency.getShort_name());
        currencyDto.setUsd_difference(currency.getUsd_difference());
        return currency;
    }





}
