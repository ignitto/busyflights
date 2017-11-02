package com.travix.medusa.busyflights.service;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusyFlightsService {
	
	@Autowired
	private SearchFlightsService flightsService;

	public List<BusyFlightsResponse> searchFlight(BusyFlightsRequest busyFlightsRequest) {
		List<BusyFlightsResponse> flights = flightsService.search(busyFlightsRequest);
		flights.sort((o1, o2) -> (int) (o1.getFare() - o2.getFare()));
		return flights;
	}

}
