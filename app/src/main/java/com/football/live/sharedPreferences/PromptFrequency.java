package com.football.live.sharedPreferences;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class PromptFrequency {

    private static final String PROMPT_FREQUENCY = "PROMPT_FREQUENCY";
    static SharedPreferences SharedPreferences;
    static SharedPreferences.Editor Editor;

    public static String getPromptFrequencyFromSP(Context context) {
        String text;
        SharedPreferences shared = context.getSharedPreferences(PROMPT_FREQUENCY, MODE_PRIVATE);

        text = (shared.getString("prompt_frequency", ""));
        if (text == null || text.isEmpty())
        {
            text = "0";
        }
        return text;
    }


    public static void savePromptFrequencyInSP(Context context, String text) {
        SharedPreferences = context.getSharedPreferences(PROMPT_FREQUENCY, MODE_PRIVATE);
        Editor = SharedPreferences.edit();

        Editor.putString("prompt_frequency",text);

        Editor.commit();
    }


}
