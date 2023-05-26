package com.user.example.exceptions;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ExceptionTestCases {

    @Test
    void invalidFormat(){
        new InvalidFormatException("Invalid Format");
    }
    @Test
    void invalidInput(){
        new InvalidInputException("Invalid INput");
    }
}
