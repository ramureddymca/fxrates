package com.viseo.fx.service;

import com.viseo.fx.config.WebConfig;
import com.viseo.fx.model.FxRate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class})
@WebAppConfiguration
public class RatesServiceTest {

    String expectedResponse = "{\"rates\":{\"CAD\":1.3117710389,\"HKD\":7.792571326,\"ISK\":122.7346133142,\"PHP\":50.6199533465,\"DKK\":6.7047371254,\"HUF\":295.1821281177,\"CZK\":22.8629104612,\"GBP\":0.7483850709,\"RON\":4.2877265387,\"SEK\":9.3498115916,\"IDR\":14004.9973084515,\"INR\":70.9728153598,\"BRL\":4.0813744841,\"RUB\":62.6518033375,\"HRK\":6.6751300915,\"JPY\":109.42939171,\"THB\":30.2251928943,\"CHF\":0.9826843711,\"EUR\":0.8971828459,\"MYR\":4.1420240445,\"BGN\":1.7547102099,\"TRY\":5.8487349722,\"CNY\":7.0002691549,\"NOK\":8.9969495783,\"NZD\":1.5125605598,\"ZAR\":14.4720078952,\"USD\":1.0,\"MXN\":19.0222501346,\"SGD\":1.3543872241,\"AUD\":1.4513726898,\"ILS\":3.4920150727,\"KRW\":1170.5275435134,\"PLN\":3.8271128656},\"base\":\"USD\",\"date\":\"2019-12-16\"}";
    @Mock
    private RatesPullService ratesPullService;
    @InjectMocks
    private RatesService ratesService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetLatestRates() throws Exception {
        Mockito.when(ratesPullService.getLiveRates()).thenReturn(expectedResponse);
        FxRate fxRate = ratesService.getLatestRates("A");


        assertTrue("Output message should be match", fxRate.getBase().equals("USD"));
    }
}
