package com.example.imaninvest.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurrencyDto {
    private Integer id;
    private String name;
    private String short_name;
    private Double usd_difference;


}
