package com.ncc.mn.model;

import lombok.*;

import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ExceptionResponse {
    private Date timestamp;
    private String message;
}
