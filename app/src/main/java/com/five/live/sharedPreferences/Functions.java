package com.five.live.sharedPreferences;

import static com.five.live.sharedPreferences.PromptFrequency.getPromptFrequencyFromSP;
import static com.five.live.sharedPreferences.PromptFrequency.savResponseInSP;
import static com.five.live.sharedPreferences.PromptFrequency.savePromptFrequencyInSP;

import android.content.Context;
import android.util.Log;


public class Functions {

    public static void checkAndSavePromptFrequency(Context context,String prompt_frequency, String prompt_title, String prompt_message, String map) {
        String prompt_frequency_sp =getPromptFrequencyFromSP(context);
        if (prompt_frequency_sp.equals("empty") && !prompt_frequency_sp.equals("done"))
        {
            savResponseInSP(context,prompt_frequency,prompt_title,prompt_message,map);
        }else{
            if (!prompt_frequency_sp.equals("empty") && !prompt_frequency_sp.equals("done"))
            {
                int x= Integer.parseInt(prompt_frequency_sp);
                x=x-1;
                if (x==0)
                    savePromptFrequencyInSP(context,"done");
                else
                    savePromptFrequencyInSP(context,String.valueOf(x));
            }

            Log.i("TAG","prompt_frequency_sp: "+prompt_frequency_sp);
        }
    }

}
