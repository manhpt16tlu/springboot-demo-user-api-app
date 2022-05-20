package com.ncc.mn.controller;

import com.ncc.mn.dto.UserDTO;
import com.ncc.mn.mapper.UserMapper;
import com.ncc.mn.service.user.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
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
//    @WithMockUser(username = "mn@gmail.com", password= "456")
    void shouldReturnHello() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/user/welcome")).andExpect(MockMvcResultMatchers.status().isOk());


    }

    @Test
//    @WithMockUser(username = "mn@gmail.com",password = "456")
    void createUserShouldReturnResponseValue() throws Exception {
        UserDTO request = UserDTO.builder().firstName("a").lastName("b").email("c").password("d").build();
        UserDTO response = UserDTO.builder().firstName("a").lastName("b").email("c").password("d").build();
        Mockito.when(userService.createUser(request)).thenReturn(response);
        mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/api/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"firstName\":\"manh\",\n" +
                                "    \"lastName\":\"nguyen\",\n" +
                                "    \"email\":\"mn7@gmail.com\",\n" +
                                "    \"password\":\"456\"\n" +
                                "}")
                        .characterEncoding("utf-8")
        ).andExpect(MockMvcResultMatchers.status().isOk());
//                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("manh"));

    }


}