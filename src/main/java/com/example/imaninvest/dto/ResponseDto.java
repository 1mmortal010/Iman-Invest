package com.example.imaninvest.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseDto<T> {
    private boolean success;
    private String message;
    /*
     * 0  ->OK hammasi yaxshi
     * -1 ->Not found
     * -2 ->Validation error
     * -3 ->database error
     * */
    private int code;
    private T date;
}
