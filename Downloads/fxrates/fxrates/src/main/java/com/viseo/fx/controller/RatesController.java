package com.viseo.fx.controller;

import com.viseo.fx.model.FxRate;
import com.viseo.fx.model.User;
import com.viseo.fx.service.RatesService;
import com.viseo.fx.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rates")
public class RatesController {

    private static final Logger logger = LoggerFactory.getLogger(RatesController.class);

    @Autowired
    private RatesService rateService;

    @Autowired
    private UserService userService;

    // Handler method to produce JSON response, and when user found use default tier
    @GetMapping(path = "/latest/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FxRate> getLatestRates(@PathVariable("userId") String userId) {

        logger.info("getLatestRates start, userId: " + userId);
        User user = userService.getUserById(userId);
        String tier;
        if (null != user) {
            tier = user.getPricingTier();
        } else {
            tier = "A"; // Assuming the default tier
        }
        FxRate rates = rateService.getLatestRates(tier);
        logger.debug("Latest FX rates : " + rates);
        logger.info("getLatestRates end");
        return new ResponseEntity<FxRate>(rates, HttpStatus.OK);
    }

}
