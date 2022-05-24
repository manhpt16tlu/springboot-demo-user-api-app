package com.ncc.mn.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ncc.mn.dto.UserDTO;
import com.ncc.mn.mapper.UserMapper;
import com.ncc.mn.model.ExceptionResponse;
import com.ncc.mn.model.UserRequest;
import com.ncc.mn.model.UserResponse;
import com.ncc.mn.perform.UserControllerPerform;
import com.ncc.mn.service.user.UserService;
import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(controllers = UserController.class)
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;
    @MockBean
    private UserMapper userMapper;

    @Test
    void createUserShouldReturn201() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"firstName\":\"manh\",\n" +
                        "    \"lastName\":\"nguyen\",\n" +
                        "    \"email\":\"mn@gmail.com\",\n" +
                        "    \"password\":\"456\"\n" +
                        "}")
                .characterEncoding("utf-8")
        ).andExpect(MockMvcResultMatchers.status().isCreated());

    }

    @Test
    void createUserShouldReturn500() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/api/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"firstName\":\"manh\",\n" +
                        "    \"lastName\":\"nguyen\",\n" +
                        "    \"email\":\"mn@gmail.com\"\n" +
                        "}")
                .characterEncoding("utf-8")
        ).andExpect(MockMvcResultMatchers.status().is5xxServerError());
    }

    @Test
    @DisplayName("should return error message when missing field")
    void createUserShouldReturnErrMessage() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .post("/api/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"firstName\":\"manh\",\n" +
                        "    \"lastName\":\"nguyen\",\n" +
                        "    \"email\":\"mn@gmail.com\"\n" +
                        "}")
                .characterEncoding("utf-8")
        ).andReturn();
        ObjectMapper objectMapper = new ObjectMapper();
        String actualResponse = mvcResult.getResponse().getContentAsString();
        String actualMessage = objectMapper.readValue(actualResponse, ExceptionResponse.class).getMessage();
        Assertions.assertThat(actualMessage).isEqualTo("missing required field");

    }

    @Test
    @DisplayName("should return object response when making post request to /api/user")
    void createUserShouldReturnObject() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        UserDTO userDTO = UserDTO.builder().firstName("manh").lastName("nguyen").email("mn@gmail.com").password("123").build();
        Mockito.when(userMapper.userRequestToUserDTO(ArgumentMatchers.any(UserRequest.class))).thenReturn(userDTO);
        Mockito.when(userService.createUser(userDTO)).thenReturn(userDTO);
        UserResponse userResponse = UserResponse.builder().firstName("manh").lastName("nguyen").email("mn@gmail.com").build();
        Mockito.when(userMapper.userDtoToUserResponse(userDTO)).thenReturn(userResponse);

        String content ="{\n" +
                "    \"firstName\":\"manh\",\n" +
                "    \"lastName\":\"nguyen\",\n" +
                "    \"email\":\"mn@gmail.com\",\n" +
                "    \"password\":\"123\"\n" +
                "}";
        String endpoint = "/api/user";
        MvcResult mvcResult = UserControllerPerform.getPostRequestResult(mockMvc,endpoint,content);
        Assertions.assertThat(mvcResult.getResponse().getStatus()).isEqualTo(201);
        UserResponse actualRespone = objectMapper.readValue(mvcResult.getResponse().getContentAsString(),UserResponse.class);
        Assertions.assertThat(actualRespone.getEmail()).isEqualTo("mn@gmail.com");
        Assertions.assertThat(actualRespone.getFirstName()).isEqualTo("manh");
    }

    @Test
    @DisplayName("should return list user")
    void getAllUser() throws Exception {
        String endpoint = "/api/user";
        MvcResult mvcResult = UserControllerPerform.getListUserRequestResult(mockMvc,endpoint);
        Assertions.assertThat(mvcResult.getResponse().getStatus()).isEqualTo(201);
    }


}