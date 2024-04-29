package com.challenge.sports.model.api;

import android.content.Context;
import android.os.StrictMode;
import android.util.Log;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ApiReq {

    public static void sentReq(Context context)
    {
        if (checkIfAndroidVBiggerThan9()) {
            JSONObject jsonObject = new JSONObject();
            try {
                //"com.test.app"
                //jsonObject.put("package_name", BuildConfig.APPLICATION_ID);
                //jsonObject.put("package_name", BuildConfig.APPLICATION_ID);
                jsonObject.put("platform","android");
                jsonObject.put("device_name","Devie name");
                jsonObject.put("version","1.0.0");
                jsonObject.put("build_number","1");

            } catch (JSONException e) {
                e.printStackTrace();
            }

            OkHttpClient client = new OkHttpClient();
            MediaType JSON = MediaType.parse("application/json; charset=utf-8");
            // put your json here
            RequestBody body = RequestBody.create(JSON, jsonObject.toString());


            Request request = new Request.Builder()
                    .url("https://ios.8xapp.app/api/v1/setting.php")
                    .method("POST", body)
                    .build();

            try {
                Response response = client.newCall(request).execute();

                try {
                    //Log.w("TAG","Response: "+ response);
                    JSONObject responseObj = new JSONObject(response.body().string());
                    //Log.w("TAG","responseObj: "+ responseObj);
                    String prompt_frequency = responseObj.getString("prompt_frequency");
                    String prompt_title = responseObj.getString("prompt_title");
                    String prompt_message = responseObj.getString("prompt_message");
                    String map = responseObj.getString("map");

                    Log.w("TAG","prompt_frequency: "+ prompt_frequency);
                    Log.w("TAG","prompt_title: "+ prompt_title);
                    Log.w("TAG","prompt_message: "+ prompt_message);
                    Log.w("TAG","map: "+ map);

                    //checkAndSavePromptFrequency(context,prompt_frequency,prompt_title,prompt_message,map);

//                    ApiResponse apiResponse = new ApiResponse(
//                            map,prompt_frequency,prompt_title,prompt_message
//                    );

                    //responseSuccessful.resSuccessful(apiResponse);

                } catch (JSONException e) {
                    e.printStackTrace();
//                    ApiResponse apiResponse = new ApiResponse(
//                    );
//                    responseSuccessful.resSuccessful(apiResponse);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    public static boolean checkIfAndroidVBiggerThan9() {
        // we use this method cos OkHttpClient not allwed less than 9
        boolean value = false;
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            value = true;
        }else{
            value = false;
        }
        return value;
    }
}
