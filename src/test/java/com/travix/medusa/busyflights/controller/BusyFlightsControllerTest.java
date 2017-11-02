package com.travix.medusa.busyflights.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class BusyFlightsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private static BusyFlightsRequest request;
    private static CrazyAirRequest crazyAirRequest;
    private static ToughJetRequest toughJetRequest;

    @BeforeClass
    public static void init(){
        request = new BusyFlightsRequest();
        request.setOrigin("LHR");
        request.setDestination("LHR");
        request.setDepartureDate("2017-07-08T09:28:27.141Z");
        request.setReturnDate("2017-07-09T09:28:27.141Z");
        request.setNumberOfPassengers(1);

        crazyAirRequest = new CrazyAirRequest(request);
        toughJetRequest = new ToughJetRequest(request);
    }

    @Test
    public void testCrazyAirController() throws Exception {
        mockMvc.perform(post("/crazyair/search")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(crazyAirRequest)))
                .andExpect(status().isOk());
    }

    @Test
    public void testToughJetController() throws Exception {
        mockMvc.perform(post("/toughjet/search")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(toughJetRequest)))
                .andExpect(status().isOk());
    }

    @Test
    public void testBusyFlightsValidation_400() throws Exception {
        request.setNumberOfPassengers(0);
        mockMvc.perform(post("/busyflights/search")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().is(400));
    }


}