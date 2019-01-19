package com.example.yujun.myapplication;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.GroundOverlayOptions;
import com.baidu.mapapi.map.LogoPosition;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.TextureMapView;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import com.baidu.mapapi.search.core.RouteLine;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.IndoorRouteResult;
import com.baidu.mapapi.search.route.MassTransitRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRoutePlanOption;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.baidu.mapapi.utils.DistanceUtil;
import com.example.yujun.myapplication.control.exhibitionroom;
import com.example.yujun.myapplication.listener.MyOrientationListener;
import com.example.yujun.myapplication.listener.NetWorkStateReceiver;
import com.example.yujun.myapplication.overlayutil.WalkingRouteOverlay;
import com.example.yujun.myapplication.utils.ExhAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements ActionBar.TabListener, View.OnClickListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v13.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    // 百度地图相关
    private double mLatitude;
    private double mLongtitude;
    private MyOrientationListener myOrientationListener;
    private float mCurrentX;
    TextureMapView mMapView;
    BaiduMap mBaiduMap;
    boolean showWalkFlag = true;
    boolean showMusumeFlag = true;
    NetWorkStateReceiver netWorkStateReceiver;

    // 展览相关
    private List<exhibitionroom> exhroomList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_main);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // Set up the action bar.
        final ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        // When swiping between different sections, select the corresponding
        // tab. We can also use ActionBar.Tab#select() to do this if we have
        // a reference to the Tab.
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });

        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by
            // the adapter. Also specify this Activity object, which implements
            // the TabListener interface, as the callback (listener) for when
            // this tab is selected.
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(mSectionsPagerAdapter.getPageTitle(i))
                            .setTabListener(this));
        }

        if (netWorkStateReceiver == null) {
            netWorkStateReceiver = new NetWorkStateReceiver();
        }
        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(netWorkStateReceiver, filter);
        System.out.println("注册");

        initLocation();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

//        MenuItem searchItem = menu.findItem(R.id.action_search);
//        SearchView searchView =
//                (SearchView) MenuItemCompat.getActionView(searchItem);
        // Configure the search info and add any event listeners...

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch(id){
            case R.id.action_info:
                startActivity(new Intent(MainActivity.this, MusumeInfoActivity.class));
                return true;
            case R.id.action_qr:
                startActivity(new Intent(MainActivity.this, CaptureActivity.class));
                return true;
            case R.id.action_settings:
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, SettingsActivity.class);
                MainActivity.this.startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in
        // the ViewPager.

        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    //static
    @SuppressLint("validFragment")
    public class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        //static
        private  final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.error, container, false);
            int id = getArguments().getInt(ARG_SECTION_NUMBER);

            switch (id){
                case 1:
                    rootView = inflater.inflate(R.layout.fragment_map, container, false);
                    initMapView(rootView);
                    break;
                case 2:
                    rootView = inflater.inflate(R.layout.fragment_exhibitions, container, false);
                    initExhRoom(rootView);
                    break;
                case 3:
                    rootView = inflater.inflate(R.layout.fragment_things, container, false);
                    Button button1 = (Button)rootView.findViewById(R.id.fruit_btn1);
                    Button button2 = (Button)rootView.findViewById(R.id.fruit_btn2);
                    Button button3 = (Button)rootView.findViewById(R.id.fruit_btn3);
                    Button button4 = (Button)rootView.findViewById(R.id.fruit_btn4);
                    Button button5 = (Button)rootView.findViewById(R.id.fruit_btn5);
                    button1.setOnClickListener(MainActivity.this);
                    button2.setOnClickListener(MainActivity.this);
                    button3.setOnClickListener(MainActivity.this);
                    button4.setOnClickListener(MainActivity.this);
                    button5.setOnClickListener(MainActivity.this);
                    break;
                default:
                    break;
            }
            return rootView;
        }
    }

    @Override
    public void onClick(View v) {
        String data = "first";
        switch(v.getId()){
            case R.id.fruit_btn1:
                break;
            case R.id.fruit_btn2:
                data = "second";
                break;
            case R.id.fruit_btn3:
                data = "third";
                break;
            case R.id.fruit_btn4:
                data = "fourth";
                break;
            case R.id.fruit_btn5:
                data = "fifth";
                break;
            default:
                break;
        }
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, ThirdActivity.class);
        intent.putExtra("button", data);
        MainActivity.this.startActivity(intent);
    }

        /**
         * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
         * one of the sections/tabs/pages.
         */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).

            String ARG_SECTION_NUMBER = "section_number";
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, position + 1);
            fragment.setArguments(args);

            return fragment;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "博物馆导览";
                case 1:
                    return "展览导览";
                case 2:
                    return "文物导览";
            }
            return null;
        }
    }

    // 展览相关
    public void initExhRoom(View v){

        String[] name = new String[5];
        name[0] = (String) this.getResources().getText(R.string.img_but01);
        name[1] = (String) this.getResources().getText(R.string.img_but02);
        name[2] = (String) this.getResources().getText(R.string.img_but03);
        name[3] = (String) this.getResources().getText(R.string.img_but04);
        name[4] = (String) this.getResources().getText(R.string.img_but05);

        final exhibitionroom first = new exhibitionroom(name[0],R.drawable.first_museum,"first");
        exhroomList.add(first);
        final exhibitionroom second = new exhibitionroom(name[1],R.drawable.second_museum,"second");
        exhroomList.add(second);
        final exhibitionroom third = new exhibitionroom(name[2],R.drawable.third_museum,"third");
        exhroomList.add(third);
        final exhibitionroom four = new exhibitionroom(name[3],R.drawable.tangpic_museum,"fourth");
        exhroomList.add(four);
        final exhibitionroom fifth = new exhibitionroom(name[4],R.drawable.tang_museum, "fifth");
        exhroomList.add(fifth);

        ExhAdapter adapter = new ExhAdapter(MainActivity.this,R.layout.fragment_exhibitions,exhroomList);

        ListView listView = (ListView)v.findViewById(R.id.list_view);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener( ) {
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                        exhibitionroom exhroom = exhroomList.get(position);
                        String data = exhroom.getNumber();
                        Intent intent = new Intent(MainActivity.this,secondActivity.class);
                        if( exhroomList.get(position).equals(first)){
                            intent.putExtra("list", data);
                        }
                        if( exhroomList.get(position).equals(second)){
                            intent.putExtra("list", data);
                        }
                        if( exhroomList.get(position).equals(third)){
                            intent.putExtra("list", data);
                        }
                        if( exhroomList.get(position).equals(four)){
                            intent.putExtra("list", data);
                        }
                        if( exhroomList.get(position).equals(fifth)){
                            intent.putExtra("list", data);
                        }
                        startActivity(intent);
                    }
                }
        );
    }

    // 百度地图相关
    public  void initMapView(View v){

        //获取地图控件引用
        mMapView = (TextureMapView)v.findViewById(R.id.mTexturemap);
        mBaiduMap = mMapView.getMap();

        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
        mBaiduMap.setIndoorEnable(true);

        // map type
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);

        // baidu logo
        mMapView.setLogoPosition(LogoPosition.logoPostionleftTop);

        //getLocationByLL(mLatitude, mLongtitude);
        mBaiduMap.setMapStatus(MapStatusUpdateFactory.zoomTo((float)19.0));
        getLocationByLL(34.230637, 108.961711);

        // 博物馆固定定位
        Button homeBtn = (Button)v.findViewById(R.id.homeBtn);
        homeBtn.setOnClickListener(new HomeClickListener());

        // 手机定位
        Button locateBtn = (Button)v.findViewById(R.id.locateBtn);
        locateBtn.setOnClickListener(new LocateClickListener());

        // 展览marker入口
        final Button showMusumeBtn = (Button)v.findViewById(R.id.musumeBtn);
        showMusumeBtn.setOnClickListener(new MyMusumeClickListener());

        // 导览路线图片覆盖
        final Button walkBtn = (Button)v.findViewById(R.id.walkBtn);
        walkBtn.setOnClickListener(new WalkClickListener());

        // 步行导航：手机定位->博物馆位置
        final Button navigateBtn = (Button)v.findViewById(R.id.navigateBtn);
        navigateBtn.setOnClickListener(new NavigateClickListener());
    }

    private class MyLocationListener implements BDLocationListener{
        // 定位后的回调
        @Override
        public void onReceiveLocation(BDLocation location) {

            MyLocationData data = new MyLocationData.Builder()
                    .direction(mCurrentX)
                    .accuracy(location.getRadius())
                    .latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();

            mBaiduMap.setMyLocationData(data);

            mLatitude = location.getLatitude();
            mLongtitude = location.getLongitude();
        }
    }

    public void getLocationByLL(double la, double lg)
    {
        //地理坐标的数据结构
        LatLng latLng = new LatLng(la, lg);
        //描述地图状态将要发生的变化,通过当前经纬度来使地图显示到该位置
        MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(latLng);
        mBaiduMap.animateMapStatus(msu);
    }

    public void initLocation(){
        LocationClient mLocationClient = new LocationClient(this);
        MyLocationListener mLocationListener = new MyLocationListener();
        mLocationClient.registerLocationListener(mLocationListener);

        LocationClientOption option = new LocationClientOption();
        option.setCoorType("bd09ll");
        option.setIsNeedAddress(true);
        option.setOpenGps(true);
        option.setScanSpan(1000);

        mLocationClient.setLocOption(option);

        myOrientationListener = new MyOrientationListener(MainActivity.this);
        myOrientationListener.setOnOrientationListener(new MyOrientationListener.OnOrientationListener() {
            @Override
            public void OnOrientationChanged(float x) {
                mCurrentX = x;
            }
        });

        // 开启定位
        if(!mLocationClient.isStarted())
            mLocationClient.start();

        // 开启方向传感器
        myOrientationListener.start();
    }

    public class LocateClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            BitmapDescriptor mMarker = BitmapDescriptorFactory.fromResource(R.drawable.point);
            MyLocationConfiguration configuration = new MyLocationConfiguration(
                    MyLocationConfiguration.LocationMode.NORMAL, true, mMarker);
            mBaiduMap.setMyLocationConfiguration(configuration);
            MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo((float)16.0);
            mBaiduMap.setMapStatus(msu);

            getLocationByLL(mLatitude, mLongtitude);
        }
    }

    public class HomeClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            mBaiduMap.setMapStatus(MapStatusUpdateFactory.zoomTo((float)19.0));
            getLocationByLL(34.230637, 108.961711);
        }
    }

    public class WalkClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            if (showWalkFlag) {
                mBaiduMap.clear();

                //定义Ground的显示地理范围
                LatLng southwest = new LatLng(34.229085, 108.959793);
                LatLng northeast = new LatLng(34.2321, 108.962955);
                LatLngBounds bounds = new LatLngBounds.Builder()
                        .include(northeast)
                        .include(southwest)
                        .build();

                //定义Ground显示的图片
                BitmapDescriptor bdGround = BitmapDescriptorFactory
                        .fromResource(R.drawable.musume);

                //定义Ground覆盖物选项
                OverlayOptions ooGround = new GroundOverlayOptions()
                        .positionFromBounds(bounds)
                        .image(bdGround)
                        .transparency(1.0f);

                //在地图中添加Ground覆盖物
                mBaiduMap.addOverlay(ooGround);

                showWalkFlag = false;
            } else {
                mBaiduMap.clear();

                showWalkFlag = true;
            }

        }
    }

    public class MyMusumeClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (showMusumeFlag) {
                mBaiduMap.clear();

                //创建OverlayOptions的集合
                List<OverlayOptions> options = new ArrayList<OverlayOptions>();
                //设置坐标点
                LatLng point1 = new LatLng(34.230159, 108.961697);
                LatLng point2 = new LatLng(34.2305, 108.962425);
                LatLng point3 = new LatLng(34.230772, 108.961724);
                LatLng point4 = new LatLng(34.230697, 108.961059);
                LatLng point5 = new LatLng(34.230123, 108.961032);
                //创建OverlayOptions属性
                BitmapDescriptor ex1 = BitmapDescriptorFactory
                        .fromResource(R.drawable.ex1);
                BitmapDescriptor ex2 = BitmapDescriptorFactory
                        .fromResource(R.drawable.ex2);
                BitmapDescriptor ex3 = BitmapDescriptorFactory
                        .fromResource(R.drawable.ex3);
                BitmapDescriptor ex4 = BitmapDescriptorFactory
                        .fromResource(R.drawable.ex4);
                BitmapDescriptor ex5 = BitmapDescriptorFactory
                        .fromResource(R.drawable.ex5);
                OverlayOptions option1 = new MarkerOptions()
                        .position(point1)
                        .title("first")
                        .icon(ex1);
                OverlayOptions option2 = new MarkerOptions()
                        .position(point2)
                        .title("second")
                        .icon(ex2);
                OverlayOptions option3 = new MarkerOptions()
                        .position(point3)
                        .title("third")
                        .icon(ex3);
                OverlayOptions option4 = new MarkerOptions()
                        .position(point4)
                        .title("fourth")
                        .icon(ex4);
                OverlayOptions option5 = new MarkerOptions()
                        .position(point5)
                        .title("fifth")
                        .icon(ex5);
                //将OverlayOptions添加到list
                options.add(option1);
                options.add(option2);
                options.add(option3);
                options.add(option4);
                options.add(option5);

                //在地图上批量添加
                mBaiduMap.addOverlays(options);

                mBaiduMap.setOnMarkerClickListener(new MarkerClickListener());

                showMusumeFlag = false;
            } else {
                mBaiduMap.clear();
                showMusumeFlag = true;
            }
        }
    }

    public class MarkerClickListener implements BaiduMap.OnMarkerClickListener {

        @Override
        public boolean onMarkerClick(Marker marker) {

            String title = marker.getTitle();
            Intent intent = new Intent(MainActivity.this,secondActivity.class);
            intent.putExtra("list", title);
            startActivity(intent);
            return true;
        }

    }

    public class NavigateClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            RoutePlanSearch mSearch = RoutePlanSearch.newInstance();
            RouteLine route = null;
            // 陕西历史博物馆附近
            final LatLng stPoint = new LatLng(34.230037, 108.961011);
//            LatLng stPoint = new LatLng(mLatitude, mLongtitude);
            final LatLng enPoint = new LatLng(34.230637, 108.961711);

            mSearch.setOnGetRoutePlanResultListener(new OnGetRoutePlanResultListener() {
                @Override
                public void onGetWalkingRouteResult(WalkingRouteResult walkingRouteResult) {
                    if (walkingRouteResult == null || walkingRouteResult.error != SearchResult.ERRORNO.NO_ERROR) {
                        Toast.makeText(MainActivity.this, "抱歉，规划路线失败",
                                Toast.LENGTH_SHORT).show();
                    }
                    if (walkingRouteResult.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
                        // 起终点或途经点地址有岐义，通过以下接口获取建议查询信息
                        // result.getSuggestAddrInfo()
                        return;
                    }
                    if (walkingRouteResult.error == SearchResult.ERRORNO.NO_ERROR) {

                        WalkingRouteOverlay overlay = new WalkingRouteOverlay(mBaiduMap);
                        mBaiduMap.setOnMarkerClickListener(overlay);
                        overlay.setData(walkingRouteResult.getRouteLines().get(0));
                        overlay.addToMap();
                        overlay.zoomToSpan();

                        double distance = DistanceUtil. getDistance(stPoint, enPoint);

                        Toast.makeText(MainActivity.this, "温馨提示您：您距离陕西历史博物馆" + String.format("%.1f", distance) + "米",
                                Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onGetTransitRouteResult(TransitRouteResult transitRouteResult) {

                }

                @Override
                public void onGetMassTransitRouteResult(MassTransitRouteResult massTransitRouteResult) {

                }

                @Override
                public void onGetDrivingRouteResult(DrivingRouteResult drivingRouteResult) {

                }

                @Override
                public void onGetIndoorRouteResult(IndoorRouteResult indoorRouteResult) {

                }

                @Override
                public void onGetBikingRouteResult(BikingRouteResult bikingRouteResult) {

                }
            });

            PlanNode startPlanNode = PlanNode.withLocation(stPoint);
            PlanNode endPlanNode = PlanNode.withLocation(enPoint);

            mSearch.walkingSearch((new WalkingRoutePlanOption())
                    .from(startPlanNode)
                    .to(endPlanNode));

//            mSearch.destroy();
        }
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(netWorkStateReceiver);
        System.out.println("注销");
        super.onDestroy();
    }

}