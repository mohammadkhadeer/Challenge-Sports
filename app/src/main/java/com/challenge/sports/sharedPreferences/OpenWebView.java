package com.challenge.sports.sharedPreferences;

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

    public static String getLastWebLinkOpenedFromSP(Context context) {
        String text;
        SharedPreferences shared = context.getSharedPreferences(OPEN_WEB_VIEW, MODE_PRIVATE);

        text = (shared.getString("last_link", ""));
        if (text == null || text.isEmpty())
        {
            text = "no";
        }
        return text;
    }

    //here
    public static void saveWebViewOnOrOffInSP(Context context, String text) {
        SharedPreferences = context.getSharedPreferences(OPEN_WEB_VIEW, MODE_PRIVATE);
        Editor = SharedPreferences.edit();

        Editor.putString("open_web_view_or_no",text);

        Editor.commit();
    }

    public static void saveLastWebLinkOpenedOnSP(Context context, String text,String open_type) {
        SharedPreferences = context.getSharedPreferences(OPEN_WEB_VIEW, MODE_PRIVATE);
        Editor = SharedPreferences.edit();

        Editor.putString("last_link",text);
        Editor.putString("open_type",open_type);

        Editor.commit();
    }

    public static String getLastURLOpenTypeSP(Context context) {
        String text;
        SharedPreferences shared = context.getSharedPreferences(OPEN_WEB_VIEW, MODE_PRIVATE);

        text = (shared.getString("open_type", ""));
        if (text == null || text.isEmpty())
        {
            text = "0";
        }
        return text;
    }

    public static String getWebButtonActiveOrNoSP(Context context) {
        String text;
        SharedPreferences shared = context.getSharedPreferences(OPEN_WEB_VIEW, MODE_PRIVATE);

        text = (shared.getString("web_button", ""));
        if (text == null || text.isEmpty())
        {
            text = "not_active";
        }
        return text;
    }

    public static void activeWebButtonInSP(Context context, String text) {
        SharedPreferences = context.getSharedPreferences(OPEN_WEB_VIEW, MODE_PRIVATE);
        Editor = SharedPreferences.edit();

        Editor.putString("web_button",text);

        Editor.commit();
    }

}
