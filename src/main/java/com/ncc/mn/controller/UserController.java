package com.ncc.mn.controller;

import com.ncc.mn.dto.UserDTO;
import com.ncc.mn.exception.UserServiceException;
import com.ncc.mn.mapper.UserMapper;
import com.ncc.mn.model.ErrorMessage;
import com.ncc.mn.model.OperationStatusResp;
import com.ncc.mn.model.UserRequest;
import com.ncc.mn.model.UserResponse;
import com.ncc.mn.service.user.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

//    @GetMapping(value = "/hello")
//    public ResponseEntity<String> hello(){
//        return new ResponseEntity<>(null,HttpStatus.OK);
//    }

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest) throws Exception {
        if (StringUtils.isEmpty(userRequest.getEmail()) || StringUtils.isEmpty(userRequest.getFirstName()) || StringUtils.isEmpty(userRequest.getPassword()))
            throw new UserServiceException(ErrorMessage.MISSING_REQUIRED_FIELD.getMessage());
        UserDTO userDTO = userMapper.userRequestToUserDTO(userRequest);

        UserDTO createdUser = userService.createUser(userDTO);

        UserResponse returnValue = userMapper.userDtoToUserResponse(createdUser);

        return new ResponseEntity<>(returnValue, HttpStatus.CREATED);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public Page<UserResponse> getAllUser(@RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "limit", defaultValue = "3") int limit) {
        Page<UserDTO> listUsers = userService.getAllUser(page, limit);
        Page<UserResponse> returnValue = listUsers.map(userMapper::userDtoToUserResponse);
        return returnValue;
    }

    @GetMapping(value = {"/{userId}"}, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public UserResponse getUser(@PathVariable String userId) {
        UserDTO findedUser = userService.getUser(userId);
        UserResponse returnValue = userMapper.userDtoToUserResponse(findedUser);
        return returnValue;
    }

    @PutMapping(value = "/{userId}")
    public UserResponse updateUser(@PathVariable String userId, @RequestBody UserRequest userRequest) {
        UserDTO userDTO = userMapper.userRequestToUserDTO(userRequest);
        UserDTO updatedUser = userService.updateUser(userId, userDTO);
        UserResponse returnValue = userMapper.userDtoToUserResponse(updatedUser);
        return returnValue;
    }

    @DeleteMapping(value = "/{userId}")
    public OperationStatusResp deleteUser(@PathVariable String userId) {
        OperationStatusResp operationStatusResp;
        userService.deleteUser(userId);
        operationStatusResp = OperationStatusResp.builder().operation("DELETE").status("success").build();
        return operationStatusResp;
    }

}
