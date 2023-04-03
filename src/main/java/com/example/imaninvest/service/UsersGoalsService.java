package com.example.imaninvest.service;

import com.example.imaninvest.dto.AccountDto;
import com.example.imaninvest.dto.GoalsDto;
import com.example.imaninvest.dto.ResponseDto;
import com.example.imaninvest.dto.UsersGoalsDto;
import com.example.imaninvest.mapper.UserGoalsMapper;
import com.example.imaninvest.module.Account;
import com.example.imaninvest.module.UsersGoals;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UsersGoalsService {
    private GoalsService goalsService;
    private UserService userService;
    private List<UsersGoals> usersGoalsList;

    private UserGoalsMapper userGoalsMapper;
    private Integer index;

    public UsersGoalsService(GoalsService goalsService, UserService userService, UserGoalsMapper userGoalsMapper) {
        this.goalsService = goalsService;
        this.userService = userService;
        this.userGoalsMapper = userGoalsMapper;
        this.usersGoalsList = new ArrayList<>();
        this.index = 0;
    }

    public ResponseDto<UsersGoalsDto> create(UsersGoalsDto usersGoalsDto) {
        if (this.goalsService.getGoals(usersGoalsDto.getGoal_id()).getDate() == null) {
            return ResponseDto.<UsersGoalsDto>builder()
                    .code(-1)
                    .message("User not found!")
                    .build();
        }

        if (this.userService.getUserAcc(usersGoalsDto.getUser_id()).getDate() == null) {
            return ResponseDto.<UsersGoalsDto>builder()
                    .code(-1)
                    .message("User not found!")
                    .build();
        }

        UsersGoals usersGoals = userGoalsMapper.toEntity(usersGoalsDto);
        usersGoals.setId(++this.index);
        usersGoals.setCreated_at(LocalDateTime.now());
        this.usersGoalsList.add(usersGoals);
        return ResponseDto.<UsersGoalsDto>builder()
                .message("User successful created!")
                .success(true)
                .code(0)
                .build();
    }

    public ResponseDto<UsersGoalsDto> get(Integer id) {
        for (UsersGoals usersGoals : this.usersGoalsList) {
            if(Objects.equals(usersGoals.getId(), id)){
                return ResponseDto.<UsersGoalsDto>builder()
                        .message("Account was found")
                        .success(true)
                        .code(0)
                        .date(userGoalsMapper.toDto(usersGoals))
                        .build();
            }
        }
        return ResponseDto.<UsersGoalsDto>builder()
                .message("Account not found")
                .success(false)
                .code(-1)
                .build();
    }

    public ResponseDto<UsersGoalsDto> update(UsersGoalsDto usersGoalsDto, Integer id) {
        if (this.goalsService.getGoals(usersGoalsDto.getUser_id()).getDate() == null) {
            return ResponseDto.<UsersGoalsDto>builder()
                    .code(-1)
                    .message("User not found!")
                    .build();
        }

        if (this.userService.getUserAcc(usersGoalsDto.getUser_id()).getDate() == null) {
            return ResponseDto.<UsersGoalsDto>builder()
                    .code(-1)
                    .message("User not found!")
                    .build();
        }

        for (UsersGoals usersGoals : this.usersGoalsList) {
            if (usersGoals.getId().equals(id)) {
                usersGoals = userGoalsMapper.toEntity(usersGoalsDto);
                usersGoals.setUpdated_at(LocalDateTime.now());
                return ResponseDto.<UsersGoalsDto>builder()
                        .message("Account was updated")
                        .success(true)
                        .code(0)
                        .build();
            }
        }

        return ResponseDto.<UsersGoalsDto>builder()
                .message("Account not found")
                .success(false)
                .code(-1)
                .build();
    }

    public ResponseDto<UsersGoalsDto> delete(Integer id) {
        for (UsersGoals usersGoals : this.usersGoalsList) {
            if (usersGoals.getId().equals(id)) {
                this.usersGoalsList.remove(usersGoals);
                return ResponseDto.<UsersGoalsDto>builder()
                        .message("Account was updated")
                        .success(true)
                        .code(0)
                        .build();
            }
        }

        return ResponseDto.<UsersGoalsDto>builder()
                .message("Account not found")
                .success(false)
                .code(-1)
                .build();
    }
}