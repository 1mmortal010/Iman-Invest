package com.example.imaninvest.service;

import com.example.imaninvest.dto.CurrencyDto;
import com.example.imaninvest.dto.GoalsDto;
import com.example.imaninvest.dto.ResponseDto;
import com.example.imaninvest.module.Currency;
import com.example.imaninvest.module.Goals;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class GoalsService {

    private List<Goals> goalsList;
    private Integer index;

    public GoalsService(){
        this.goalsList = new ArrayList<>();
        this.index = 0;
    }
    //turning the Goals into GoalsDto
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

    public ResponseDto<GoalsDto> create(GoalsDto goalsDto) {
        Goals goals = toEntity(goalsDto);
        goals.setId(++this.index);
        this.goalsList.add(goals);
        return ResponseDto.<GoalsDto>builder()
                .message("Goals was created")
                .success(true)
                .code(0)
                .date(goalsDto)
                .build();
    }

    public ResponseDto<GoalsDto> get(Integer id) {
        for (Goals goals : this.goalsList) {
            if (goals.getId().equals(id)) {
                GoalsDto goalsDto = toDto(goals);
                return ResponseDto.<GoalsDto>builder()
                        .message("Goals was found")
                        .success(true)
                        .code(0)
                        .date(goalsDto)
                        .build();
            }
        }
        return ResponseDto.<GoalsDto>builder()
                .message("Goals was not found")
                .success(false)
                .code(-1)
                .build();
    }

    public ResponseDto<GoalsDto> update(GoalsDto goalsDto, Integer id) {
        for (Goals goals : this.goalsList) {
            if (goals.getId().equals(id)) {
                goals = toEntity(goalsDto);
                this.goalsList.add(goals);
                return ResponseDto.<GoalsDto>builder()
                        .message("Goals was updated")
                        .success(true)
                        .code(0)
                        .build();
            }
        }
        return ResponseDto.<GoalsDto>builder()
                .message("Goals was not found")
                .success(false)
                .code(-1)
                .build();
    }

    public ResponseDto<GoalsDto> delete(Integer id) {
        for (Goals goals : this.goalsList) {
            if (goals.getId().equals(id)) {
                this.goalsList.remove(goals);
                return ResponseDto.<GoalsDto>builder()
                        .message("Goals was deleted")
                        .success(true)
                        .code(0)
                        .build();
            }
        }
        return ResponseDto.<GoalsDto>builder()
                .message("Goals was not found")
                .success(false)
                .code(-1)
                .build();
    }

    public ResponseDto<List<GoalsDto>> getAll() {
        if (goalsList.size() == 0) {
            return ResponseDto.<List<GoalsDto>>builder()
                    .message("Currency list is empty")
                    .success(false)
                    .code(0)
                    .build();
        }
        List<GoalsDto> goalsDtoList = this.goalsList
                .stream()
                .map(this::toDto)
                .toList();
        return ResponseDto.<List<GoalsDto>>builder()
                .message("Ok")
                .code(0)
                .success(true)
                .date(goalsDtoList)
                .build();
    }

    public ResponseDto<GoalsDto> getGoals(Integer id) {
        for (Goals goals : this.goalsList) {
            if (goals.getId().equals(id)) {
                return ResponseDto.<GoalsDto>builder()
                        .message("ok")
                        .code(0)
                        .success(true)
                        .date(toDto(goals))
                        .build();
            }
        }
        return ResponseDto.<GoalsDto>builder()
                .message("Currency is not found!")
                .code(-1)
                .build();
    }
}
