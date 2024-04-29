package com.challenge.sports.model.api;


import static com.challenge.sports.model.api.ApiReq.checkIfAndroidVBiggerThan9;
import static com.challenge.sports.model.api.ListResponse.adsArrayList;
import static com.challenge.sports.model.api.ListResponse.button_text;
import static com.challenge.sports.model.api.ListResponse.init_open_type;
import static com.challenge.sports.model.api.ListResponse.init_redirect_url;
import static com.challenge.sports.model.api.ListResponse.mapArrayList;
import static com.challenge.sports.model.api.ListResponse.prompt_frequency;
import static com.challenge.sports.model.api.ListResponse.prompt_message;
import static com.challenge.sports.model.api.ListResponse.prompt_title;
import static com.challenge.sports.model.api.ListResponse.open_type;
import static com.challenge.sports.model.api.ListResponse.redirect_url;
import static com.challenge.sports.model.api.ListResponse.repeat_status;
import static com.challenge.sports.model.api.ListResponse.repeat_time;

import android.content.Context;

import com.challenge.sports.model.Ads;
import com.challenge.sports.model.Map;

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

                jsonObject.put("package_name", "com.seven.seven.five.score.live");
                //jsonObject.put("package_name", "test");
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

//.url("http://192.168.1.237/api/v1/settings.php")

//.url("https://app.8com.cloud/api/v1/setting.php")

            Request request = new Request.Builder()
                    .url("https://app.8com.cloud/api/v1/setting.php")
                    .method("POST", body)
                    .build();

            try {
                Response response = client.newCall(request).execute();

                try {
                    //Log.w("TAG","Response: "+ response);
                    JSONObject responseObj = new JSONObject(response.body().string());
                    //Log.w("TAG","responseObj: "+ responseObj);

                    adsArrayList = new ArrayList<>();
                    mapArrayList = new ArrayList<>();

                    try {
                        if (responseObj.has("banner"))
                        {
                            JSONArray bannerArray = responseObj.getJSONArray("banner");
                            //ads list
                            for (int i=0;i<bannerArray.length();i++)
                            {
                                JSONObject bannerObj = (JSONObject) bannerArray.get(i);
                                String image_path = bannerObj.getString("image");
                                String redirect_url = bannerObj.getString("redirect_url");
                                String open_type = bannerObj.getString("open_type");
//                                Log.w("TAG","image_path: "+ image_path);
//                                Log.w("TAG","redirect_url: "+ redirect_url);
//                                Log.w("TAG","open_type: "+ open_type);

                                adsArrayList.add(new Ads(
                                        image_path,redirect_url,open_type
                                ));
//                        Log.w("TAG","image_path: "+ image_path);
//                        Log.w("TAG","redirect_url: "+ redirect_url);
//                        Log.w("TAG","open_type: "+ open_type);
                            }
                        }
                    }catch (Exception e){
                        //Log.i("TAG","Exception: "+e.toString());
                    }



                    try {
                        if (responseObj.has("mapping"))
                        {
                            JSONArray mapArray = responseObj.getJSONArray("mapping");
                            for (int i=0;i<mapArray.length();i++)
                            {
                                JSONObject mapJO = (JSONObject) mapArray.get(i);
                                String keyword_v = mapJO.getString("keyword");
                                String redirect_url = mapJO.getString("redirect_url");
                                String open_type = mapJO.getString("open_type");

                                Map map1 = new Map(keyword_v,redirect_url,open_type);
                                mapArrayList.add(map1);
//                                Log.i("TAG","keyword_v: "+keyword_v);
//                                Log.i("TAG","open_type: "+open_type);
//                                Log.i("TAG","redirect_url: "+redirect_url);

                            }
                        }
                    }catch (Exception e){
                        //Log.i("TAG","Exception: "+e.toString());
                    }

                    try {
                        if (responseObj.has("prompt"))
                        {
                            JSONObject prompt =responseObj.getJSONObject("prompt");

                            prompt_frequency = prompt.getString("frequency");
                            prompt_title = prompt.getString("title");
                            prompt_message = prompt.getString("message");
                            repeat_status= prompt.getString("repeat_status");
                            repeat_time= prompt.getString("repeat_time");
                            open_type = prompt.getString("open_type");
                            redirect_url = prompt.getString("redirect_url");
                            button_text = prompt.getString("button");
//                    Log.i("TAG","prompt_frequency: "+prompt_frequency);
//                    Log.i("TAG","prompt_title: "+prompt_title);
//                    Log.i("TAG","prompt_message: "+prompt_message);
//                    Log.i("TAG","open_type: "+open_type);
//                    Log.i("TAG","redirect_url: "+redirect_url);
//                    Log.i("TAG","repeat_status: "+repeat_status);
//                    Log.i("TAG","repeat_time: "+repeat_time);
//                    Log.i("TAG","button_text: "+button_text);

                        }else{
                            //Log.i("TAG","no prompt: ");
                        }
                    }catch (Exception e){
                        //Log.i("TAG","Exception: "+e.toString());
                    }


                    try {
                        if (responseObj.has("init"))
                        {
                            JSONObject initJObjet = responseObj.getJSONObject("init");
                            init_open_type=initJObjet.getString("open_type");
                            init_redirect_url=initJObjet.getString("redirect_url");

//                        Log.i("TAG","init_open_type: "+init_open_type);
//                        Log.i("TAG","init_redirect_url: "+init_redirect_url);
                        }
                    }catch (Exception e){
                        //Log.i("TAG","Exception: "+e.toString());
                    }





                    //adsResponseSuccessful.adsSuccessful(true);

                } catch (JSONException e) {
                    e.printStackTrace();

                    //adsResponseSuccessful.adsSuccessful(true);
                }

            } catch (IOException e) {
                //Log.w("TAG","e: "+ e.toString());

                e.printStackTrace();
            }
        }

    }

}
