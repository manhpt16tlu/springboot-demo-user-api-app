package com.ncc.mn.model;


import lombok.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
}
