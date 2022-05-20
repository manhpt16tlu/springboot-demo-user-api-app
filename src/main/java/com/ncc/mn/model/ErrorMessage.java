package com.ncc.mn.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ErrorMessage {
    MISSING_REQUIRED_FIELD("missing required field"),
    RECORD_ALREADY_EXIST("records already exist"),
    RECORD_NOT_FOUND("record not found");
    private String message;

}
