package com.score.pro.sharedPreferences;

import static com.score.pro.sharedPreferences.PromptFrequency.getPromptFrequencyFromSP;
import static com.score.pro.sharedPreferences.PromptFrequency.savePromptFrequencyInSP;

import android.content.Context;
import android.util.Log;


public class Functions {

    public static void checkAndSavePromptFrequency(Context context,String prompt_frequency) {
        String prompt_frequency_sp =getPromptFrequencyFromSP(context);
        if (prompt_frequency_sp.equals("empty") && !prompt_frequency_sp.equals("done"))
        {
            savePromptFrequencyInSP(context,prompt_frequency);
        }else{
            if (!prompt_frequency_sp.equals("empty") && !prompt_frequency_sp.equals("done"))
            {
                int x= Integer.parseInt(prompt_frequency_sp);
                x=x-1;
                if (x==0 || x==-1)
                    savePromptFrequencyInSP(context,"done");
                else
                    savePromptFrequencyInSP(context,String.valueOf(x));
            }

            Log.i("TAG","prompt_frequency_sp: "+prompt_frequency_sp);
        }
    }

}
