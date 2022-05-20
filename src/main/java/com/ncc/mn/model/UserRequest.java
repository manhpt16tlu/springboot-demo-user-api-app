package com.ncc.mn.model;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
