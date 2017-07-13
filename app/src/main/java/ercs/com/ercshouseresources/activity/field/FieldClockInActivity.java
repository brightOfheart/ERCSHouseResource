package ercs.com.ercshouseresources.activity.field;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.Poi;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.inner.GeoPoint;

import java.io.File;
import java.util.Calendar;
import java.util.TimeZone;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.activity.BaseActivity;
import ercs.com.ercshouseresources.base.BaseApplication;
import ercs.com.ercshouseresources.bean.UpLoadPicBean;
import ercs.com.ercshouseresources.network.HttpUtils;
import ercs.com.ercshouseresources.network.MyGson;
import ercs.com.ercshouseresources.network.NetHelper;
import ercs.com.ercshouseresources.service.LocationService;
import ercs.com.ercshouseresources.util.OtherUitl;
import ercs.com.ercshouseresources.util.SPUtil;
import ercs.com.ercshouseresources.util.TitleControl;
import ercs.com.ercshouseresources.util.ToastUtil;
import ercs.com.ercshouseresources.view.dialog.LoadingDialog;
import static ercs.com.ercshouseresources.util.OtherUitl.getBitmapDegree;
import static ercs.com.ercshouseresources.util.OtherUitl.rotateBitmapByDegree;

/**
 * Created by Administrator on 2017/7/5.
 * 外勤打卡
 */

public class FieldClockInActivity extends BaseActivity {
    @BindView(R.id.bmapView)
    MapView mMapView;//百度地图
    @BindView(R.id.tv_address)
    TextView tv_address;//定位地址
    @BindView(R.id.tv_time)
    TextView tv_time;//显示当前的系统时间
    @BindView(R.id.iv_pic)
    ImageView iv_pic;//显示拍照的照片
    @BindView(R.id.edit_content)
    EditText edit_content;//填写外出事由
    private LocationService locationService;
    private double latNow, lngNow;
    private String Id, CreatorId, StartLocation,streetDetail;
    private LoadingDialog dialog;
    private BaiduMap mBaidumap;
    private static final int msgKey1 = 1;
    private String cameraPath = "";
    public final static int CAMERA_REQUEST_CODE = 3;
    public static String SAVED_IMAGE_DIR_PATH =
            Environment.getExternalStorageDirectory().getPath()
                    + "/AppName/camera/";// 拍照路径
    private String id = "";
    private SPUtil spUtil;
    public String StartImagePage = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fieldclcokin);
        ButterKnife.bind(this);
        initTitle();
        create();
    }

    /**
     * 设置标题栏
     */
    private void initTitle() {
        TitleControl t = new TitleControl(this);
        t.setTitle(getString(R.string.str_fieldclockin));

    }

    /**
     * 初始化
     */
    private void create() {
        dialog = new LoadingDialog(FieldClockInActivity.this, 0);
        mBaidumap = mMapView.getMap();
        if (spUtil == null)
            spUtil = new SPUtil(this, "fileName");
        id = spUtil.getString(BaseApplication.ID, "");
        new TimeThread().start();
        setLocation();
    }

    /**
     * 设置定位
     */
    private void setLocation() {
        locationService = ((BaseApplication) getApplication()).locationService;
        //获取locationservice实例，建议应用中只初始化1个location实例，然后使用，可以参考其他示例的activity，都是通过此种方式获取locationservice实例的
        locationService.registerListener(mListener);
        //注册监听
        int type = getIntent().getIntExtra("from", 0);
        if (type == 0) {
            locationService.setLocationOption(locationService.getDefaultLocationClientOption());
        } else if (type == 1) {
            locationService.setLocationOption(locationService.getOption());
        }
        locationService.start();// 定位SDK
        dialog.show();
    }

    /**
     * 点击事件的处理
     *
     * @param view
     */
    @OnClick({R.id.ly_clockin, R.id.btn_post})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ly_clockin://拍照打卡
                savePath();
                break;
            case R.id.btn_post://提交
                if (edit_content.getText().toString().length() > 5 && edit_content.getText().toString().length() < 50) {
                    if (StartImagePage.length() > 0)
                        postData(edit_content.getText().toString());
                    else
                        ToastUtil.showToast(this, "请上传照片!");
                } else {
                    ToastUtil.showToast(this, "外出事由最少不能低于5个字,最多不能超过50个字!");
                }
                break;

        }
    }

    /**
     * 提交数据接口
     */
    private void postData(String str_content) {
        dialog.show();
        NetHelper.outsidePunchClock(StartLocation, StartImagePage, id, str_content, new HttpUtils.HttpCallback() {
            @Override
            public void onSuccess(String data) {
                dialog.dismiss();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
            }

            @Override
            public void onError(String msg) {
                super.onError(msg);
                dialog.dismiss();
                ToastUtil.showToast(getApplicationContext(), msg);
            }
        });
    }

    /**
     * 指定相机拍摄照片保存地址
     */
    private void savePath() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            cameraPath = SAVED_IMAGE_DIR_PATH + System.currentTimeMillis() + ".png";
            Intent intent = new Intent();
            // 指定开启系统相机的Action
            intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
            // intent.setAction("android.media.action.STILL_IMAGE_CAMERA");
            String out_file_path = SAVED_IMAGE_DIR_PATH;
            File dir = new File(out_file_path);
            if (!dir.exists()) {
                dir.mkdirs();
            } // 把文件地址转换成Uri格式
            Uri uri = Uri.fromFile(new File(cameraPath));
            // 设置系统相机拍摄照片完成后图片文件的存放地址
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            startActivityForResult(intent, CAMERA_REQUEST_CODE);
        } else {
            Toast.makeText(getApplicationContext(), "请确认已经插入SD卡",
                    Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == CAMERA_REQUEST_CODE) {
                if (!cameraPath.equals("")) {
                    try {
                        Bitmap photoBmp = OtherUitl.getBitmapFormUri(FieldClockInActivity.this, Uri.fromFile(new File(cameraPath)));
                        int degree = getBitmapDegree(cameraPath);
                        Bitmap newbitmap = rotateBitmapByDegree(photoBmp, degree);
                        iv_pic.setImageBitmap(newbitmap);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    String str = OtherUitl.imageToBase64(cameraPath);
                    upLoadPic(str);
                } else {
                    ToastUtil.showToast(this, "请选择图片");
                }

            }
        }
    }

    /**
     * 上传打卡图片
     */
    private void upLoadPic(String path) {
        dialog.show();
        NetHelper.uploadPic(path, new HttpUtils.HttpCallback() {
            @Override
            public void onSuccess(String data) {
                dialog.dismiss();
                final UpLoadPicBean upLoadPicBean = MyGson.getInstance().fromJson(data, UpLoadPicBean.class);
                if (upLoadPicBean.getType().equals("1")) {
                    StartImagePage = upLoadPicBean.getData().getId();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.showToast(getApplicationContext(), upLoadPicBean.getContent());
                    }
                });
            }

            @Override
            public void onError(String msg) {
                dialog.dismiss();
                super.onError(msg);
                ToastUtil.showToast(getApplicationContext(), msg);
            }
        });

    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        // -----------location config ------------

    }

    /*****
     * 定位结果回调，重写onReceiveLocation方法，可以直接拷贝如下代码到自己工程中修改
     */
    private BDLocationListener mListener = new BDLocationListener() {

        @Override
        public void onReceiveLocation(BDLocation location) {
            // TODO Auto-generated method stub
            if (null != location && location.getLocType() != BDLocation.TypeServerError) {
                StringBuffer sb = new StringBuffer(256);
                sb.append("time : ");
                /**
                 * 时间也可以使用systemClock.elapsedRealtime()方法 获取的是自从开机以来，每次回调的时间；
                 * location.getTime() 是指服务端出本次结果的时间，如果位置不发生变化，则时间不变
                 */
                sb.append(location.getTime());
                sb.append("\nlocType : ");// 定位类型
                sb.append(location.getLocType());
                sb.append("\nlocType description : ");// *****对应的定位类型说明*****
                sb.append(location.getLocTypeDescription());
                sb.append("\nlatitude : ");// 纬度
                sb.append(location.getLatitude());
                sb.append("\nlontitude : ");// 经度
                sb.append(location.getLongitude());
                sb.append("\nradius : ");// 半径
                sb.append(location.getRadius());
                sb.append("\nCountryCode : ");// 国家码
                sb.append(location.getCountryCode());
                sb.append("\nCountry : ");// 国家名称
                sb.append(location.getCountry());
                sb.append("\ncitycode : ");// 城市编码
                sb.append(location.getCityCode());
                sb.append("\ncity : ");// 城市
                sb.append(location.getCity());
                sb.append("\nDistrict : ");// 区
                sb.append(location.getDistrict());
                sb.append("\nStreet : ");// 街道
                sb.append(location.getStreet());
                sb.append("\naddr : ");// 地址信息
                sb.append(location.getAddrStr());
                sb.append("\nUserIndoorState: ");// *****返回用户室内外判断结果*****
                sb.append(location.getUserIndoorState());
                sb.append("\nDirection(not all devices have value): ");
                sb.append(location.getDirection());// 方向
                sb.append("\nlocationdescribe: ");
                sb.append(location.getLocationDescribe());// 位置语义化信息
                sb.append("\nPoi: ");// POI信息
                if (location.getPoiList() != null && !location.getPoiList().isEmpty()) {
                    for (int i = 0; i < location.getPoiList().size(); i++) {
                        Poi poi = (Poi) location.getPoiList().get(i);
                        sb.append(poi.getName() + ";");
                    }
                }
                if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
                    sb.append("\nspeed : ");
                    sb.append(location.getSpeed());// 速度 单位：km/h
                    sb.append("\nsatellite : ");
                    sb.append(location.getSatelliteNumber());// 卫星数目
                    sb.append("\nheight : ");
                    sb.append(location.getAltitude());// 海拔高度 单位：米
                    sb.append("\ngps status : ");
                    sb.append(location.getGpsAccuracyStatus());// *****gps质量判断*****
                    sb.append("\ndescribe : ");
                    sb.append("gps定位成功");
                } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
                    // 运营商信息
                    if (location.hasAltitude()) {// *****如果有海拔高度*****
                        sb.append("\nheight : ");
                        sb.append(location.getAltitude());// 单位：米
                    }
                    sb.append("\noperationers : ");// 运营商信息
                    sb.append(location.getOperators());
                    sb.append("\ndescribe : ");
                    sb.append("网络定位成功");
                } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
                    sb.append("\ndescribe : ");
                    sb.append("离线定位成功，离线定位结果也是有效的");
                } else if (location.getLocType() == BDLocation.TypeServerError) {
                    sb.append("\ndescribe : ");
                    sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
                } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
                    sb.append("\ndescribe : ");
                    sb.append("网络不同导致定位失败，请检查网络是否通畅");
                } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
                    sb.append("\ndescribe : ");
                    sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
                }
                latNow = location.getLatitude();
                lngNow = location.getLongitude();
                StartLocation = location.getAddrStr();
                 streetDetail = location.getDistrict()+location.getStreet();
                logMsg("定位地址:" + location.getCity() + location.getDistrict() + location.getStreet() + location.getStreetNumber());
            }
        }

        public void onConnectHotSpotMessage(String s, int i) {
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
    }

    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        locationService.unregisterListener(mListener); //注销掉监听
        locationService.stop(); //停止定位服务
        super.onStop();
    }


    /**
     * 显示请求字符串
     *
     * @param str
     */
    public void logMsg(final String str) {
        dialog.dismiss();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tv_address.setText(str);
                showCenterPoi();
                showLocation(latNow, lngNow);
            }
        });

        locationService.stop();// 定位SDK
    }

    /**
     * 显示地图中心点
     */
    private void showCenterPoi() {
        LatLng cenpt = new LatLng(latNow, lngNow);
        //定义地图状态
        MapStatus mMapStatus = new MapStatus.Builder()
                .target(cenpt)
                .zoom(19)
                .build();
        //定义MapStatusUpdate对象，以便描述地图状态将要发生的变化
        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
        //改变地图状态
        mBaidumap.setMapStatus(mMapStatusUpdate);
    }

    /**
     * 显示位置信息
     *
     * @param latitude
     * @param longitude
     */
    private void showLocation(Double latitude, Double longitude) {


        //显示气泡
        // 创建InfoWindow展示的view
        LatLng pt = null;
        View view = LayoutInflater.from(this).inflate(R.layout.map_item, null); //自定义气泡形状
        TextView textView = (TextView) view.findViewById(R.id.my_postion);
        textView.setText(streetDetail);
        BitmapDescriptor bd = null;
        try {
            bd = BitmapDescriptorFactory.fromView(view);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (null == bd) {
            return;
        }
        MarkerOptions oo = new MarkerOptions().position(new LatLng(latitude, longitude)).icon(bd);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.down_icon);
        BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(bitmap);
        MarkerOptions oo2 = new MarkerOptions().position(new LatLng(latitude, longitude)).icon(bitmapDescriptor);


        mBaidumap.addOverlay(oo2);
        mBaidumap.addOverlay(oo);





        // Marker mMarker = (Marker) (mBaidumap.addOverlay(oo));
    }

    //获得当前年月日时分秒星期
    public String getTime() {
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        String mHour = String.valueOf(c.get(Calendar.HOUR_OF_DAY));//时
        String mMinute = String.valueOf(c.get(Calendar.MINUTE));//分
        String mSecond = String.valueOf(c.get(Calendar.SECOND));//秒
        mMinute = mMinute.length() == 1 ? "0" + mMinute : mMinute;
        mSecond = mSecond.length() == 1 ? "0" + mSecond : mSecond;
        return mHour + ":" + mMinute + ":" + mSecond;
    }

    public class TimeThread extends Thread {
        @Override
        public void run() {
            do {
                try {
                    Thread.sleep(1000);
                    Message msg = new Message();
                    msg.what = msgKey1;
                    mHandler.sendMessage(msg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } while (true);
        }
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case msgKey1:
                    tv_time.setText(getTime());
                    break;
                default:
                    break;
            }
        }
    };
}
