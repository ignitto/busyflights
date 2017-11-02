package com.travix.medusa.busyflights.controller;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.service.BusyFlightsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "busyflights")
public class BusyFlightsController {
	
	@Autowired
	private BusyFlightsService service;

	@ApiOperation(value = "Search busy Flights")
    @RequestMapping(path="/search", method = RequestMethod.POST)
	private List<BusyFlightsResponse> searchBusyFlights(@RequestBody BusyFlightsRequest busyFlightsRequest) {
    	return service.searchFlight(busyFlightsRequest);
	}
}
