package com.user.example.controller;

import com.user.example.models.requests.TriggerActionRequest;
import com.user.example.models.requests.UserInput;
import com.user.example.service.TriggerActionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class ControllerClassTest {

    @InjectMocks
    ControllerClass controllerClass;

    @Spy
    TriggerActionService triggerActionService;

    @Spy
    TriggerActionRequest triggerActionRequest;

    @Spy
    UserInput userInput;

    @BeforeEach
    void init() throws NoSuchFieldException {
        triggerActionRequest.setAction("copy dailyRunningKms to userAge");
        triggerActionRequest.setTrigger("userAge equals to 50");
        triggerActionService.setAction("copy dailyRunningKms to userAge");
        triggerActionService.setTrigger("userAge equals to 50");
        userInput.setUserAge(2);
        userInput.setUserName("Gokul");
        userInput.setDailyRunningKms(1.0);
        userInput.setUserWeight(55.2);
        userInput.setDailyWaterIntake(5.2);
    }
    @Test
    void setTriggerAndAction() {
        ResponseEntity<Object> objectResponseEntity = controllerClass.setTriggerAndAction(triggerActionRequest);
        Assertions.assertNotNull(objectResponseEntity);
    }

    @Test
    void setInput() {
        ResponseEntity<Object> objectResponseEntity = controllerClass.setInput(userInput);
        Assertions.assertNotNull(objectResponseEntity);
    }
}