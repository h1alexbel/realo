package com.realo.estate.integration.controller.rest;

import com.realo.estate.integration.TestcontainersTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static com.realo.estate.integration.controller.rest.util.UrlPathsConst.GET_BY_NAME_ENDPOINT;
import static com.realo.estate.integration.controller.rest.util.UrlPathsConst.PROVIDERS_PATH;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
class ProviderRestControllerIT extends TestcontainersTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAll() throws Exception {
        mockMvc.perform(get(PROVIDERS_PATH))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void getByNameOk() throws Exception {
        mockMvc.perform(get(GET_BY_NAME_ENDPOINT)
                        .queryParam("name", "A100 Development"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void getByNameNotFound() throws Exception {
        mockMvc.perform(get(GET_BY_NAME_ENDPOINT)
                        .queryParam("name", "Test"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void deleteByIdNotFound() throws Exception {
        mockMvc.perform(delete(PROVIDERS_PATH + "/1000"))
                .andExpect(status().isNotFound());
    }
}