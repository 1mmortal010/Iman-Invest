package com.example.imaninvest.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
@Getter
@Setter
public class AccountDto {
    private Integer id;
    private Integer user_id;
    private Integer currency_id;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}
