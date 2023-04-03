package com.example.imaninvest.service;

import com.example.imaninvest.dto.InvestmentDto;
import com.example.imaninvest.dto.ResponseDto;
import com.example.imaninvest.modul.Invesment;
import com.example.imaninvest.service.mapper.InvestmentMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class InvestmentService {

    private List<Invesment> investments;
    private  Integer index;
    private InvestmentMapper mapper;

    public  InvestmentService(InvestmentMapper mapper){
        this.investments = new ArrayList<>();
        this.mapper = mapper;
        this.index = 0;
    }
    public ResponseDto<InvestmentDto> create(InvestmentDto dto) {
        Invesment inves = mapper.toEntity(dto);
        inves.setId(++this.index);
        inves.setCreated_at(LocalDateTime.now());
        this.investments.add(inves);
        return ResponseDto.<InvestmentDto>builder()
                .message("Invesment successful created")
                .success(true)
                .build();
    }


    public ResponseDto<InvestmentDto> get(Integer id) {
        for(Invesment inves : this.investments){
            if (inves.getId().equals(id)){
                InvestmentDto dto = mapper.toDto(inves);

                return  ResponseDto.<InvestmentDto>builder()
                        .message("OK")
                        .success(true)
                        .data(dto)
                        .build();
            }
        }
        return ResponseDto.<InvestmentDto>builder()
                .message("Invesment not found")
                .success(false)
                .build();
    }

    public ResponseDto<InvestmentDto> update(InvestmentDto dto, Integer id) {
        for(Invesment inves : this.investments){
            if(inves.getId().equals(id)){
                inves  = mapper.toEntity(dto);
                this.investments.add(inves);
                return ResponseDto.<InvestmentDto>builder()
                        .message("Invesment successful upadated")
                        .success(true)
                        .build();
            }
        }
         return ResponseDto.<InvestmentDto>builder()
                .message("Invesment is not found")
                .success(false)
                .build();
    }

    public ResponseDto<InvestmentDto> delete(Integer id) {
        for (Invesment inves : this.investments){
            if (inves.getId().equals(id)){
                this.investments.remove(inves);
                return ResponseDto.<InvestmentDto>builder()
                        .success(true)
                        .message("OK")
                        .build();
            }
        }
        return ResponseDto.<InvestmentDto>builder()
                .message("Invesment not found")
                .success(false)
                .build();
    }


}
