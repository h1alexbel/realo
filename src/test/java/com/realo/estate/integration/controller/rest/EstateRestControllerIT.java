package com.realo.estate.integration.controller.rest;

import com.realo.estate.integration.TestcontainersTest;
import org.junit.jupiter.api.DisplayName;
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
    private static final String TYPE_PARAM = "type";

    @Test
    @DisplayName("get all default test case")
    void getAll() throws Exception {
        mockMvc.perform(get(ESTATES_PATH))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    @DisplayName("get all by default test case")
    void getAllByType() throws Exception {
        mockMvc.perform(get(GET_ALL_ESTATES_BY_TYPE_ENDPOINT)
                        .queryParam(TYPE_PARAM, "LIVING"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    @DisplayName("delete by id not found test case")
    void deleteByIdNotFound() throws Exception {
        mockMvc.perform(delete(ESTATES_PATH + "/1000"))
                .andExpect(status().isNotFound());
    }
}