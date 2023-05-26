package com.user.example.models;

import com.user.example.exceptions.InvalidFormatException;
import com.user.example.models.requests.UserInput;
import lombok.Getter;
import lombok.ToString;

import java.lang.reflect.Field;

import static com.user.example.AppConstants.*;

@Getter
@ToString
public class Action {
    private String source;
    private String target;
    private Field sourceField;
    private Field targetField;

    public Action(String action) throws NoSuchFieldException {

        if (action != null) {
            String[] split = action.split(SPACE);
            int length = split.length - 1;
            if (length == ACTION_MINIMUM_SPLIT) {
                String source = split[ACTION_SOURCE_INDEX];
                String target = split[ACTION_TARGET_INDEX];
                this.sourceField = UserInput.class.getDeclaredField(source);
                this.targetField = UserInput.class.getDeclaredField(target);
                this.source = source;
                this.target = target;
            } else
                throw new InvalidFormatException("User input action [" + action + "] is not in correct format. Please follow \"copy <x> to <y>\" format");
        } else
            throw new NullPointerException("action cannot be null");


    }
}
