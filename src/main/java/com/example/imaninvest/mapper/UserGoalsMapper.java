package com.example.imaninvest.mapper;

import com.example.imaninvest.dto.UsersGoalsDto;
import com.example.imaninvest.module.UsersGoals;
import org.springframework.stereotype.Component;

@Component
public class UserGoalsMapper {
    public UsersGoals toEntity(UsersGoalsDto usersGoalsDto) {
        UsersGoals usersGoals = new UsersGoals();
        usersGoals.setGoal_id(usersGoalsDto.getGoal_id());
        usersGoals.setUser_id(usersGoals.getUser_id());
        usersGoals.setCreated_at(usersGoalsDto.getCreated_at());
        usersGoals.setUpdated_at(usersGoalsDto.getUpdated_at());
        return usersGoals;
    }

    public UsersGoalsDto toDto(UsersGoals usersGoals) {
        UsersGoalsDto usersGoalsDto = new UsersGoalsDto();
        usersGoalsDto.setId(usersGoals.getId());
        usersGoalsDto.setGoal_id(usersGoals.getGoal_id());
        usersGoalsDto.setUser_id(usersGoals.getUser_id());
        usersGoalsDto.setCreated_at(usersGoals.getCreated_at());
        usersGoalsDto.setUpdated_at(usersGoals.getUpdated_at());
        return usersGoalsDto;
    }
}