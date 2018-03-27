package com.example.a67371.tabtest.location;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 异步任务，获取地理位置
 */

public class LocationAsyncTask extends AsyncTask<Void,Void,Void> {
    private static final String TAG = "LocationAsyncTask";
    private OnTaskFin call;
    private String location = null;//地理位置，"30.783729,103.858923"
    private String json = null;//


    public LocationAsyncTask(String location,OnTaskFin call){
        this.location = location;
        this.call = call;
    }

    public interface OnTaskFin{
        void onTaskFin(String city, String town);
    }




    @Override
    protected Void doInBackground(Void... params) {
        json = HttpHelper.getLocation(location);
        return null;
    }
    @Override
    protected void onPostExecute(Void s) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject result = jsonObject.getJSONObject("result");
            JSONObject addressComponent = result.getJSONObject("addressComponent");
            String city = addressComponent.getString("city");
            String district = addressComponent.getString("district");
            Log.d(TAG,city+"---"+district);
            call.onTaskFin(city,district);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        super.onPostExecute(s);
    }
}
