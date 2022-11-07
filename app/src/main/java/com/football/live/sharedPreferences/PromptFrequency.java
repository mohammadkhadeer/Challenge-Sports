package com.football.live.sharedPreferences;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

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
            text = "empty";
        }
        return text;
    }

    public static String getTitleFromSP(Context context) {
        String text;
        SharedPreferences shared = context.getSharedPreferences(PROMPT_FREQUENCY, MODE_PRIVATE);

        text = (shared.getString("title", ""));
        if (text == null || text.isEmpty())
        {
            text = "empty";
        }
        return text;
    }

    public static String getMassageFromSP(Context context) {
        String text;
        SharedPreferences shared = context.getSharedPreferences(PROMPT_FREQUENCY, MODE_PRIVATE);

        text = (shared.getString("message", ""));
        if (text == null || text.isEmpty())
        {
            text = "empty";
        }
        return text;
    }

    public static void savePromptFrequencyInSP(Context context, String text) {
        SharedPreferences = context.getSharedPreferences(PROMPT_FREQUENCY, MODE_PRIVATE);
        Editor = SharedPreferences.edit();

        Editor.putString("prompt_frequency",text);

        Editor.commit();
    }

    public static void saveMassageAndTitleInSP(Context context, String message,String title) {
        SharedPreferences = context.getSharedPreferences(PROMPT_FREQUENCY, MODE_PRIVATE);
        Editor = SharedPreferences.edit();

        Editor.putString("message",message);
        Editor.putString("title",title);

        Editor.commit();
    }



    public static void cleanPromptFrequency(Context context) {
        SharedPreferences = context.getSharedPreferences(PROMPT_FREQUENCY, MODE_PRIVATE);
        Editor = SharedPreferences.edit();
        Editor.putString("prompt_frequency","");
        Editor.putString("prompt_title","");
        Editor.putString("prompt_message","");
        Editor.putString("contaxt_str","");
        Editor.putString("url","");

        Editor.commit();
    }

}
