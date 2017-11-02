package com.travix.medusa.busyflights.controller;

import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
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
@RequestMapping(value = "toughjet")
public class ToughjetController {

    @ApiOperation(value = "Searching ToughJet flights")
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ResponseEntity<List<ToughJetResponse>> searchToughJet(@RequestBody ToughJetRequest request) throws Exception {
        List<ToughJetResponse> list = new ArrayList<>();
        list.add(new ToughJetResponse("toughjet", 19.99, 20, 10, "LHR", "TMS", "01-29-2017 15:14:00", "02-15-2016 09:35:00"));
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
