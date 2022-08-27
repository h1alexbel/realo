package com.realo.estate.integration.controller.rest;

import com.realo.estate.integration.TestcontainersTest;
import org.junit.jupiter.api.DisplayName;
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

  private static final String LOGIN_PARAM = "login";

  @Autowired
  private MockMvc mockMvc;

  @Test
  @DisplayName("get by name default test case")
  void getByNameOk() throws Exception {
    mockMvc.perform(get(GET_BY_LOGIN_ENDPOINT)
            .queryParam(LOGIN_PARAM, "jog123"))
        .andExpect(status().is2xxSuccessful());
  }

  @Test
  @DisplayName("get by name not found test case")
  void getByNameNotFound() throws Exception {
    mockMvc.perform(get(GET_BY_LOGIN_ENDPOINT)
            .queryParam(LOGIN_PARAM, "Test"))
        .andExpect(status().is4xxClientError());
  }

  @Test
  @DisplayName("delete by id not found test case")
  void deleteByIdNotFound() throws Exception {
    mockMvc.perform(delete(USERS_PATH + "/1000"))
        .andExpect(status().isNotFound());
  }
}