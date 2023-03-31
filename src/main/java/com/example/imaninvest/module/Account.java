package com.example.imaninvest.module;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Account {
    private Integer id;
    private Integer user_id;
    private Integer currency_id;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}