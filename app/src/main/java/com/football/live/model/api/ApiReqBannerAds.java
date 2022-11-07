package com.football.live.model.api;


import static com.football.live.model.api.ApiReq.checkIfAndroidVBiggerThan9;
import static com.football.live.model.api.ListResponse.adsArrayList;
import static com.football.live.model.api.ListResponse.mapArrayList;
import static com.football.live.sharedPreferences.Functions.checkAndSavePromptFrequency;
import static com.football.live.sharedPreferences.PromptFrequency.saveMassageAndTitleInSP;

import android.content.Context;
import android.util.Log;

import com.football.live.model.Ads;
import com.football.live.model.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ApiReqBannerAds {

    public static void sentReqBanner(Context context)
    {
        if (checkIfAndroidVBiggerThan9()) {

            JSONObject jsonObject = new JSONObject();
            try {
                //BuildConfig.APPLICATION_ID com.test.app
                //test
                //com.football.basketball.score.live.seven.ex
                jsonObject.put("package_name", "com.football.basketball.score.live.seven.ex");
                jsonObject.put("platform","android");
                jsonObject.put("device_name","Devie name");
                jsonObject.put("version","android");
                jsonObject.put("build_number",1);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            OkHttpClient client = new OkHttpClient();
            MediaType JSON = MediaType.parse("application/json; charset=utf-8");
            // put your json here
            RequestBody body = RequestBody.create(JSON, jsonObject.toString());


            Request request = new Request.Builder()
                    .url("https://app.8com.cloud/api/v1/setting.php")
                    .method("POST", body)
                    .build();

            try {
                Response response = client.newCall(request).execute();

                try {
                    //Log.w("TAG","Response: "+ response);
                    JSONObject responseObj = new JSONObject(response.body().string());
                    Log.w("TAG","responseObj: "+ responseObj);
                    JSONArray bannerArray = responseObj.getJSONArray("banner");

                    adsArrayList = new ArrayList<>();
                    mapArrayList = new ArrayList<>();

                    String map = responseObj.getString("map");

                    String[] separated = map.split(";");
                    //String beforeS=separated[0];

                    JSONObject prompt =responseObj.getJSONObject("prompt");

                    String prompt_frequency = prompt.getString("frequency");
                    String prompt_title = prompt.getString("title");
                    String prompt_message = prompt.getString("message");

//                    if (prompt_frequency.isEmpty())
//                    {
//                        Log.i("TAG","prompt_frequency: is empty");
//                    }

                    Log.i("TAG","prompt_frequency: "+prompt_frequency);
                    Log.i("TAG","prompt_title: "+prompt_title);
                    Log.i("TAG","prompt_message: "+prompt_message);


                    checkAndSavePromptFrequency(context,prompt_frequency);
                    saveMassageAndTitleInSP(context,prompt_message,prompt_title);

                    for (int i=0;i<separated.length;i++)
                    {
                        String beforeS=separated[i];
                        String[] separated2 = beforeS.split("=>");
                        Map map1 = new Map(separated2[0],separated2[1]);
                        mapArrayList.add(map1);
                        Log.i("TAG","Map_key: "+map1.getMap_key());
                        Log.i("TAG","Map_link: "+map1.getMap_link());
                    }



                    for (int i=0;i<bannerArray.length();i++)
                    {
                        JSONObject bannerObj = (JSONObject) bannerArray.get(i);
                        String image_path = bannerObj.getString("image");
                        String redirect_url = bannerObj.getString("redirect_url");
                        String open_type = bannerObj.getString("open_type");
                        adsArrayList.add(new Ads(
                                image_path,redirect_url,open_type
                        ));
                        Log.w("TAG","image_path: "+ image_path);
                        Log.w("TAG","redirect_url: "+ redirect_url);
                        Log.w("TAG","open_type: "+ open_type);
                    }
                    //adsResponseSuccessful.adsSuccessful(true);

                } catch (JSONException e) {
                    e.printStackTrace();

                    //adsResponseSuccessful.adsSuccessful(true);
                }

            } catch (IOException e) {
                Log.w("TAG","e: "+ e.toString());

                e.printStackTrace();
            }
        }

    }
}
