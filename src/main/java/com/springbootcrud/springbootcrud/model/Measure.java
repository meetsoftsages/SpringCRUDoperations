package com.springbootcrud.springbootcrud.model;

public class Measure {
    private String metric;
    private String value;
    private Boolean bestValue;

    public String getMetric() {
        return metric;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Boolean getBestValue() {
        return bestValue;
    }

    public void setBestValue(Boolean bestValue) {
        this.bestValue = bestValue;
    }
}

