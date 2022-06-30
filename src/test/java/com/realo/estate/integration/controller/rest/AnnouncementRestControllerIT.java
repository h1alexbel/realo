package com.realo.estate.integration.controller.rest;

import com.realo.estate.integration.TestcontainersTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static com.realo.estate.integration.controller.rest.util.UrlPathsConst.ANNOUNCEMENTS_PATH;
import static com.realo.estate.integration.controller.rest.util.UrlPathsConst.GET_ALL_BY_TITLE_ENDPOINT;
import static com.realo.estate.integration.controller.rest.util.UrlPathsConst.GET_ALL_BY_TYPE_ENDPOINT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
class AnnouncementRestControllerIT extends TestcontainersTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAll() throws Exception {
        mockMvc.perform(get(ANNOUNCEMENTS_PATH))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void getAllByTitleContaining() throws Exception {
        mockMvc.perform(get(GET_ALL_BY_TITLE_ENDPOINT)
                        .param("title", "cozy"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void getAllByType() throws Exception {
        mockMvc.perform(get(GET_ALL_BY_TYPE_ENDPOINT)
                        .param("type", "FOR_SELL"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void deleteByIdNotFound() throws Exception {
        mockMvc.perform(delete(ANNOUNCEMENTS_PATH + "/1000"))
                .andExpect(status().isNotFound());
    }
}