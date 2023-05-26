package com.user.example.models.requests;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TriggerActionRequest {
    private String action;
    private String trigger;
}
