package com.travix.medusa.busyflights.converter;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BusyFlightsConverter {

    public List<BusyFlightsResponse> toBusyFlightsResponse(CrazyAirResponse[] crazyAirResponses) {
      return Arrays.stream(crazyAirResponses).map(this::getBusyFlightResponse).collect(Collectors.toList());
    }

    public List<BusyFlightsResponse> toBusyFlightsResponse(ToughJetResponse[] toughJetResponses) {
        return Arrays.stream(toughJetResponses).map(this::getBusyFlightResponse).collect(Collectors.toList());
    }

    public BusyFlightsResponse getBusyFlightResponse(CrazyAirResponse res){
        return new BusyFlightsResponse(res.getAirline(), Suppliers.CRAZY_AIR.getSupplier(), res.getPrice(), res.getDepartureAirportCode(),
                res.getDestinationAirportCode(), res.getDepartureDate(), res.getArrivalDate());
    }

    public BusyFlightsResponse getBusyFlightResponse(ToughJetResponse res){
        double price = roundPrice((res.getBasePrice() - res.getDiscount()) * res.getTax());
        return new BusyFlightsResponse(res.getCarrier(), Suppliers.TOUGH_JET.getSupplier(), price, res.getDepartureAirportName(),
                res.getArrivalAirportName(), res.getOutboundDateTime(), res.getInboundDateTime());
    }
    private double roundPrice(double price){
        BigDecimal roundedPrice = new BigDecimal(price);
        roundedPrice = roundedPrice.setScale(2, RoundingMode.HALF_UP);
        return roundedPrice.doubleValue();
    }
}
