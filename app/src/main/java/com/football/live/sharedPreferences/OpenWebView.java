package com.football.live.sharedPreferences;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

public class OpenWebView {

    private static final String OPEN_WEB_VIEW = "OPEN_WEB_VIEW";
    static SharedPreferences SharedPreferences;
    static SharedPreferences.Editor Editor;

    public static String getOpenWebViewBeforeFromSP(Context context) {
        String text;
        SharedPreferences shared = context.getSharedPreferences(OPEN_WEB_VIEW, MODE_PRIVATE);

        text = (shared.getString("open_web_view_or_no", ""));
        if (text == null || text.isEmpty())
        {
            text = "no";
        }
        return text;
    }


    public static void saveOpenWebViewBeforeInSP(Context context, String text) {
        SharedPreferences = context.getSharedPreferences(OPEN_WEB_VIEW, MODE_PRIVATE);
        Editor = SharedPreferences.edit();

        Editor.putString("open_web_view_or_no",text);

        Editor.commit();
    }


}
