package com.example.imaninvest.controller;

import com.example.imaninvest.dto.ResponseDto;
import com.example.imaninvest.dto.UserDto;
import com.example.imaninvest.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseDto<UserDto> create(@RequestBody UserDto userDto) {
        return userService.createUser(userDto);

    }

    @GetMapping("/get")
    public ResponseDto<UserDto> get(@RequestParam(value = "id") Integer id) {
        return userService.get(id);
    }

    @PutMapping("/update")
    public ResponseDto<UserDto> update(@RequestBody UserDto userDto, @RequestParam(value = "id") Integer id) {
        return userService.update(userDto, id);
    }

    @DeleteMapping("/delete")
    public ResponseDto<UserDto> delete(@RequestParam(value = "id") Integer id) {
        return userService.delete(id);
    }

    @GetMapping("get-all")
    public ResponseDto<List<UserDto>> getAllUser() {
        return userService.getAll();
    }


}
