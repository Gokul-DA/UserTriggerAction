package com.user.example.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TriggerActionRequest {
    private String action;
    private String trigger;
}
