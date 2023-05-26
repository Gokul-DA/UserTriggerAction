package com.user.example.models;

import com.user.example.exceptions.InvalidFormatException;
import com.user.example.models.requests.UserInput;
import lombok.Getter;
import lombok.ToString;

import java.lang.reflect.Field;

import static com.user.example.AppConstants.TRIGGER_MINIMUM_SPLIT;
import static com.user.example.AppConstants.TRIGGER_SOURCE_INDEX;

@Getter
@ToString
public class Trigger {
    private String fieldName;
    private Logic logic;
    private String value;
    private Field sourceField;

    public Trigger(String trigger) throws NoSuchFieldException {
        if (trigger != null) {
            String[] split = trigger.split(" ");
            int length = split.length - 1;
            if (length >= TRIGGER_MINIMUM_SPLIT) {
                String source = split[TRIGGER_SOURCE_INDEX];
                String value = split[length];

                StringBuilder logicBuilder = new StringBuilder();
                for (int i = 1; i < length; i++) {
                    logicBuilder.append(split[i]).append(" ");
                }
                this.logic = Logic.findByValue(logicBuilder.toString().trim());

                this.sourceField = UserInput.class.getDeclaredField(source);
                this.fieldName = source;
                this.value = value;

            } else
                throw new InvalidFormatException("User input trigger [" + trigger + "] is not in correct format");
        } else
            throw new NullPointerException("trigger cannot be null");
    }


}
