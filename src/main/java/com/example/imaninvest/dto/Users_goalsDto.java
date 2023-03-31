package com.example.imaninvest.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class Users_goalsDto {
    private Integer id;
    private Integer user_id;
    private Integer goal_id;
    private Float amount;
    private Float monthly_investment;
    private LocalDateTime estimated_date;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private LocalDateTime deleted_at;
}