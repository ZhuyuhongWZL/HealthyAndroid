package com.example.a67371.tabtest.location;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import java.util.List;

import static android.content.Context.LOCATION_SERVICE;

/**
 *
 */
public class LocationHelper {
    private static final String TAG = "LocationHelper";
    private static final String MESSAGE = "暂时无法获得当前位置,请检查应用是否具有网络及定位权限";
    private static final String OK_MESSAGE = "成功获取地理位置";
    private Context context;
    private LocationManager locationManager;//位置服务
    private String provider;//位置提供器
    private Location location;
    private CallBack callBack;

    public LocationHelper(Context context,CallBack callBack) {
        this.context = context;
        this.callBack = callBack;
        locationManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);//获得位置服务
        provider = judgeProvider(locationManager);
    }



    public interface CallBack{
        /**
         *
         * @param isSuccess 是否成功获得地理位置
         * @param message 提示消息
         * @param city
         * @param town
         */
        void callback(boolean isSuccess, String message, String city, String town);
    }


    public void getLocation(){
        if (provider != null) {//有位置提供器的情况
            //为了压制getLastKnownLocation方法的警告
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            Log.d(TAG,"provider--"+provider);
            location = locationManager.getLastKnownLocation(provider);

            if (location != null) {
                run(location);//得到当前经纬度并开启线程去反向地理编码
            } else {
                callBack.callback(false,MESSAGE+":location is null","","");
                //Log.d(TAG,MESSAGE);
                //tvLocation.setText(MESSAGE);
            }
        }else{//不存在位置提供器的情况
            callBack.callback(false,MESSAGE+":不存在位置提供器","","");
            //tvLocation.setText(MESSAGE);
        }
    }

    /**
     * 判断是否有可用的内容提供器
     * @return 不存在返回null
     */
    private String judgeProvider(LocationManager locationManager) {
        List<String> prodiverlist = locationManager.getProviders(true);
        if(prodiverlist.contains(LocationManager.NETWORK_PROVIDER)){
            return LocationManager.NETWORK_PROVIDER;
        }else if(prodiverlist.contains(LocationManager.GPS_PROVIDER)) {
            return LocationManager.GPS_PROVIDER;
        }else{
            // Toast.makeText(MainActivity.this,"没有可用的位置提供器",Toast.LENGTH_SHORT).show();
        }
        return null;
    }
    /**
     * 得到当前经纬度并开启线程去反向地理编码
     */
    private void run(Location location) {
        String latitude = location.getLatitude()+"";
        String longitude = location.getLongitude()+"";
        Log.d(TAG,latitude+","+longitude);
        new LocationAsyncTask(latitude + "," + longitude, new LocationAsyncTask.OnTaskFin() {
            @Override
            public void onTaskFin(String city,String town) {
                callBack.callback(true,OK_MESSAGE,city,town);
            }
        }).execute();
    }


}
