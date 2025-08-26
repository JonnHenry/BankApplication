package com.devsu.hackerearth.backend.account.model.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseErrorDto {

    private int code;
    private String error;
    private String message;
    private LocalDateTime timestamp;
    
}
