package com.user.example.service;

import com.user.example.models.Action;
import com.user.example.models.Trigger;
import lombok.Getter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Getter
public class TriggerActionService {
    private Trigger trigger;
    private Action action;


    public void setAction(String action) {
        this.action = new Action(action);
    }

    public void setTrigger(String trigger) {
        this.trigger = new Trigger(trigger);
    }

    void checkTrigger(){
        performAction();
    }

    void performAction(){

    }
}
