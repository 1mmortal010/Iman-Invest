package com.example.imaninvest.controller;

import com.example.imaninvest.dto.InvestmentDto;
import com.example.imaninvest.dto.ResponseDto;
import com.example.imaninvest.modul.Invesment;
import com.example.imaninvest.service.InvestmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("investment")
public class InvestmentController {

    private InvestmentService investmentService;

    public InvestmentController(InvestmentService investmentService){}

    @PostMapping("/creat")
    public ResponseDto<InvestmentDto> creat_investment(@RequestParam InvestmentDto dto) {

        return investmentService.create(dto);
    }

    @GetMapping("/get")
    public ResponseDto<InvestmentDto>  get_investment(@RequestParam(value = ("id")) Integer id) {

        return  investmentService.get(id);
    }

    @PutMapping("/update")
    public ResponseDto<InvestmentDto>  update_investment(@RequestBody InvestmentDto dto, @RequestParam(value = ("id")) Integer id) {

        return  investmentService.update(dto , id);
    }

    @DeleteMapping("/delete")
    public ResponseDto<InvestmentDto>  delete_investment(@RequestParam(value = ("id")) Integer id) {

        return  investmentService.delete(id);
    }
}
