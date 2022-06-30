package com.realo.estate.integration.controller.rest;

import com.realo.estate.integration.TestcontainersTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static com.realo.estate.integration.controller.rest.util.UrlPathsConst.ESTATES_PATH;
import static com.realo.estate.integration.controller.rest.util.UrlPathsConst.GET_ALL_ESTATES_BY_TYPE_ENDPOINT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
class EstateRestControllerIT extends TestcontainersTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAll() throws Exception {
        mockMvc.perform(get(ESTATES_PATH))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void getAllByType() throws Exception {
        mockMvc.perform(get(GET_ALL_ESTATES_BY_TYPE_ENDPOINT)
                        .queryParam("type", "LIVING"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void deleteNotFound() throws Exception {
        mockMvc.perform(delete(ESTATES_PATH + "/1000"))
                .andExpect(status().isNotFound());
    }
}