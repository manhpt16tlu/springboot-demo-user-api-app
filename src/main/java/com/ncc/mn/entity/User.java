package com.ncc.mn.entity;

import lombok.*;

import javax.persistence.*;


@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String userId;
    @Column(nullable = false, length = 100)
    private String firstName;
    @Column(nullable = false, length = 100)
    private String lastName;
    @Column(nullable = false, length = 100)
    private String email;
    @Column(nullable = false)
    private String encpyptPassword;

//    private String emailVerificationToken;
//
//    @Column(nullable = false,columnDefinition = "boolean default false")
//    private Boolean emailVerificationStatus = false;

}
