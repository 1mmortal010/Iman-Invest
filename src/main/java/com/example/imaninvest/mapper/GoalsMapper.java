package com.example.imaninvest.mapper;

import com.example.imaninvest.dto.GoalsDto;
import com.example.imaninvest.module.Goals;
import org.springframework.stereotype.Component;

@Component
public class GoalsMapper {
    public GoalsDto toDto(Goals goals) {
        GoalsDto goalsDto = new GoalsDto();
        goalsDto.setId(goals.getId());
        goalsDto.setText(goals.getText());
        return goalsDto;
    }
    //turning the GoalsDto into Goals
    public Goals toEntity(GoalsDto goalsDto) {
        Goals goals = new Goals();
        goals.setText(goalsDto.getText());
        return goals;
    }
}