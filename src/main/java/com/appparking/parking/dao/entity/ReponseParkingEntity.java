package com.appparking.parking.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ReponseParkingEntity {

    @JsonProperty(value = "records")
    private List<RecordEnity> records;

    public List<RecordEnity> getRecords() {
        return records;
    }

    public void setRecords(List<RecordEnity> records) {
        this.records = records;
    }
}
