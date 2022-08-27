package com.realo.estate.integration.controller.rest;

import com.realo.estate.integration.TestcontainersTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static com.realo.estate.integration.controller.rest.util.UrlPathsConst.ANNOUNCEMENTS_PATH;
import static com.realo.estate.integration.controller.rest.util.UrlPathsConst.
    GET_ALL_BY_TITLE_ENDPOINT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
class AnnouncementRestControllerIT extends TestcontainersTest {

  private static final String TITLE_PARAM = "title";

  @Autowired
  private MockMvc mockMvc;

  @Test
  @DisplayName("get all by title containing default test case")
  void getAllByTitleContaining() throws Exception {
    mockMvc.perform(get(GET_ALL_BY_TITLE_ENDPOINT)
            .param(TITLE_PARAM, "cozy"))
        .andExpect(status().is2xxSuccessful());
  }

  @Test
  @DisplayName("delete by id not found test case")
  void deleteByIdNotFound() throws Exception {
    mockMvc.perform(delete(ANNOUNCEMENTS_PATH + "/1000"))
        .andExpect(status().isNotFound());
  }
}