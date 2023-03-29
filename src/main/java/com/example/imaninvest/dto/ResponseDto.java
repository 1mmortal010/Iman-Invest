package com.example.imaninvest.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseDto<T> {

    private  boolean success;

    private String massage;

    private  int code;
    /*
    *0 - all good
    * -1 - not found
    * -2 - validation error
    * -3 - database error
     */

    private  T data;

}
