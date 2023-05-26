package com.user.example.controller;

import com.user.example.exceptions.InvalidFormatException;
import com.user.example.exceptions.InvalidInputException;
import com.user.example.models.requests.TriggerActionRequest;
import com.user.example.models.requests.UserInput;
import com.user.example.service.TriggerActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerClass {

    @Autowired
    TriggerActionService triggerActionService;

    @PostMapping(path = {"/triggerAction"}, consumes = {"application/json"}, produces = {"application/json","text/plain"})
    ResponseEntity<Object> setTriggerAndAction(@RequestBody TriggerActionRequest triggerActionRequest) {
        System.out.println("triggerActionRequest = " + triggerActionRequest);
        try {
            triggerActionService.setAction(triggerActionRequest.getAction());
            triggerActionService.setTrigger(triggerActionRequest.getTrigger());
        } catch (InvalidFormatException | NullPointerException | IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (NoSuchFieldException e) {
            return ResponseEntity.badRequest().body("No Such field [" + e.getMessage() + "]. Please define a proper action or trigger");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
        return ResponseEntity.accepted().body(null);
    }

    @PostMapping(path = {"/userInput"}, consumes = {"application/json"}, produces = {"application/json"})
    ResponseEntity<Object> setInput(@RequestBody UserInput userInput) {
        System.out.println("Request userInput = " + userInput);
        try {
            triggerActionService.isInitialised();
            userInput.validateRequest();
            triggerActionService.checkTrigger(userInput);
            System.out.println("After updating userInput = " + userInput);
        } catch (NullPointerException | InvalidInputException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
        return ResponseEntity.accepted().body(userInput);
    }
}
