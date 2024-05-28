package com.brightkut.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)// add this because we need to call msg from super class
@Data
public class CustomerNotFoundException extends RuntimeException {
    private final String msg;
}
