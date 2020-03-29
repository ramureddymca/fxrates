package com.viseo.fx.model;

public class Rates {
    private Double bid;
    private Double market;
    private Double ask;

    public Double getBid() {
        return bid;
    }

    public void setBid(Double bid) {
        this.bid = bid;
    }

    public Double getMarket() {
        return market;
    }

    public void setMarket(Double market) {
        this.market = market;
    }

    public Double getAsk() {
        return ask;
    }

    public void setAsk(Double ask) {
        this.ask = ask;
    }

    @Override
    public String toString() {
        return "Currency [bid=" + bid + ", market=" + market + ", ask=" + ask + "]";
    }
}