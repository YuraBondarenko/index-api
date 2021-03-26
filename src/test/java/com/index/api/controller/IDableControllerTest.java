package com.index.api.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.index.api.dto.request.CityRequestDto;
import com.index.api.dto.request.CountryRequestDto;
import com.index.api.dto.request.HumanRequestDto;
import com.index.api.dto.response.CityResponseDto;
import com.index.api.dto.response.CountryResponseDto;
import com.index.api.dto.response.HumanResponseDto;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class IDableControllerTest {
    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void saveAndGetCountry_Ok() throws Exception {
        String requestContent = objectMapper
                .writeValueAsString(new CountryRequestDto().setId(1L).setName("country"));
        String responseContent = objectMapper
                .writeValueAsString(new CountryResponseDto().setId(1L).setName("country"));
        mockMvc.perform(post("/country").content(requestContent)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().string(responseContent));
        mockMvc.perform(get("/country/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(responseContent));
        mockMvc.perform(get("/country/all"))
                .andExpect(status().isOk())
                .andExpect(content().string(List.of(responseContent).toString()));
    }

    @Test
    public void saveAndGetHuman_Ok() throws Exception {
        String requestContent = objectMapper
                .writeValueAsString(new HumanRequestDto().setId(1L).setName("human").setAge(23));
        String responseContent = objectMapper
                .writeValueAsString(new HumanResponseDto().setId(1L).setName("human").setAge(23));
        mockMvc.perform(post("/human").content(requestContent)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().string(responseContent));
        mockMvc.perform(get("/human/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(responseContent));
        mockMvc.perform(get("/human/all"))
                .andExpect(status().isOk())
                .andExpect(content().string(List.of(responseContent).toString()));
    }

    @Test
    public void saveAndGetCities_Ok() throws Exception {
        for (int i = 0; i < 100; i++) {
            String requestContent = objectMapper
                    .writeValueAsString(new CityRequestDto().setId((long) i).setName("city" + i));
            String responseContent = objectMapper
                    .writeValueAsString(new CityResponseDto().setId((long) i).setName("city" + i));

            mockMvc.perform(post("/city").content(requestContent)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isCreated())
                    .andExpect(content().string(responseContent));
            mockMvc.perform(get("/city/" + i))
                    .andExpect(status().isOk())
                    .andExpect(content().string(responseContent));
        }
    }
}
