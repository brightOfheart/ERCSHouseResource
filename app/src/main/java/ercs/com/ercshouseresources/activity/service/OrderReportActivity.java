package ercs.com.ercshouseresources.activity.service;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.adapter.OrderReportPhotoGridAdapter;
import ercs.com.ercshouseresources.bean.ReportOrderDetailBean;
import ercs.com.ercshouseresources.bean.UpLoadPicBean;
import ercs.com.ercshouseresources.network.HttpUtils;
import ercs.com.ercshouseresources.network.MyGson;
import ercs.com.ercshouseresources.network.NetHelper;
import ercs.com.ercshouseresources.network.NetHelperNew;
import ercs.com.ercshouseresources.util.OtherUitl;
import ercs.com.ercshouseresources.util.TitleControl;
import ercs.com.ercshouseresources.util.ToastUtil;
import ercs.com.ercshouseresources.view.MyGridView;
import ercs.com.ercshouseresources.view.dialog.LoadingDialog;

import static ercs.com.ercshouseresources.util.StringUtil.getStr;

/**
 * 报备订单详情
 */
public class OrderReportActivity extends AppCompatActivity {

    @BindView(R.id.iv_seecamera)
    ImageView iv_seecamera;   //带看照相
    @BindView(R.id.iv_record)
    ImageView iv_record;   //备案照相
    @BindView(R.id.iv_deposit)
    ImageView iv_deposit;   //定金照相
    @BindView(R.id.gridview_see)
    MyGridView gridview_see;   //带看图片

    @BindView(R.id.tv_see)
    TextView tv_see;   //带看状态 可带看 已带看
    @BindView(R.id.tv_deposit)
    TextView tv_deposit;   //定金状态
    @BindView(R.id.tv_payments)
    TextView tv_payments;   //首付状态
   @BindView(R.id.tv_record)
    TextView tv_record;   //备案状态

    @BindView(R.id.tv_daikan)
    TextView tv_daikan;//带看文字叙述

    @BindView(R.id.gridview_deposit)
    MyGridView gridview_deposit;   //定金图片
     @BindView(R.id.gridview_record)
    MyGridView gridview_record;   //定金图片

    @BindView(R.id.tv_reportstate)
    TextView tv_reportstate;//报备状态

    @BindView(R.id.ll_reportno)
    LinearLayout ll_reportno;//报备未通过显示理由
    @BindView(R.id.ll_see)
    LinearLayout ll_see;//带看
    @BindView(R.id.ll_deposit)
    LinearLayout ll_deposit;//定金
    @BindView(R.id.ll_payments)
    LinearLayout ll_payments;//首付
    @BindView(R.id.ll_record)
    LinearLayout ll_record;//备案
    @BindView(R.id.ll_finish)
    LinearLayout ll_finish;//完成
    public final static int CAMERA_REQUEST_CODE = 3;
    public static String SAVED_IMAGE_DIR_PATH =
            Environment.getExternalStorageDirectory().getPath()
                    + "/AppName/camera/";// 拍照路径
    private String cameraPath = "";


    private OrderReportPhotoGridAdapter seePhotoGridAdapter;
    private List<String> seeCameraPaths;//带看图片集合

    private OrderReportPhotoGridAdapter depositPhotoGridAdapter;
    private List<String> depositCameraPaths;//定金图片集合

    private OrderReportPhotoGridAdapter recordPhotoGridAdapter;
    private List<String> recordCameraPaths;//备案图片集合


    private LoadingDialog dialog;


    private int cameraType=0;//1 带看 2定金 3备案

    private int  State=2;//1 报备待审 2 报备通过 3 报备驳回 4 已带看 5 已交订金 6 已付首款 7 已备案 8 已完成 9 退房待审 10 已退房

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_report);
        ButterKnife.bind(this);
        initTitle();
        initSeeView();
        initDepositView();
        initrecordView();
        downLoad();
    }

    /**
     * 加载网络数据
     */
    private void downLoad() {
        NetHelperNew.getReportingOrderDetail("", new HttpUtils.HttpCallback() {
            @Override
            public void onSuccess(String data) {

                final ReportOrderDetailBean reportOrderDetailBean = MyGson.getInstance().fromJson(data, ReportOrderDetailBean.class);
//                State=reportOrderDetailBean.getData().getState();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.showToast(OrderReportActivity.this,reportOrderDetailBean.getContent());

                        initLayout();
                    }
                });
            }

            @Override
            public void onError(String msg) {
                super.onError(msg);
                ToastUtil.showToast(OrderReportActivity.this,msg);
            }
        });
    }

    /**
     * 初始化布局
     */
    private void initLayout() {
        //1 报备待审 2 报备通过 3 报备驳回 4 已带看 5 已交订金 6 已付首款 7 已备案 8 已完成 9 退房待审 10 已退房
          switch (State)
          {
              case 1:
                  //报备待审
                  tv_reportstate.setText("等待审核");
                  tv_reportstate.setTextColor(getResources().getColor(R.color.orange));
               break;
              case 2:
                  //报备通过
                  ll_see.setVisibility(View.VISIBLE);
                  tv_reportstate.setText("审核通过");
                  tv_reportstate.setTextColor(getResources().getColor(R.color.system_color));

                  tv_see.setText("可带看");
                  iv_seecamera.setVisibility(View.VISIBLE);//隐藏照相机
                  seePhotoGridAdapter.setCance(true);
               break;
              case 3:
                  //报备驳回
                  tv_reportstate.setText("审核未通过");
                  tv_reportstate.setTextColor(getResources().getColor(R.color.red));
                  ll_reportno.setVisibility(View.VISIBLE);
               break;
              case 4:
                  //已带看
                  ll_see.setVisibility(View.VISIBLE);
                  ll_deposit.setVisibility(View.VISIBLE);

                  iv_deposit.setVisibility(View.VISIBLE);
                  tv_deposit.setText("待交定金");
               break;
              case 5:
                  //已交订金
                  ll_see.setVisibility(View.VISIBLE);
                  ll_deposit.setVisibility(View.VISIBLE);
                  ll_payments.setVisibility(View.VISIBLE);
                  tv_payments.setText("待交首付");
               break;
              case 6:
                  //已付首款
                  ll_see.setVisibility(View.VISIBLE);
                  ll_deposit.setVisibility(View.VISIBLE);
                  ll_payments.setVisibility(View.VISIBLE);
                  ll_record.setVisibility(View.VISIBLE);
                  tv_record.setText("待备案");
                  iv_record.setVisibility(View.VISIBLE);
                  recordPhotoGridAdapter.setCance(true);
               break;
              case 7:
                  //已备案
                  ll_see.setVisibility(View.VISIBLE);
                  ll_deposit.setVisibility(View.VISIBLE);
                  ll_payments.setVisibility(View.VISIBLE);
                  ll_record.setVisibility(View.VISIBLE);
                  ll_finish.setVisibility(View.VISIBLE);
               break;
              case 8:

               break;
          }
    }

    /**
     * 带看控件初始化
     */
    private void initSeeView() {

        //文字叙述加橘色
        Spannable span = new SpannableString(tv_daikan.getText().toString());//获取文字
        span.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.orange)), 10, 15, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);//显示为橘色
        tv_daikan.setText(span);
        seeCameraPaths=new ArrayList<>();
        seePhotoGridAdapter = new OrderReportPhotoGridAdapter(this, seeCameraPaths, new OrderReportPhotoGridAdapter.OnCancelPhotoListener() {
            @Override
            public void cancelPhoto(int i) {
                seeCameraPaths.remove(i);
                seePhotoGridAdapter.notifyDataSetChanged();
            }
        });
        gridview_see.setAdapter(seePhotoGridAdapter);
    }
 /**
     * 定金控件初始化
     */
    private void initDepositView() {
        depositCameraPaths=new ArrayList<>();
        depositPhotoGridAdapter = new OrderReportPhotoGridAdapter(this, depositCameraPaths, new OrderReportPhotoGridAdapter.OnCancelPhotoListener() {
            @Override
            public void cancelPhoto(int i) {
                depositCameraPaths.remove(i);
                depositPhotoGridAdapter.notifyDataSetChanged();
            }
        });
        gridview_deposit.setAdapter(depositPhotoGridAdapter);
    }
 /**
     * 备案控件初始化
     */
    private void initrecordView() {
        recordCameraPaths=new ArrayList<>();
        recordPhotoGridAdapter = new OrderReportPhotoGridAdapter(this, recordCameraPaths, new OrderReportPhotoGridAdapter.OnCancelPhotoListener() {
            @Override
            public void cancelPhoto(int i) {
                recordCameraPaths.remove(i);
                recordPhotoGridAdapter.notifyDataSetChanged();
            }
        });
        gridview_record.setAdapter(recordPhotoGridAdapter);
    }

    /**\
     * 初始化标题
     */
    private void initTitle() {
        TitleControl titleControl = new TitleControl(this);
        titleControl.setTitle("华鸿金色柏林");
        dialog=new LoadingDialog(this,0);
    }

    @OnClick({R.id.iv_seecamera,R.id.iv_deposit,R.id.iv_record})
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.iv_seecamera:
                cameraType=1;
                //点击带看照相
                if (seeCameraPaths.size()==6)
                {
                    ToastUtil.showToast(OrderReportActivity.this,"最多传六张图片");
                }else
                {
                    savePath();
                }

                break;
            case R.id.iv_deposit:
                cameraType=2;
                //点击定金照相
                if (depositCameraPaths.size()==6)
                {
                    ToastUtil.showToast(OrderReportActivity.this,"最多传六张图片");
                }else
                {
                    savePath();
                }

                break;
            case R.id.iv_record:
                cameraType=3;
                //点击备案照相
                if (recordCameraPaths.size()==6)
                {
                    ToastUtil.showToast(OrderReportActivity.this,"最多传六张图片");
                }else
                {
                    savePath();
                }

                break;
        }
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
                    String str = OtherUitl.imageToBase64(cameraPath);
                    upLoadPic(str);
                } else {
                    ToastUtil.showToast(this, getStr(R.string.str_chosepic));
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

                    String id = upLoadPicBean.getData().getId();
                    Log.i("-->","上传图片成功："+id);
                    //上传图片成功就刷新图片样式
                    if (cameraType==1)
                    {
                        //带看
                        seeCameraPaths.add(cameraPath);
                        seePhotoGridAdapter.notifyDataSetChanged();
                    }
                     else if (cameraType==2)
                    {
                        //定金
                        depositCameraPaths.add(cameraPath);
                        depositPhotoGridAdapter.notifyDataSetChanged();
                    }else if (cameraType==3)
                    {
                        //备案
                        recordCameraPaths.add(cameraPath);
                        recordPhotoGridAdapter.notifyDataSetChanged();
                    }

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
}
