package com.example.administrator.foundationdemo.map;

import android.graphics.Color;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapOptions;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.MyLocationStyle;
import com.brothers.fly.aichebao.R;

public class MapActivity extends AppCompatActivity implements AMapLocationListener, LocationSource {

    private MapView mapView;
    private AMap aMap;
    private AMapLocationClient locationClient;
    private AMapLocationClientOption option;
    private static final String TAG = "FLY.Map";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        initMap(savedInstanceState);

    }


    private void initMap(Bundle savedInstanceState) {
        mapView = (MapView) findViewById(R.id.map_viewv);
        mapView.onCreate(savedInstanceState);

        if (aMap == null) {
            aMap = mapView.getMap();
            aMap.setTrafficEnabled(true);// 显示实时交通状况
            //地图模式可选类型：
            // 标准：MAP_TYPE_NORMAL
            // 卫星：MAP_TYPE_SATELLITE
            // 夜间：MAP_TYPE_NIGHT
            aMap.setMapType(AMap.MAP_TYPE_NORMAL);
        }
        UiSettings uiSettings = aMap.getUiSettings();
        uiSettings.setZoomPosition(AMapOptions.ZOOM_POSITION_RIGHT_CENTER);
        aMap.setTrafficEnabled(true);// 显示实时交通状况
        //地图模式可选类型：
        // 导航：MAP_TYPE_NAVI
        // 标准：MAP_TYPE_NORMAL
        // 卫星：MAP_TYPE_SATELLITE
        // 夜间：MAP_TYPE_NIGHT
        aMap.setMapType(AMap.MAP_TYPE_NAVI);
        //地图监听定位
        aMap.setLocationSource(this);
        //初始化定位蓝点样式类
        MyLocationStyle myLocationStyle = new MyLocationStyle();
//        myLocationStyle.myLocationIcon(BitmapDescriptorFactory.fromResource(R.drawable.map_dian));// 设置小蓝点的图标  
        myLocationStyle.strokeColor(Color.DKGRAY);// 设置圆形的边框颜色  
        myLocationStyle.radiusFillColor(Color.argb(100, 0, 0, 180));// 设置圆形的填充颜色  
        myLocationStyle.strokeWidth(0f);// 设置圆形的边框粗细  
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle.interval(3000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style

        aMap.getUiSettings().setMyLocationButtonEnabled(true);//设置默认定位按钮是否显示，非必需设置。
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。

        initLocationClient();
    }

    private void initLocationClient() {

        if (locationClient == null) {
            //初始化定位
            locationClient = new AMapLocationClient(this);
        }
        if (option == null) {
            //初始化定位参数
            option = new AMapLocationClientOption();
            //设置为高精度定位模式
            option.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        }
        //设置定位回调监听
        locationClient.setLocationListener(this);
        locationClient.setLocationOption(option);
        // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
        // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
        // 在定位结束后，在合适的生命周期调用onDestroy()方法
        // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
        locationClient.startLocation();
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (mapView != null) {
            //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
            mapView.onResume();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mapView != null) {
            //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
            mapView.onPause();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mapView != null) {
            //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
            mapView.onDestroy();
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null) {
            if (aMapLocation.getErrorCode() == 0) {
                // 显示系统小蓝点
                mListener.onLocationChanged(aMapLocation);
                locationClient.stopLocation();//停止定位  
            }

        } else {
            Log.d(TAG, "onLocationChanged: " + aMapLocation.getErrorCode() + aMapLocation.getErrorInfo());
        }
    }

    OnLocationChangedListener mListener;

    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        mListener = onLocationChangedListener;

        if (locationClient == null) {
            locationClient = new AMapLocationClient(this);
        }
        if (option == null) {
            option = new AMapLocationClientOption();
            //设置GPS定位优先，即使设置高精度定位模式，它也会优先GPS在室内定位很差，最好不要设置，就默认的也就是false;
            option.setGpsFirst(true);
            //高精度定位模式
            option.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        }
        //设置定位，onLocationChanged就是这个接口的方法
        locationClient.setLocationListener(this);
        locationClient.setLocationOption(option);
        //开始定位
        locationClient.startLocation();
    }

    @Override
    public void deactivate() {
        if (locationClient.isStarted())
            locationClient.stopLocation();
        locationClient = null;
        mListener = null;
    }

}
