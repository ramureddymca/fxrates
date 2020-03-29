package com.viseo.fx.controller;

import com.viseo.fx.config.WebConfig;
import com.viseo.fx.constant.Constants;
import com.viseo.fx.model.FxRate;
import com.viseo.fx.model.Rates;
import com.viseo.fx.model.User;
import com.viseo.fx.service.RatesService;
import com.viseo.fx.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class})
@WebAppConfiguration
public class RatesControllerTest {

    private static final String PATH = "/rates/latest/101";

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @Mock
    private RatesService ratesService;

    @InjectMocks
    private RatesController ratesController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(ratesController).build();
    }

    @Test
    public void testGetLatestRates() throws Exception {
        String expectedResponse = "{\"rates\":{\"CAD\":1.3117710389,\"HKD\":7.792571326,\"ISK\":122.7346133142,\"PHP\":50.6199533465,\"DKK\":6.7047371254,\"HUF\":295.1821281177,\"CZK\":22.8629104612,\"GBP\":0.7483850709,\"RON\":4.2877265387,\"SEK\":9.3498115916,\"IDR\":14004.9973084515,\"INR\":70.9728153598,\"BRL\":4.0813744841,\"RUB\":62.6518033375,\"HRK\":6.6751300915,\"JPY\":109.42939171,\"THB\":30.2251928943,\"CHF\":0.9826843711,\"EUR\":0.8971828459,\"MYR\":4.1420240445,\"BGN\":1.7547102099,\"TRY\":5.8487349722,\"CNY\":7.0002691549,\"NOK\":8.9969495783,\"NZD\":1.5125605598,\"ZAR\":14.4720078952,\"USD\":1.0,\"MXN\":19.0222501346,\"SGD\":1.3543872241,\"AUD\":1.4513726898,\"ILS\":3.4920150727,\"KRW\":1170.5275435134,\"PLN\":3.8271128656},\"base\":\"USD\",\"date\":\"2019-12-16\"}";
        Map<String, Rates> ratesMap = new HashMap<>();
        Rates rates = new Rates();
        rates.setBid(0.98);
        rates.setMarket(1.0);
        rates.setAsk(1.2);
        ratesMap.put(Constants.BASE_TYPE, rates);

        FxRate fxrate = new FxRate();
        fxrate.setBase(Constants.BASE_TYPE);
        fxrate.setRates(ratesMap);

        User user = new User("101", "A", "abc@gmail.com");

       when(userService.getUserById("101")).thenReturn(user);

       when(ratesService.getLatestRates("A")).thenReturn(fxrate);



        mockMvc.perform(get(PATH))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.base", is(Constants.BASE_TYPE)));
        //.andExpect(jsonPath("$.rates", is(ratesMap)));
        verify(ratesService, times(1)).getLatestRates("A");
        verifyNoMoreInteractions(ratesService);
    }

    private void verifyNoMoreInteractions(RatesService ratesService) {
    }

}
