package com.user.example.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserInput {
    private String userName;
    private int userAge;
    private double userWeight;
    private int dailyRunningKms;
    private int dailyWaterIntake;
}
