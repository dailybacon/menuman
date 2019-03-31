package com.beefyboys.menuman.controllers;

import com.beefyboys.menuman.MenumanApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = WebEnvironment.RANDOM_PORT,
        classes = MenumanApplication.class)
@AutoConfigureMockMvc

public class MenuControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    // complete this later

    @Test
    public void shouldReturnStatusIsOkWhenGetAllMenus() throws Exception {
        mvc.perform(get("/api/menu").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
