package com.example.learnspring.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseError extends BaseResponse {
    private String message;
}