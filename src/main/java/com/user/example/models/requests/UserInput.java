package com.user.example.models.requests;

import com.user.example.exceptions.InvalidInputException;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.lang.reflect.Field;

@Setter
@Getter
@ToString
public class UserInput {
    private String userName;
    private int userAge;
    private double userWeight;
    private double dailyRunningKms;
    private double dailyWaterIntake;

    public void validateRequest() {
        if (this.userName == null || this.userName.isEmpty() || this.userName.isBlank())
            throw new InvalidInputException("userName cannot be null or empty");
        if (this.userAge < 1)
            throw new InvalidInputException("Age cannot be less than 1");
        if (this.userAge >= 130)
            throw new InvalidInputException("Age cannot be more than 130");

        if (this.userWeight == 0)
            throw new InvalidInputException("Weight cannot be 0");
        if (this.userWeight < 0)
            throw new InvalidInputException("Weight cannot be less than 0");

        if (this.dailyRunningKms < 0)
            throw new InvalidInputException("DailyRunningKms cannot be less than 0");
        if (this.dailyWaterIntake < 0)
            throw new InvalidInputException("DailyWaterIntake cannot be less than 0");
    }
}
