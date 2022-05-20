package com.ncc.mn.dto;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class UserDTO {
    private Integer id;
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String encpyptPassword;
//    private String emailVerificationToken;
//    private Boolean emailVerificationStatus;

}
