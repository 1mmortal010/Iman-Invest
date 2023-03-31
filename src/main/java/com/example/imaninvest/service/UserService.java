package com.example.imaninvest.service;

import com.example.imaninvest.dto.ResponseDto;
import com.example.imaninvest.module.User;
import org.springframework.stereotype.Service;
import com.example.imaninvest.dto.UserDto;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private List<User> userList;
    private Integer index;

    public UserService() {
        this.userList = new ArrayList<>();
        this.index = 0;
    }

    public ResponseDto<UserDto> createUser(UserDto userDto) {
        if (!checkerUserPassword(userDto.getPassword())) {
            return ResponseDto.<UserDto>builder()
                    .code(-1)
                    .message("Password error!")
                    .build();
        }
        if (!checkerUserPhone(userDto.getPhone_number())) {
            return ResponseDto.<UserDto>builder()
                    .code(-1)
                    .message("This phone number is used by other user!")
                    .build();
        }
        if (!checkerUserEmail(userDto.getEmail())) {
            return ResponseDto.<UserDto>builder()
                    .code(-1)
                    .message("User already exist!")
                    .build();
        }
        User user = toEntity(userDto);
        user.setId(++this.index);
        user.setCreated_at(LocalDateTime.now());
        this.userList.add(user);
//        UserDto userDto1=toDto(user);
        return ResponseDto.<UserDto>builder()
                .message("User successful created!")
                .success(true)
                .code(0)
                .build();

    }

    public ResponseDto<UserDto> get(Integer id) {
        for (User user : this.userList) {
            if (user.getId().equals(id)) {
                UserDto dto = toDto(user);
                return ResponseDto.<UserDto>builder()
                        .message("Ok")
                        .success(true)
                        .date(dto)
                        .code(0)
                        .build();
            }
        }
        return ResponseDto.<UserDto>builder()
                .message("User is not found!")
                .success(false)
                .code(-1)
                .build();
    }

    public ResponseDto<UserDto> delete(Integer id) {
        for (User user : this.userList) {
            if (user.getId().equals(id)) {
                this.userList.remove(user);
                return ResponseDto.<UserDto>builder()
                        .message("User successul deleted!")
                        .success(true)
                        .code(0)
                        .build();
            }
        }
        return ResponseDto.<UserDto>builder()
                .message("User is not found!")
                .success(false)
                .code(-1)
                .build();
    }

    public ResponseDto<UserDto> update(UserDto userDto, Integer id) {
        if (!checkerUserPhone(userDto.getPhone_number())) {
            return ResponseDto.<UserDto>builder()
                    .code(-1)
                    .message("This phone number is used by other user!")
                    .build();
        }
        if (!checkerUserEmail(userDto.getEmail())) {
            return ResponseDto.<UserDto>builder()
                    .code(-1)
                    .message("Email is already exit!")
                    .build();
        }
        if (!checkerUserPassword(userDto.getPassword())) {
            return ResponseDto.<UserDto>builder()
                    .code(-1)
                    .message("Password error!")
                    .build();
        }
        for (User user : this.userList) {
            if (user.getId().equals(id)) {
                user = toEntity(userDto);
                user.setUpdated_at(LocalDateTime.now());
                this.userList.add(user);
                return ResponseDto.<UserDto>builder()
                        .message("User successful updated!")
                        .success(true)
                        .code(0)
                        .build();
            }
        }
        return ResponseDto.<UserDto>builder()
                .message("User is not found!")
                .success(false)
                .code(-1)
                .build();
    }

    public ResponseDto<List<UserDto>> getAll() {
        if (this.userList.size() == 0) {
            return ResponseDto.<List<UserDto>>builder()
                    .message("Userlist is Empty!")
                    .code(0)
                    .success(true)
                    .build();
        }
        List<UserDto> userDtoList = this.userList
                .stream()
                .map(this::toDto)
                .toList();
        return ResponseDto.<List<UserDto>>builder()
                .message("Ok")
                .code(0)
                .success(true)
                .date(userDtoList)
                .build();
    }
    public ResponseDto<UserDto> getUserAcc(Integer id) {
        for (User user : this.userList) {
            if (user.getId().equals(id)) {
                return ResponseDto.<UserDto>builder()
                        .message("Ok")
                        .success(true)
                        .code(0)
                        .date(toDto(user))
                        .build();
            }
        }
        return ResponseDto.<UserDto>builder()
                .message("Card is not found!")
                .code(-1)
                .build();
    }


    private User toEntity(UserDto dto) {
        User user = new User();
        user.setFirst_name(dto.getFirst_name());
        user.setLast_name(dto.getLast_name());
        user.setBirth_date(dto.getBirth_date());
        user.setAge(dto.getAge());
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setImage(dto.getImage());
        user.setPhone_number(dto.getPhone_number());
        user.setPassword(dto.getPassword());
        user.setAccount_id(dto.getAccount_id());
        user.setAccountPassword(dto.getAccountPassword());
        user.setCreated_at(dto.getCreated_at());
        user.setUpdated_at(dto.getUpdated_at());
        return user;
    }

    private UserDto toDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setFirst_name(user.getFirst_name());
        dto.setLast_name(user.getLast_name());
        dto.setBirth_date(user.getBirth_date());
        dto.setAge(user.getAge());
        dto.setEmail(user.getEmail());
        dto.setPhone_number(user.getPhone_number());
        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());
        dto.setImage(user.getImage());
        dto.setAccountPassword(user.getAccountPassword());
        dto.setAccount_id(user.getAccount_id());
        dto.setCreated_at(user.getCreated_at());
        dto.setUpdated_at(user.getUpdated_at());
        return dto;
    }

    private boolean checkerUserEmail(String email) {
        for (User user : this.userList) {
            if (user.getEmail().equals(email)) {
                return false;
            }
        }
        return true;
    }

    private boolean checkerUserPassword(String password) {
        if (password.length() <= 7) {
            return false;

        }
        return true;
    }

    private boolean checkerUserPhone(String phone) {
        for (User user : this.userList) {
            if (user.getPhone_number().equals(phone)) {
                return false;
            }
        }
        return true;
    }


}
