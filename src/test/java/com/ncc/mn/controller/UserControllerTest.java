package com.ncc.mn.controller;

import com.ncc.mn.dto.UserDTO;
import com.ncc.mn.mapper.UserMapper;
import com.ncc.mn.model.UserRequest;
import com.ncc.mn.model.UserResponse;
import com.ncc.mn.service.user.UserService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(controllers = UserController.class)
//@SpringBootTest
//@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;
    @MockBean
    private UserMapper userMapper;

    @Test
    void shouldReturnHello() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/user/welcome")).andExpect(MockMvcResultMatchers.status().isOk());


    }

    @Test
    void createUserShouldReturn() throws Exception {
        UserDTO request = UserDTO.builder().firstName("manh").lastName("nguyen").email("mn@gmail.com").password("456").build();
        UserDTO response = UserDTO.builder().firstName("manh").lastName("nguyen").email("mn@gmail.com").build();
        Mockito.when(userService.createUser(request)).thenReturn(response);
        UserResponse createdUser = UserResponse.builder().email("mn@gmail.com").firstName("manh").lastName("nguyen").build();
        Mockito.when(userMapper.userDtoToUserResponse(response)).thenReturn(createdUser);
        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/api/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"firstName\":\"manh\",\n" +
                                "    \"lastName\":\"nguyen\",\n" +
                                "    \"email\":\"mn@gmail.com\",\n" +
                                "    \"password\":\"456\"\n" +
                                "}")
                        .characterEncoding("utf-8")
        ).andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("manh"));
    }


}