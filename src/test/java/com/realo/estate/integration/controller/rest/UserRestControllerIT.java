package com.realo.estate.integration.controller.rest;

import com.realo.estate.integration.TestcontainersTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static com.realo.estate.integration.controller.rest.util.UrlPathsConst.GET_BY_LOGIN_ENDPOINT;
import static com.realo.estate.integration.controller.rest.util.UrlPathsConst.USERS_PATH;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
class UserRestControllerIT extends TestcontainersTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getByNameOk() throws Exception {
        mockMvc.perform(get(GET_BY_LOGIN_ENDPOINT)
                        .queryParam("login", "jog123"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void getByNameNotFound() throws Exception {
        mockMvc.perform(get(GET_BY_LOGIN_ENDPOINT)
                        .queryParam("login", "Test"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void getAll() throws Exception {
        mockMvc.perform(get(USERS_PATH))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void deleteByIdNotFound() throws Exception {
        mockMvc.perform(delete(USERS_PATH + "/1000"))
                .andExpect(status().isNotFound());
    }
}