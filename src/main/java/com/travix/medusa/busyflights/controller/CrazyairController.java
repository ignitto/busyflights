package com.travix.medusa.busyflights.controller;

import com.travix.medusa.busyflights.domain.crazyair.CrazyAirRequest;
import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(value = "crazyair")
public class CrazyairController {

    @ApiOperation(value = "Searching Crazyair flights")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ResponseEntity<List<CrazyAirResponse>> searchCrazyAir(@RequestBody CrazyAirRequest request) throws InterruptedException {
        List<CrazyAirResponse> list = new ArrayList<>();
        list.add(new CrazyAirResponse("crazyair", 34.45, "E", "LHR", "TMS", "01-29-2017 13:45:00", "02-15-2016 08:45:00"));
        list.add(new CrazyAirResponse("crazyair", 25.98, "E", "LHR", "TMS", "01-29-2017 15:14:00", "02-15-2016 09:35:00"));
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
