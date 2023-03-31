package com.example.imaninvest.controller;

import com.example.imaninvest.dto.CurrencyDto;
import com.example.imaninvest.dto.ResponseDto;
import com.example.imaninvest.service.CurrencyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("currency")
public class CurrencyController {
    private CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @PostMapping("/create")
    public ResponseDto<CurrencyDto> createCurrency(@RequestBody CurrencyDto currencyDto) {
        return this.currencyService.create(currencyDto);
    }

    @GetMapping("/get")
    public ResponseDto<CurrencyDto> getCurrency(@RequestParam(value = "id") Integer id) {
        return this.currencyService.get(id);
    }

    @PutMapping("/update")
    public ResponseDto<CurrencyDto> updateCurrency(@RequestBody CurrencyDto currencyDto, @RequestParam Integer id) {
        return currencyService.update(currencyDto, id);
    }

    @DeleteMapping("/delete")
    public ResponseDto<CurrencyDto> deleteCurrency(@RequestParam Integer id) {
        return currencyService.delete(id);
    }
    @GetMapping("/getAll")
    public ResponseDto<List<CurrencyDto>>getAll(){
        return currencyService.getAllCurrency();
    }

}
