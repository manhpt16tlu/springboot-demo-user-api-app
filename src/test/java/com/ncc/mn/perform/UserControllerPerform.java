package com.ncc.mn.perform;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public class UserControllerPerform {
    public static MvcResult getPostRequestResult(MockMvc mockMvc, String endpoint, String content) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders
                .post(endpoint)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content)
                .characterEncoding("utf-8")
        ).andReturn();
    }

    public static MvcResult getListUserRequestResult(MockMvc mockMvc, String endpoint) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders
                .get(endpoint)
                .param("page","0")
                .param("limit","3")
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn();
    }

    public static MvcResult getUserRequestResult(MockMvc mockMvc,String endpoint) throws Exception{
        return mockMvc.perform(MockMvcRequestBuilders
                .get(endpoint,"123")
                .contentType(MediaType.APPLICATION_JSON)
        ).andReturn();
    }
}
