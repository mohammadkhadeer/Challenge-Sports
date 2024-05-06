package com.challenge.sports.model.data.Matches;//
//  Coverage.java
//
//  Generated using https://jsonmaster.github.io
//  Created on May 02, 2024
//

import java.util.*;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Coverage {

    @SerializedName("mlive")
    @Expose
    private int mlive;
    @SerializedName("lineup")
    @Expose
    private int lineup;

    public void setMlive(int mlive) {
        this.mlive = mlive;
    }

    public int getMlive() {
        return this.mlive;
    }

    public void setLineup(int lineup) {
        this.lineup = lineup;
    }

    public int getLineup() {
        return this.lineup;
    }


}