package com.example.imaninvest.controller;

import com.example.imaninvest.dto.CurrencyDto;
import com.example.imaninvest.dto.GoalsDto;
import com.example.imaninvest.dto.ResponseDto;
import com.example.imaninvest.service.CurrencyService;
import com.example.imaninvest.service.GoalsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("goals")
public class GoalsController {
    private GoalsService goalsService;
    //DI - dependency injection
    public GoalsController(GoalsService goalsService) {
        this.goalsService = goalsService;
    }
    //goals/create
    @PostMapping("/create")
    public ResponseDto<GoalsDto> create(@RequestBody GoalsDto goalsDto) {
        return this.goalsService.create(goalsDto);
    }
    //goals/get
    @GetMapping("/get")
    public ResponseDto<GoalsDto> get(@RequestParam(value = "id") Integer id) {
        return this.goalsService.get(id);
    }
    //goals/update
    @PutMapping("/update")
    public ResponseDto<GoalsDto> update(@RequestBody GoalsDto goalsDto, @RequestParam Integer id) {
        return goalsService.update(goalsDto, id);
    }
    //goals/delete
    @DeleteMapping("/delete")
    public ResponseDto<GoalsDto> delete(@RequestParam Integer id) {
        return goalsService.delete(id);
    }
    //goals/getAll
    @GetMapping("/getAll")
    public ResponseDto<List<GoalsDto>>getAll(){
        return goalsService.getAll();
    }

}