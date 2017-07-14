package ercs.com.ercshouseresources.activity.clockin;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.Poi;
import com.baidu.mapapi.model.LatLng;
import java.io.File;
import java.text.ParseException;
import java.util.Calendar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.activity.BaseActivity;
import ercs.com.ercshouseresources.base.BaseApplication;
import ercs.com.ercshouseresources.bean.BaseBean;
import ercs.com.ercshouseresources.bean.ClockinLookBean;
import ercs.com.ercshouseresources.bean.ClockinSetBean;
import ercs.com.ercshouseresources.bean.UpLoadPicBean;
import ercs.com.ercshouseresources.network.HttpUtils;
import ercs.com.ercshouseresources.network.MyGson;
import ercs.com.ercshouseresources.network.NetHelper;
import ercs.com.ercshouseresources.service.LocationService;
import ercs.com.ercshouseresources.util.NetWorkUtil;
import ercs.com.ercshouseresources.util.OtherUitl;
import ercs.com.ercshouseresources.util.SPUtil;
import ercs.com.ercshouseresources.util.TimeUtil;
import ercs.com.ercshouseresources.util.TitleControl;
import ercs.com.ercshouseresources.util.ToastUtil;
import ercs.com.ercshouseresources.util.imageUtil.GlideUtil;
import ercs.com.ercshouseresources.view.CustomView;
import ercs.com.ercshouseresources.view.dialog.CustomerDatePickerDialog;
import ercs.com.ercshouseresources.view.dialog.LoadingDialog;
import static ercs.com.ercshouseresources.util.StringUtil.getStr;

/**
 * Created by Administrator on 2017/6/27.
 * 打卡
 */

public class ClockinActivity extends BaseActivity {
    private LocationService locationService;
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.iv_photo)
    ImageView iv_photo;
    @BindView(R.id.ly)
    LinearLayout ly;
    @BindView(R.id.tv_address)
    TextView tv_address;
    @BindView(R.id.tv_name)
    TextView tv_name;
    private SPUtil spUtil;
    @BindView(R.id.tv_tit1)
    TextView tv_tit1;
    @BindView(R.id.tv_ads1)
    TextView tv_ads1;
    @BindView(R.id.tv_sta1)
    TextView tv_sta1;
    @BindView(R.id.tv_tit2)
    TextView tv_tit2;
    @BindView(R.id.tv_ads2)
    TextView tv_ads2;
    @BindView(R.id.tv_sta2)
    TextView tv_sta2;
    @BindView(R.id.tv_timer)
    TextView tv_timer;
    @BindView(R.id.ll_bottom)
    LinearLayout ll_bottom;
    @BindView(R.id.iv_right)
    ImageView iv_right;//考勤范围小对号
    private LoadingDialog dialog;
    private ClockinSetBean clockinSetBean;//打卡设置返回
    private double latNow, lngNow;
    public final static int ALBUM_REQUEST_CODE = 1;
    public final static int CROP_REQUEST = 2;
    public final static int CAMERA_REQUEST_CODE = 3;
    public static String SAVED_IMAGE_DIR_PATH =
            Environment.getExternalStorageDirectory().getPath()
                    + "/AppName/camera/";// 拍照路径
    private String cameraPath = "";
    public boolean bln_UpLoad = false;
    public String StartImagePage = "";
    private String Id, CreatorId, StartLocation;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clockin);
        ButterKnife.bind(this);
        initTitle();
        if (NetWorkUtil.check(getApplicationContext()))
            getNetData(getYear() + "-" + getMonth() + "-" + getDay());

    }

    /**
     * 点击事件的处理
     *
     * @param view
     */
    @OnClick({R.id.tv_timer, R.id.iv_post})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_timer://选择年月
                new CustomerDatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        tv_timer.setText(year+"年"+(month + 1)+"月"+day+"日");
                        if (NetWorkUtil.check(getApplicationContext())) {

                            getNetData(year + "-" + (month + 1) + "-" + day);

                        }
                    }
                }, getYear(), getMonth(), getDay(),1);
                break;
            case R.id.iv_post://上传照片
                savePath();
                break;

        }
    }

    /**
     * 设置标题栏
     */
    private void initTitle() {
        TitleControl t = new TitleControl(this);
        t.setTitle(getStr(R.string.str_clockin));
        if (spUtil == null)
            spUtil = new SPUtil(this, "fileName");
        tv_name.setText(spUtil.getString(BaseApplication.NAME, ""));
        GlideUtil.loadCircleImage(NetHelper.URL + spUtil.getString(BaseApplication.PHOTOPATH, ""), iv_photo);
        dialog = new LoadingDialog(ClockinActivity.this, 0);
        tv_timer.setText(getYear()+"年"+getMonth()+"月"+getDay()+"日");
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        ly.addView(new CustomView(this, image.getLeft(), image.getRight(), image.getTop(), image.getBottom(), image.getHeight()));

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == CAMERA_REQUEST_CODE) {
                if (!cameraPath.equals("")) {
                    String str = OtherUitl.imageToBase64(cameraPath);
                    upLoadPic(str);
                } else {
                    ToastUtil.showToast(this, getStr(R.string.str_chosepic));
                }

            }
        }
    }

    /**
     * 获取网络数据
     */
    private void getNetData(String time) {
        dialog.show();
        NetHelper.inside(time, "", new HttpUtils.HttpCallback() {
            @Override
            public void onSuccess(String data) {
                final ClockinLookBean clockinLookBean = MyGson.getInstance().fromJson(data, ClockinLookBean.class);
                dialog.dismiss();
                if (clockinLookBean.getType().equals("1")) {
                    Id = clockinLookBean.getData().getId();
                    CreatorId = clockinLookBean.getData().getCreatorId();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (clockinLookBean.getType().equals("1")) {
                            ll_bottom.setVisibility(View.VISIBLE);
                            tv_tit1.setText("打卡时间:" + getAllHm(getAllDay(clockinLookBean.getData().getStartTime())) + "(上班时间 " + getHm(clockinLookBean.getData().getAttStrtime()) + ")");
                            tv_ads1.setText(clockinLookBean.getData().getStartLocation());
                            tv_sta1.setText(kind(clockinLookBean.getData().getStartContent()));
                            tv_tit2.setText("打卡时间:" + getAllHm(getAllDay(clockinLookBean.getData().getEndTime())) + "(上班时间 " + getHm(clockinLookBean.getData().getAttEndtime()) + ")");
                            tv_ads2.setText(clockinLookBean.getData().getEndLocation());
                            tv_sta2.setText(kind(clockinLookBean.getData().getEndContent()));
                        }
                        getDatas();
                        ToastUtil.showToast(getApplicationContext(), clockinLookBean.getContent());

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
                    bln_UpLoad = true;
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

    /**
     * 上班打卡接口
     */
    public void pushClocked() {
        dialog.show();
        NetHelper.punchClock(StartImagePage, Id, CreatorId, StartLocation, new HttpUtils.HttpCallback() {
            @Override
            public void onSuccess(String data) {
                final BaseBean baseBean = MyGson.getInstance().fromJson(data, BaseBean.class);
                dialog.dismiss();
                if (baseBean.getType().equals("1")) {
                    getNetData(getYear() + "-" + getMonth() + "-" + getDay());
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.showToast(getApplicationContext(), baseBean.getContent());
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
     * 下班打卡接口
     */
    public void pushClocked2() {
        dialog.show();
        NetHelper.punchClock2(StartImagePage, Id, CreatorId, StartLocation, new HttpUtils.HttpCallback() {
            @Override
            public void onSuccess(String data) {
                final BaseBean baseBean = MyGson.getInstance().fromJson(data, BaseBean.class);
                dialog.dismiss();
                if (baseBean.getType().equals("1")) {
                    getNetData(getYear() + "-" + getMonth() + "-" + getDay());
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.showToast(getApplicationContext(), baseBean.getContent());
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
     * 访问打卡设置的接口
     */
    private void getDatas() {
        dialog.show();
        NetHelper.insideSetInfo("", new HttpUtils.HttpCallback() {
            @Override
            public void onSuccess(String data) {
                dialog.dismiss();
                clockinSetBean = MyGson.getInstance().fromJson(data, ClockinSetBean.class);
                if (clockinSetBean.getType().equals("1")) {
                    isDistanceLive();
                }
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
     * 判断当前是否在打卡范围内
     */
    public boolean isWifiLiveMac() {
        boolean b = false;

        if (clockinSetBean==null||clockinSetBean.getData()==null)
        {
            return false;
        }
        for (int i = 0; i < clockinSetBean.getData().size(); i++) {
            if (NetWorkUtil.getWifiMac(this).equals(clockinSetBean.getData().get(i).getMacAddress())) {
                b = true;
                break;
            }

        }
        return b;
    }

    /**
     * 判断是否在打卡的范围内
     *
     * @return
     */
    public boolean isDistanceLive() {
        boolean b = false;
        if (clockinSetBean==null||clockinSetBean.getData()==null)
        {
            return false;
        }
        for (int i = 0; i < clockinSetBean.getData().size(); i++) {
            String lat = clockinSetBean.getData().get(i).getLatitude();
            String lng = clockinSetBean.getData().get(i).getLongitude();
            if (lat != null && lng != null) {
                LatLng start = new LatLng(latNow, lngNow);
                LatLng end = new LatLng(Double.valueOf(lat), Double.valueOf(lng));
                String radius = clockinSetBean.getData().get(i).getRadius();
                if (getDistance(start, end) <= Double.valueOf(radius)) {
                    b = true;
                    break;
                }
            }
        }

        return b;
    }


    /**
     * 计算两点之间距离
     *
     * @param start
     * @param end
     * @return 米
     */
    public double getDistance(LatLng start, LatLng end) {
        double lat1 = (Math.PI / 180) * start.latitude;
        double lat2 = (Math.PI / 180) * end.latitude;
        double lon1 = (Math.PI / 180) * start.longitude;
        double lon2 = (Math.PI / 180) * end.longitude;
//      double Lat1r = (Math.PI/180)*(gp1.getLatitudeE6()/1E6);
//      double Lat2r = (Math.PI/180)*(gp2.getLatitudeE6()/1E6);
//      double Lon1r = (Math.PI/180)*(gp1.getLongitudeE6()/1E6);
//      double Lon2r = (Math.PI/180)*(gp2.getLongitudeE6()/1E6);
        //地球半径
        double R = 6371;
        //两点间距离 km，如果想要米的话，结果*1000就可以了
        double d = Math.acos(Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon2 - lon1)) * R;
        return d * 1000;
    }

    /**
     * 显示请求字符串
     *
     * @param str
     */
    public void logMsg(final String str) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (isWifiLiveMac()||isDistanceLive())
                {
                    tv_address.setText(str);
                    iv_right.setImageResource(R.mipmap.right);
                }else
                {
                    tv_address.setText(getString(R.string.str_outofbounds));
                    iv_right.setImageResource(R.mipmap.icon_cancel);
                }

            }
        });
        locationService.stop();// 定位SDK
    }

    /**
     * 返回小时分
     */
    private String getHm(String str) {
        return str.substring(0, str.lastIndexOf(":"));
    }

    /**
     * 获取全日期
     *
     * @param str
     * @return
     */
    private String getAllDay(String str) {
        String newdate = "";
        try {
            newdate = TimeUtil.dealDateFormat(str);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return newdate;
    }

    private String getAllHm(String str) {
        return str.substring(str.indexOf(" "), str.lastIndexOf(":"));
    }

    /**
     * 获取当前的年份
     *
     * @return
     */
    private int getYear() {
        return Integer.valueOf(Calendar.getInstance().get(Calendar.YEAR));
    }

    /**
     * 获取当前的月份
     *
     * @return
     */
    private int getMonth() {
        return Integer.valueOf(Calendar.getInstance().get(Calendar.MONTH) + 1);
    }

    /**
     * 获取当前的日
     */
    private int getDay() {
        return Integer.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
    }

    /**
     * 获取返回的类型
     */
    private String kind(String str) {
        if (str.equals("0"))
            return "未签";
        else if (str.equals("1"))
            return "正常";
        else if (str.equals("2"))
            return "外出";
        else if (str.equals("3"))
            return "休息";
        else if (str.equals("4"))
            return "补签";
        else if (str.equals("5"))
            return "迟到";
        else if (str.equals("6"))
            return "早退";
        else if (str.equals("7"))
            return "旷工";
        else
            return "";
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
    protected void onStop() {
        // TODO Auto-generated method stub
        locationService.unregisterListener(mListener); //注销掉监听
        locationService.stop(); //停止定位服务
        super.onStop();
    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        // -----------location config ------------
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
                logMsg("已在考勤范围:" + location.getCity() + location.getDistrict() + location.getStreet() + location.getStreetNumber());
            }
        }

        public void onConnectHotSpotMessage(String s, int i) {
        }
    };
}
