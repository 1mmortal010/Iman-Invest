package com.example.imaninvest.service;

import com.example.imaninvest.dto.InvestmentDto;
import com.example.imaninvest.dto.ResponseDto;
import com.example.imaninvest.modul.Invesment;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class InvestmentService {

    private List<Invesment> investments;
    private  Integer index;

    public  InvestmentService(){
        this.investments = new ArrayList<>();
        this.index = 0;
    }
    public ResponseDto<InvestmentDto> create(InvestmentDto dto) {
        Invesment inves = toEntity(dto);
        inves.setId(++this.index);
        inves.setCreated_at(LocalDateTime.now());
        this.investments.add(inves);
        return ResponseDto.<InvestmentDto>builder()
                .massage("Invesment successful created")
                .success(true)
                .build();
    }


    public ResponseDto<InvestmentDto> get(Integer id) {
        for(Invesment inves : this.investments){
            if (inves.getId().equals(id)){
                InvestmentDto dto = toDto(inves);

                return  ResponseDto.<InvestmentDto>builder()
                        .massage("OK")
                        .success(true)
                        .data(dto)
                        .build();
            }
        }
        return ResponseDto.<InvestmentDto>builder()
                .massage("Invesment not found")
                .success(false)
                .build();
    }

    public ResponseDto<InvestmentDto> update(InvestmentDto dto, Integer id) {
        for(Invesment inves : this.investments){
            if(inves.getId().equals(id)){
                inves  = toEntity(dto);
                this.investments.add(inves);
                return ResponseDto.<InvestmentDto>builder()
                        .massage("Invesment successful upadated")
                        .success(true)
                        .build();
            }
        }
         return ResponseDto.<InvestmentDto>builder()
                .massage("Invesment is not found")
                .success(false)
                .build();
    }

    public ResponseDto<InvestmentDto> delete(Integer id) {
        for (Invesment inves : this.investments){
            if (inves.getId().equals(id)){
                this.investments.remove(inves);
                return ResponseDto.<InvestmentDto>builder()
                        .success(true)
                        .massage("OK")
                        .build();
            }
        }
        return ResponseDto.<InvestmentDto>builder()
                .massage("Invesment not found")
                .success(false)
                .build();
    }

    private  Invesment toEntity(InvestmentDto dto){
        Invesment inves = new Invesment();
        inves.setAccount_id(dto.getAccount_id());
        inves.setDuration(dto.getDuration());
        inves.setAmount(dto.getAmount());
        inves.setUser_goals_id(dto.getUser_goals_id());
        return inves;
    }

    private  InvestmentDto toDto(Invesment inves){
        InvestmentDto dto = new InvestmentDto();
        dto.setAccount_id(inves.getAccount_id());
        dto.setAmount(inves.getAmount());
        dto.setDuration(inves.getDuration());
        dto.setUser_goals_id(inves.getUser_goals_id());
        dto.setCreated_at(inves.getCreated_at());
        return  dto;
    }
}
