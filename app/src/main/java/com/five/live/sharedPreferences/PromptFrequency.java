package com.five.live.sharedPreferences;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

public class PromptFrequency {

    private static final String PROMPT_FREQUENCY = "PROMPT_FREQUENCY";
    static SharedPreferences SharedPreferences;
    static SharedPreferences.Editor Editor;

    public static void savResponseInSP(Context context, String prompt_frequency, String prompt_title, String prompt_message, String map) {
        savePromptFrequencyInSP(context,prompt_frequency);
        savePrompt_titleInSP(context,prompt_title);
        savePrompt_messageInSP(context,prompt_message);
        saveMapInSP(context,map);
    }

    public static void savePromptFrequencyInSP(Context context, String text) {
        SharedPreferences = context.getSharedPreferences(PROMPT_FREQUENCY, MODE_PRIVATE);
        Editor = SharedPreferences.edit();

        Editor.putString("prompt_frequency",text);

        Editor.commit();
    }

    public static void savePrompt_titleInSP(Context context, String text) {
        SharedPreferences = context.getSharedPreferences(PROMPT_FREQUENCY, MODE_PRIVATE);
        Editor = SharedPreferences.edit();

        Editor.putString("prompt_title",text);

        Editor.commit();
    }

    public static void savePrompt_messageInSP(Context context, String text) {
        SharedPreferences = context.getSharedPreferences(PROMPT_FREQUENCY, MODE_PRIVATE);
        Editor = SharedPreferences.edit();

        Editor.putString("prompt_message",text);

        Editor.commit();
    }

    public static void saveMapInSP(Context context, String text) {
        String contaxt_str,url;
        String[] separated = text.split(";");
        String beforeS=separated[0];

        String currentString = separated[0];
        String[] separated2 = currentString.split("=>");
        contaxt_str =separated2[0];
        url=separated2[1];

        SharedPreferences = context.getSharedPreferences(PROMPT_FREQUENCY, MODE_PRIVATE);
        Editor = SharedPreferences.edit();

        Editor.putString("contaxt_str",contaxt_str);
        Editor.putString("url",url);

        Editor.commit();
    }


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

    public static String getPrompt_titleFromSP(Context context) {
        String text;
        SharedPreferences shared = context.getSharedPreferences(PROMPT_FREQUENCY, MODE_PRIVATE);

        text = (shared.getString("prompt_title", ""));
        if (text == null || text.isEmpty())
        {
            text = "empty";
        }
        return text;
    }

    public static String getPrompt_messageFromSP(Context context) {
        String text;
        SharedPreferences shared = context.getSharedPreferences(PROMPT_FREQUENCY, MODE_PRIVATE);

        text = (shared.getString("prompt_message", ""));
        if (text == null || text.isEmpty())
        {
            text = "empty";
        }
        return text;
    }

    public static String getContaxt_strFromSP(Context context) {
        String text;
        SharedPreferences shared = context.getSharedPreferences(PROMPT_FREQUENCY, MODE_PRIVATE);

        text = (shared.getString("contaxt_str", ""));
        if (text == null || text.isEmpty())
        {
            text = "empty";
        }
        return text;
    }

    public static String getUrlFromSP(Context context) {
        String text;
        SharedPreferences shared = context.getSharedPreferences(PROMPT_FREQUENCY, MODE_PRIVATE);

        text = (shared.getString("url", ""));
        if (text == null || text.isEmpty())
        {
            text = "empty";
        }
        return text;
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
