package com.example.learnspring.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDetailResponse extends BaseResponse {
    private Long id;
    private String name;
}
