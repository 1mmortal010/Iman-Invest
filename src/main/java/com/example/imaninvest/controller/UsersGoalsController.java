package com.example.imaninvest.controller;

import com.example.imaninvest.dto.GoalsDto;
import com.example.imaninvest.dto.ResponseDto;
import com.example.imaninvest.dto.UsersGoalsDto;
import com.example.imaninvest.service.UsersGoalsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users_goals")
public class UsersGoalsController {
    private UsersGoalsService usersGoalsService;
    //DI - dependency injection
    public UsersGoalsController(UsersGoalsService usersGoalsService) {
        this.usersGoalsService = usersGoalsService;
    }
    //users_goals/create
    @PostMapping("/create")
    public ResponseDto<UsersGoalsDto> create(@RequestBody UsersGoalsDto usersGoalsDto) {
        return this.usersGoalsService.create(usersGoalsDto);
    }
    //users_goals/get
    @GetMapping("/get")
    public ResponseDto<UsersGoalsDto> get(@RequestParam(value = "id") Integer id) {
        return this.usersGoalsService.get(id);
    }
    //users_goals/update
    @PutMapping("/update")
    public ResponseDto<UsersGoalsDto> update(@RequestBody UsersGoalsDto usersGoalsDto, @RequestParam Integer id) {
        return usersGoalsService.update(usersGoalsDto, id);
    }
    //users_goals/delete
    @DeleteMapping("/delete")
    public ResponseDto<UsersGoalsDto> delete(@RequestParam Integer id) {
        return usersGoalsService.delete(id);
    }
}