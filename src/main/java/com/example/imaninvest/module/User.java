package com.example.imaninvest.module;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class User {
    private Integer id;
    private String first_name;
    private String last_name;
    private LocalDate birth_date;
    private Integer age;
    private String username;
    private String password;
    private String image;
    private String phone_number;
    private String email;
    private Integer account_id;
    private String accountPassword;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}