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

    //TODO: work on the proper implementation
    void copyTo(String sourceString, String targetString) throws NoSuchFieldException, IllegalAccessException {

        Field source1 = UserInput.class.getDeclaredField(sourceString);
        Field destination1 = UserInput.class.getDeclaredField(targetString);
        source1.setAccessible(true);
        destination1.setAccessible(true);

//        if (source1 instanceof String && target instanceof Integer)
//            throw new IllegalArgumentException("Cant copy String to Integer");
//        if (source1 instanceof String && target instanceof Double)
//            throw new IllegalArgumentException("Cant copy String to Double");

        Object value = source1.get(this);
        destination1.set(this, value);

//        if (source instanceof Integer && target instanceof String)
//            throw new IllegalArgumentException("Cant copy String to Integer");
//        if (source instanceof Integer && target instanceof Double)
//            throw new IllegalArgumentException("Cant copy String to Integer");
//        if (source instanceof Double && target instanceof String)
//            throw new IllegalArgumentException("Cant copy String to Integer");
//        if (source instanceof Double && target instanceof Integer)
//            throw new IllegalArgumentException("Cant copy String to Integer");
//        if (source instanceof Double && target instanceof Double)
//            throw new IllegalArgumentException("Cant copy String to Integer");

    }

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
