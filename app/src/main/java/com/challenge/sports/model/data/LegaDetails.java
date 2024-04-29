package com.challenge.sports.model.data;

public class LegaDetails {
    String lega_name,image;

    public LegaDetails(String lega_name, String image) {
        this.lega_name = lega_name;
        this.image = image;
    }

    public String getLega_name() {
        return lega_name;
    }

    public void setLega_name(String lega_name) {
        this.lega_name = lega_name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
