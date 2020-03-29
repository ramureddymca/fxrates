package com.viseo.fx.service;

import com.viseo.fx.constant.Constants;
import com.viseo.fx.model.FxRate;
import com.viseo.fx.model.Rates;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

@Service
public class RatesService {

    private static final Logger logger = LoggerFactory.getLogger(RatesService.class);
    @Autowired
    private RatesPullService ratesPullService;

    private static Map<String, Double> spread;
    {
        spread = new HashMap<>();
        spread.put("A", 0.02);
        spread.put("B", 0.03);
        spread.put("C", 0.04);
    }

    /**
     * method will get the fx rates from pullService and apply spread on that and return final data.
     * @param tier
     * @return
     */
    public FxRate getLatestRates(String tier) {

        logger.info("getLatestRates start, tier: " + tier);
        String fxRates = ratesPullService.getLiveRates();
        logger.debug("Loaded the live rates, Rates Json: " + fxRates);
        String rateDetails = fxRates.substring(fxRates.indexOf("{") + 10, fxRates.indexOf("}"));

        List<String> rates = Arrays.asList(rateDetails.split(","));
        Map<String, String> ratesMap = rates.stream().map(s -> s.split(":"))
                .collect(Collectors.toMap(val -> val[0], val -> val[1]));

        Map<String, Rates> spreadRates = applySpread(tier, ratesMap);

        FxRate fxRate = new FxRate();
        fxRate.setBase(Constants.BASE_TYPE);
        fxRate.setRates(spreadRates);
        logger.debug("getLatestRates start, fxrate: " + fxRate);
        return fxRate;

    }

    private Map<String, Rates> applySpread(String tier, Map<String, String> ratesMap) {
        Iterator<Entry<String, String>> rateSet = ratesMap.entrySet().iterator();

        Map<String, Rates> map = new HashMap<>();
        while (rateSet.hasNext()) {
            Entry<String, String> entry = rateSet.next();
            String type = entry.getKey();
            String value = entry.getValue();
            Rates rate = new Rates();
            Double doubleValue = Double.valueOf(value);
            Double spreadVal = spread.get(tier);
            rate.setBid(doubleValue - doubleValue * spreadVal);
            rate.setMarket(doubleValue);
            rate.setAsk(doubleValue + doubleValue * spreadVal);

            map.put(type, rate);
        }
        return map;
    }


}
