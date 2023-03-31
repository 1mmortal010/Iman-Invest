package com.example.imaninvest.service;

import com.example.imaninvest.dto.AccountDto;
import com.example.imaninvest.dto.GoalsDto;
import com.example.imaninvest.dto.ResponseDto;
import com.example.imaninvest.dto.UsersGoalsDto;
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
    private Integer index;

    public UsersGoalsService(GoalsService goalsService, UserService userService) {
        this.goalsService = goalsService;
        this.userService = userService;
        this.usersGoalsList = new ArrayList<>();
        this.index = 0;
    }

    private UsersGoals toEntity(UsersGoalsDto usersGoalsDto) {
        UsersGoals usersGoals = new UsersGoals();
        usersGoals.setGoal_id(usersGoalsDto.getGoal_id());
        usersGoals.setUser_id(usersGoals.getUser_id());
        usersGoals.setCreated_at(usersGoalsDto.getCreated_at());
        usersGoals.setUpdated_at(usersGoalsDto.getUpdated_at());
        return usersGoals;
    }

    private UsersGoalsDto toDto(UsersGoals usersGoals) {
        UsersGoalsDto usersGoalsDto = new UsersGoalsDto();
        usersGoalsDto.setId(usersGoals.getId());
        usersGoalsDto.setGoal_id(usersGoals.getGoal_id());
        usersGoalsDto.setUser_id(usersGoals.getUser_id());
        usersGoalsDto.setCreated_at(usersGoals.getCreated_at());
        usersGoalsDto.setUpdated_at(usersGoals.getUpdated_at());
        return usersGoalsDto;
    }

    public ResponseDto<UsersGoalsDto> create(UsersGoalsDto usersGoalsDto) {
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

        UsersGoals usersGoals = toEntity(usersGoalsDto);
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
                        .date(toDto(usersGoals))
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
                usersGoals = toEntity(usersGoalsDto);
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