package com.viseo.fx.model;

import java.util.Map;

public class FxRate {
    Rates RatesObject;
    private String base;

    private Map<String, Rates> rates;

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Map<String, Rates> getRates() {
        return rates;
    }

    public void setRates(Map<String, Rates> rates) {
        this.rates = rates;
    }


}
	