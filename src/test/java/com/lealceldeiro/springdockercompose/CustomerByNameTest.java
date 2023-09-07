package com.lealceldeiro.springdockercompose;

import static org.hamcrest.Matchers.contains;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
@Import(AppTestConfiguration.class)
class CustomerByNameTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void customers() throws Exception {
        // when
        mockMvc.perform(MockMvcRequestBuilders.get("/customers?name={name}", "Bart"))
               .andDo(print())
               // then
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.[*].name", contains("Bart")));
    }
}
