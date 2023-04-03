package com.example.imaninvest.service.mapper;

import com.example.imaninvest.dto.InvestmentDto;
import com.example.imaninvest.modul.Invesment;
import org.springframework.stereotype.Component;

@Component
public class InvestmentMapper {
    public Invesment toEntity(InvestmentDto dto){
        Invesment inves = new Invesment();
        inves.setAccount_id(dto.getAccount_id());
        inves.setDuration(dto.getDuration());
        inves.setAmount(dto.getAmount());
        inves.setUser_goals_id(dto.getUser_goals_id());
        return inves;
    }

    public   InvestmentDto toDto(Invesment inves){
        InvestmentDto dto = new InvestmentDto();
        dto.setAccount_id(inves.getAccount_id());
        dto.setAmount(inves.getAmount());
        dto.setDuration(inves.getDuration());
        dto.setUser_goals_id(inves.getUser_goals_id());
        dto.setCreated_at(inves.getCreated_at());
        return  dto;
    }
}
