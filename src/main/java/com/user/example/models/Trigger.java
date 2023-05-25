package com.user.example.models;

import com.user.example.exceptions.InvalidFormat;
import org.springframework.stereotype.Component;

import static com.user.example.AppConstants.TRIGGER_MINIMUM_SPLIT;
import static com.user.example.AppConstants.TRIGGER_SOURCE_INDEX;

public class Trigger {
    private String source;
    private String logic;
    private String target;

    public Trigger(String trigger) {
        String[] split = trigger.split(" ");
        int length = split.length-1;
        if (length >= TRIGGER_MINIMUM_SPLIT) {
            this.source = split[TRIGGER_SOURCE_INDEX];
            this.target = split[length];

            StringBuilder logicBuilder = new StringBuilder();
            for (int i = 1; i < length; i++) {
                logicBuilder.append(split[i]).append(" ");
            }
            this.logic = logicBuilder.toString();
        }else
            throw new InvalidFormat("User input TRIGGER ["+trigger+"] is not in correct format");
    }

}
