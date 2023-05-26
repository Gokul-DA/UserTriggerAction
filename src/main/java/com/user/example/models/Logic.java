package com.user.example.models;

public enum Logic {
    EQ("equals"),
    GT("greater than"),
    GTET("greater than or equals to"),
    LT("less than"),
    LTET("less than or equals to"),
    ET("equals to");

    private String logic;

    Logic(String logic) {
        this.logic = logic;
    }

    public static Logic findByValue(String val) {
        for (Logic entry : Logic.values())
            if (entry.getLogic().equals(val))
                return entry;
        throw new IllegalArgumentException("Unexpected value '" + val + "' for 'Logic' enum.");
    }

    public String getLogic() {
        return this.logic;
    }

    @Override
    public String toString() {
        return this.logic;
    }
}
