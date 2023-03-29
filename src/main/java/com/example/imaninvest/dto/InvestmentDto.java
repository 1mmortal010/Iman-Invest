package com.example.imaninvest.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class InvestmentDto {
    private int  account_id;
    private float amount;
    private LocalDateTime created_at;
    private int user_goals_id;
    private  int duration;
}
