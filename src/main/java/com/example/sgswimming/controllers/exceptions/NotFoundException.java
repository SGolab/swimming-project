package com.example.sgswimming.controllers.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NotFoundException extends RuntimeException {

    private Long id;
    private Class<?> aClass;
}
