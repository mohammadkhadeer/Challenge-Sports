package com.challenge.sports.model.data.Matches;//
//  VenueDetails.java
//
//  Generated using https://jsonmaster.github.io
//  Created on May 02, 2024
//

import java.util.*;
import com.google.gson.annotations.SerializedName;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class VenueDetails {

    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("capacity")
    private int capacity;
    @SerializedName("country_id")
    private String countryId;
    @SerializedName("city")
    private String city;
    @SerializedName("country")
    private String country;
    @SerializedName("updated_at")
    private int updatedAt;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getCountryId() {
        return this.countryId;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return this.city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {
        return this.country;
    }

    public void setUpdatedAt(int updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getUpdatedAt() {
        return this.updatedAt;
    }


    public static VenueDetails create(String json) {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(json, VenueDetails.class);
    }

    public String toString() {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(this);
    }

}