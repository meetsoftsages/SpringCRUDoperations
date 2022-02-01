package com.springbootcrud.springbootcrud.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Component {
    private String key;
    private String name;
    private String description;
    private String qualifier;
    private ArrayList<Measure> measures = new ArrayList<Measure>();

    public Component(String key, String name, String description, String qualifier, ArrayList<Measure> measures) {
        this.key = key;
        this.name = name;
        this.description = description;
        this.qualifier = qualifier;
        this.measures = measures;
    }

    public Component(){}

    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getQualifier() {
        return qualifier;
    }
    public void setQualifier(String qualifier) {
        this.qualifier = qualifier;
    }
    public ArrayList<Measure> getMeasures() {
        return measures;
    }
    public void setMeasures(ArrayList<Measure> measures) {
        this.measures = measures;
    }
}
