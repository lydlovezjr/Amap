package com.lyd.lyd.amap;

import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.MyLocationStyle;

/**
 * 描述: 地图
 * 作者: LYD
 * 所需参数: 无
 * 创建日期: 2018/5/2 16:45
 */
public class MapActivity extends AppCompatActivity {

    private MapView mapView;//MapView 是地图容器
    private AMap aMap;//AMap 类是地图的控制器类，用来操作地图。
    private UiSettings mUiSettings;//定义一个UiSetting对象

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        mapView = findViewById(R.id.map);

        mapView.onCreate(savedInstanceState);// 此方法必须重写,创建地图
        initAmap();
    }

    /**
     * 初始化地图控制器对象
     */
    private void initAmap() {
        if (aMap == null) {
            aMap = mapView.getMap();
            setMap();
        }
    }

    /**
     * 设置map的属性
     */
    private void setMap() {
        MyLocationStyle myLocationStyle = new MyLocationStyle();
        //是否显示定位蓝点
        myLocationStyle.showMyLocation(true);

        //自定义定位蓝点图标
        //myLocationStyle.myLocationIcon(BitmapDescriptorFactory.fromResource(R.drawable.location));

        //自定义精度圆圈
        //myLocationStyle.strokeColor(ContextCompat.getColor(this.getApplicationContext(), R.color.colorPrimary));//设置定位蓝点精度圆圈的边框颜色的方法。
        //myLocationStyle.strokeWidth(10.0f);//设置定位蓝点精度圆圈的粗细
        //myLocationStyle.radiusFillColor(ContextCompat.getColor(this.getApplicationContext(), R.color.colorAccent));//设置定位蓝点精度圆圈的填充颜色的方法。

        //定位蓝点展现模式
        //myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_SHOW);//只定位一次。
        //myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE);//定位一次，且将视角移动到地图中心点。
        //myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_FOLLOW);//连续定位、且将视角移动到地图中心点，定位蓝点跟随设备移动。（1秒1次定位）
        //myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_MAP_ROTATE);//连续定位、且将视角移动到地图中心点，地图依照设备方向旋转，定位点会跟随设备移动。（1秒1次定位）
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);
        //连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）默认执行此种模式。
        //以下三种模式从5.1.0版本开始提供
        //myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE_NO_CENTER);
        //连续定位、蓝点不会移动到地图中心点，定位点依照设备方向旋转，并且蓝点会跟随设备移动。
        //myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_FOLLOW_NO_CENTER);//连续定位、蓝点不会移动到地图中心点，并且蓝点会跟随设备移动。
        //myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_MAP_ROTATE_NO_CENTER);//连续定位、蓝点不会移动到地图中心点，地图依照设备方向旋转，并且蓝点会跟随设备移动。

        //自定义定位频次
        myLocationStyle.interval(1000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style


        //开启室内地图方法
        aMap.showIndoorMap(false);

        //切换地图图层
        //aMap.setMapType(aMap.MAP_TYPE_NAVI); //导航地图
        //aMap.setMapType(AMap.MAP_TYPE_NIGHT);//夜景地图
        aMap.setMapType(AMap.MAP_TYPE_NORMAL);//白昼地图（即普通地图）
        //aMap.setMapType(AMap.MAP_TYPE_SATELLITE);//卫星图

        //显示英文地图
        aMap.setMapLanguage("zh_cn");//默认
        //aMap.setMapLanguage("en");//

        //获取经纬度信息
        aMap.setOnMyLocationChangeListener(new AMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {
                //从location对象中获取经纬度信息，地址描述信息，建议拿到位置之后调用逆地理编码接口获取（获取地址描述数据章节有介绍）
            }
        });
        //是否定位
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。

        //自定义地图 需要升级
        //可视化自定义地图模版改变底图颜色和样式，实现可视化的编辑和控制显示地图元素。

        //控件交互
        mUiSettings = aMap.getUiSettings();
        //是否显示定位按钮
        mUiSettings.setMyLocationButtonEnabled(true);//设置默认定位按钮是否显示，非必需设置。
        //是否显示缩放按钮
        //mUiSettings.setZoomControlsEnabled(false);
        //获取和设置缩放按钮的位置
        //        int zoomPosition = mUiSettings.getZoomPosition();
        //        Log.i("lyd", "缩放按钮的位置前==" + zoomPosition);// 1
        //        mUiSettings.setZoomPosition(2);
        //        int zoomPositionAfter = mUiSettings.getZoomPosition();
        //        Log.i("lyd", "缩放按钮的位置后==" + zoomPositionAfter);
        //设置是否显示指南针
        mUiSettings.setCompassEnabled(true);
        //设置是否显示比例尺
        mUiSettings.setScaleControlsEnabled(true);
        //设置高德地图Logo的位置
        //mUiSettings.setLogoPosition(AMapOptions.LOGO_POSITION_BOTTOM_LEFT);默认地图左下角

        //手势交互
        //mUiSettings.setZoomGesturesEnabled(false);
        //mUiSettings.setScrollGesturesEnabled(false);
        //mUiSettings.setRotateGesturesEnabled(false);
        //mUiSettings.setTiltGesturesEnabled(false);
        mUiSettings.setAllGesturesEnabled(true);
        boolean zoomGesturesEnabled = mUiSettings.isZoomGesturesEnabled();
        boolean scrollGesturesEnabled = mUiSettings.isScrollGesturesEnabled();
        boolean rotateGesturesEnabled = mUiSettings.isRotateGesturesEnabled();
        boolean tiltGesturesEnabled = mUiSettings.isTiltGesturesEnabled();
        Log.i("lyd", "缩放手势==" + zoomGesturesEnabled + "滑动手势==" + scrollGesturesEnabled + "旋转手势==" + rotateGesturesEnabled + "倾斜手势==" + tiltGesturesEnabled);
        //指定屏幕中心点的手势操作
        //aMap.setPointToCenter(800, 1500);
        //mUiSettings.setGestureScaleByMapCenter(true);

        //调用方法交互
        ////参数依次是：视角调整区域的中心点坐标、希望调整到的缩放级别、俯仰角0°~45°（垂直与地图时为0）、偏航角 0~360° (正北方为0)
        //改变地图的中心点
        //CameraUpdate mCameraUpdate = CameraUpdateFactory.newCameraPosition(new CameraPosition(new LatLng(0, 0), 0, 0, 0));
        //aMap.animateCamera(mCameraUpdate);
        //aMap.moveCamera(mCameraUpdate);
        //改变地图的缩放级别  一共分为 17 级，从 3 到 19。数字越大，展示的图面信息越精细。
        CameraUpdate mCameraUpdate = CameraUpdateFactory.zoomTo(17);
        aMap.moveCamera(mCameraUpdate);
        //限制显示范围
        //        LatLng southwestLatLng = new LatLng(34.7534400000, 113.6314160000);
        //        LatLng northeastLatLng = new LatLng(35.7534400000, 114.6314160000);
        //        LatLngBounds latLngBounds = new LatLngBounds(southwestLatLng, northeastLatLng);
        //        aMap.setMapStatusLimits(latLngBounds);

        //地图截图
        /*aMap.getMapScreenShot(new AMap.OnMapScreenShotListener() {
            @Override
            public void onMapScreenShot(Bitmap bitmap) {

            }

            @Override
            public void onMapScreenShot(Bitmap bitmap, int i) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                if (null == bitmap) {
                    return;
                }
                try {
                    FileOutputStream fos = new FileOutputStream(Environment.getExternalStorageDirectory() + "/lydtest_" + sdf.format(new Date()) + ".png");
                    //boolean b = bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
                    try {
                        fos.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    StringBuffer buffer = new StringBuffer();
                    if (b)
                        buffer.append("截屏成功 ");
                    else {
                        buffer.append("截屏失败 ");
                    }
                    Toast.makeText(MapActivity.this, buffer.toString(), Toast.LENGTH_SHORT).show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });*/

        //在地图上绘制
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mapView.onDestroy()，销毁地图
        mapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mapView.onResume ()，重新绘制加载地图
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mapView.onPause ()，暂停地图的绘制
        mapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mapView.onSaveInstanceState (outState)，保存地图当前的状态
        mapView.onSaveInstanceState(outState);
        //GLSurfaceView;
        //TextureView;
    }
}
