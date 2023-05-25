package com.user.example.controller;

import com.user.example.exceptions.InvalidFormat;
import com.user.example.service.TriggerActionService;
import com.user.example.models.TriggerActionRequest;
import com.user.example.models.UserInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerClass {

    @Autowired
    TriggerActionService triggerActionService;

    @PostMapping(path = {"/setTriggerAndAction"},consumes = {"application/json"})
    ResponseEntity<Object> setTriggerAndAction(@RequestBody TriggerActionRequest triggerActionRequest){
        System.out.println("triggerActionRequest = " + triggerActionRequest);
        try {
            triggerActionService.setAction(triggerActionRequest.getAction());
            triggerActionService.setTrigger(triggerActionRequest.getTrigger());
        }catch (InvalidFormat invalidFormat){
            return ResponseEntity.badRequest().body(invalidFormat.getMessage());
        }

        return ResponseEntity.accepted().body(null);
    }

//    @PostMapping(path = {"/setAction"},consumes = {"application/json"})
//    ResponseEntity<Object> setAction(){
//        return ResponseEntity.accepted().body(null);
//    }

    @PostMapping(path = {"/setInput"},consumes = {"application/json"})
    void setInput(@RequestBody UserInput userInput){

    }
}
