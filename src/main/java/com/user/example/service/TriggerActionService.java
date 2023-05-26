package com.user.example.service;

import com.user.example.models.Action;
import com.user.example.models.Logic;
import com.user.example.models.Trigger;
import com.user.example.models.requests.UserInput;
import lombok.Getter;
import org.apache.catalina.User;
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
            performAction(userInput);
        else
            System.out.println("Trigger condition not satisfied");
    }

    public void performAction(UserInput userInput) throws IllegalAccessException {
        System.out.println("TriggerActionService.performAction");
        System.out.println("Before updating "+userInput);


//        Object a = sourceField.get(userInput);
//        targetField.set(userInput,a);
        copyTo(userInput);

        System.out.println("After updating "+userInput);
    }

    public void isInitialised() {
        if (this.trigger == null)
            throw new NullPointerException("Trigger is not defined. Please define trigger first");
        if (this.action == null)
            throw new NullPointerException("Action is not defined. Please define action first");
    }

    public void copyTo(UserInput userInput) throws IllegalAccessException {
        Field sourceField = this.action.getSourceField();
        Field targetField = this.action.getTargetField();
        sourceField.setAccessible(true);
        targetField.setAccessible(true);

        if (sourceField.getType() == String.class)
            throw new IllegalArgumentException("Cant copy String to Integer/Double");
        if (targetField.getType() == String.class)
            throw new IllegalArgumentException("Cant copy Integer/Double to String");

        Object value = new Object();
        if (sourceField.getType() == int.class && targetField.getType() == double.class)
            value = sourceField.getDouble(userInput);

        if (sourceField.getType() == double.class && targetField.getType() == int.class)
            value = (int) sourceField.getDouble(userInput);
        if (sourceField.getType() == double.class && targetField.getType() == double.class)
            value = sourceField.getDouble(userInput);

        targetField.set(userInput, value);
    }

}
