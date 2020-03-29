package com.viseo.fx.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@PropertySource("classpath:config.properties")
public class RatesPullService {

    private static final Logger logger = LoggerFactory.getLogger(RatesPullService.class);

    @Value("${service.uri}")
    private String serviceUri;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * method call external service and get the fzx rates and return
     * @return
     */
    public String getLiveRates() {
        logger.debug("Invoking third party service URL " + serviceUri);
        return restTemplate.getForObject(serviceUri, String.class);
    }
}
