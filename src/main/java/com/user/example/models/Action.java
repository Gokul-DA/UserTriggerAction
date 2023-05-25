package com.user.example.models;

import com.user.example.exceptions.InvalidFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import static com.user.example.AppConstants.*;

public class Action {
    private String source;
    private String target;

    public Action(String action) {
        String[] split = action.split(" ");
        int length = split.length-1;
        if (length == ACTION_MINIMUM_SPLIT) {
            this.source = split[ACTION_SOURCE_INDEX];
            this.target = split[ACTION_TARGET_INDEX];
        }else
            throw new InvalidFormat("User input ACTION ["+action+"] is not in correct format");

    }
}
