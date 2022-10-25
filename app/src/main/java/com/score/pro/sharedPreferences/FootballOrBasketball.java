package com.score.pro.sharedPreferences;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

public class FootballOrBasketball {

    private static final String FOOTBALL_BASKETBALL = "FOOTBALL_BASKETBALL";
    static SharedPreferences SharedPreferences;
    static SharedPreferences.Editor Editor;



    public static void saveFootballOrBasketballInSP(Context context, String text) {
        SharedPreferences = context.getSharedPreferences(FOOTBALL_BASKETBALL, MODE_PRIVATE);
        Editor = SharedPreferences.edit();

        Editor.putString("football_basketball",text);

        Editor.commit();
    }



    public static String getFootballOrBasketballFromSP(Context context) {
        String text;
        SharedPreferences shared = context.getSharedPreferences(FOOTBALL_BASKETBALL, MODE_PRIVATE);

        text = (shared.getString("football_basketball", ""));
        if (text == null || text.isEmpty())
        {
            text = "empty";
        }
        return text;
    }




    public static void cleanFootballOrBasketball(Context context) {
        SharedPreferences = context.getSharedPreferences(FOOTBALL_BASKETBALL, MODE_PRIVATE);
        Editor = SharedPreferences.edit();
        Editor.putString("football_basketball","");

        Editor.commit();
    }

}
