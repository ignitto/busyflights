package com.travix.medusa.busyflights.service;

import com.travix.medusa.busyflights.converter.BusyFlightsConverter;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class SearchFlightsService {
	
	@Value("${crazyair.api}")
	private String crazyAirAPI;

	@Value("${toughjet.api}")
	private String toughJetAPI;
	
	@Autowired
	private BusyFlightsConverter busyFlightsConverter;

	@Autowired
	private RestTemplate restTemplate;
	
	public List<BusyFlightsResponse> search(BusyFlightsRequest busyFlightsRequest) {
		List<BusyFlightsResponse> crazyAirResponseList = getFromCrazyAir(busyFlightsRequest);
		List<BusyFlightsResponse> toughJetResponseList = getFromToughJet(busyFlightsRequest);
		return Stream.concat(
				crazyAirResponseList.stream(),
				toughJetResponseList.stream()
		).collect(Collectors.toList());
	}

	private List<BusyFlightsResponse> getFromToughJet(BusyFlightsRequest busyFlightsRequest) {
		ToughJetRequest toughJetRequest = new ToughJetRequest(busyFlightsRequest);
		ToughJetResponse[] toughJetFlights = searchFlights(toughJetAPI, toughJetRequest, ToughJetResponse[].class);
		return busyFlightsConverter.toBusyFlightsResponse(toughJetFlights);
	}

	private List<BusyFlightsResponse> getFromCrazyAir(BusyFlightsRequest busyFlightsRequest) {
		CrazyAirRequest crazyAirRequest = new CrazyAirRequest(busyFlightsRequest);
		CrazyAirResponse[] crazyAirFlights = searchFlights(crazyAirAPI, crazyAirRequest, CrazyAirResponse[].class);
		return busyFlightsConverter.toBusyFlightsResponse(crazyAirFlights);
	}
	
	private <T> T searchFlights(String api, Object request, Class<T> responseClass) {
        return restTemplate.postForObject(api, request, responseClass);
    }

}
