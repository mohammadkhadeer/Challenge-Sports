package com.challenge.sports.model.api;

import com.challenge.sports.model.Ads;
import com.challenge.sports.model.Map;

import java.util.ArrayList;

public class ListResponse {
    public static ArrayList<Ads> adsArrayList = new ArrayList<>();
    public static ArrayList<Map> mapArrayList = new ArrayList<>();
    public static String prompt_frequency,prompt_title,prompt_message,open_type,redirect_url
            ,repeat_status,repeat_time,button_text,init_open_type,init_redirect_url;
}
