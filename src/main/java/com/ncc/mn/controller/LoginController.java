//package com.ncc.mn.controller;
//
//
//import com.ncc.mn.exception.UserServiceException;
//import com.ncc.mn.model.CustomUserDetail;
//import com.ncc.mn.model.ErrorMessage;
//import com.ncc.mn.model.LoginResponse;
//import com.ncc.mn.model.UserLogin;
//import com.ncc.mn.utils.JwtTokenProvider;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping(value = "/api/login")
//public class LoginController {
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private JwtTokenProvider tokenProvider;
//
//    @PostMapping
//    public LoginResponse login(@RequestBody UserLogin userLogin){
//        if(StringUtils.isEmpty(userLogin.getEmail()) || StringUtils.isEmpty(userLogin.getPassword()))
//            throw new UserServiceException(ErrorMessage.MISSING_REQUIRED_FIELD.getMessage());
//        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
//                userLogin.getEmail(),userLogin.getPassword()
//        ));
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        // Trả về jwt cho người dùng.
//        String jwt = tokenProvider.generateToken((CustomUserDetail) authentication.getPrincipal());
//
//        LoginResponse response = LoginResponse.builder().jwt("Bearer "+jwt).message("login success").build();
//        return response;
//    }
//}
