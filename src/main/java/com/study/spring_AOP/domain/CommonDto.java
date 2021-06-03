package com.study.spring_AOP.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonDto <T> {
    private int statusCode;
    private T data;
}
