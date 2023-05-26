package com.user.example.service;

import com.user.example.models.Action;
import com.user.example.models.Logic;
import com.user.example.models.Trigger;
import com.user.example.models.requests.UserInput;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;

@Service
@Getter
public class TriggerActionService {
    private Trigger trigger;
    private Action action;


    public void setAction(String action) throws NoSuchFieldException {
        this.action = new Action(action);
    }

    public void setTrigger(String trigger) throws NoSuchFieldException {
        this.trigger = new Trigger(trigger);
    }

    public void checkTrigger(UserInput userInput) throws IllegalAccessException {

        Logic logic = trigger.getLogic();
        Field sourceField = trigger.getSourceField();
        sourceField.setAccessible(true);
        boolean performAction = false;
        switch (logic) {
            case EQ:
                if (sourceField.get(userInput).equals(trigger.getValue()))
                    performAction = true;
                break;
            case GT:
                System.out.println("GT");
                if (sourceField.getDouble(userInput) > Double.parseDouble(trigger.getValue()))
                    performAction = true;
                break;
            case GTET:
                if (sourceField.getDouble(userInput) >= Double.parseDouble(trigger.getValue()))
                    performAction = true;
                break;
            case LT:
                if (sourceField.getDouble(userInput) < Double.parseDouble(trigger.getValue()))
                    performAction = true;
                break;
            case LTET:
                if (sourceField.getDouble(userInput) <= Double.parseDouble(trigger.getValue()))
                    performAction = true;
                break;
            case ET:
                if (sourceField.getDouble(userInput) == Double.parseDouble(trigger.getValue()))
                    performAction = true;
                break;
        }

        if(performAction)
            performAction();
        else
            System.out.println("Trigger condition not satisfied");
    }

    public void performAction() {
        System.out.println("TriggerActionService.performAction");
    }

    public void isInitialised() {
        if (this.trigger == null)
            throw new NullPointerException("Trigger is not defined. Please define trigger first");
        if (this.action == null)
            throw new NullPointerException("Action is not defined. Please define action first");
    }

}
