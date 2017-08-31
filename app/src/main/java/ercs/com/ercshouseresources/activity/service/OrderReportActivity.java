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
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.adapter.OrderReportPhotoGridAdapter;
import ercs.com.ercshouseresources.bean.BaseBean;
import ercs.com.ercshouseresources.bean.NewHousePicBean;
import ercs.com.ercshouseresources.bean.NewHouseUpLoadPicBean;
import ercs.com.ercshouseresources.bean.ReportOrderDetailBean;
import ercs.com.ercshouseresources.bean.UpLoadPicBean;
import ercs.com.ercshouseresources.network.HttpUtils;
import ercs.com.ercshouseresources.network.MyGson;
import ercs.com.ercshouseresources.network.NetHelper;
import ercs.com.ercshouseresources.network.NetHelperNew;
import ercs.com.ercshouseresources.util.CloseActivityClass;
import ercs.com.ercshouseresources.util.OtherUitl;
import ercs.com.ercshouseresources.util.TimeUtil;
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
    @BindView(R.id.tv_no_reportreason)
    TextView tv_no_reportreason;//报备未通过原因

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


    @BindView(R.id.iv_report)
    ImageView iv_report;//报备状态图标
    @BindView(R.id.iv_see)
    ImageView iv_see;//带看状态图标
    @BindView(R.id.iv_depositstate)
    ImageView iv_depositstate;//定金状态图标
     @BindView(R.id.iv_payments)
    ImageView iv_payments;//首付状态图标
    @BindView(R.id.iv_recordstate)
    ImageView iv_recordstate;//备案状态图标
    @BindView(R.id.iv_finish)
    ImageView iv_finish;//完成状态图标

    @BindView(R.id.tv_report_time)
    TextView tv_report_time;//报备时间
    @BindView(R.id.tv_see_time)
    TextView tv_see_time;//带看时间
    @BindView(R.id.tv_deposit_tv)
    TextView tv_deposit_tv;//认购时间
    @BindView(R.id.tv_payments_time)
    TextView tv_payments_time;//首付时间
    @BindView(R.id.tv_record_time)
    TextView tv_record_time;//备案时间

    @BindView(R.id.tv_name)
    TextView tv_name;//客户姓名
    @BindView(R.id.tv_tel)
    TextView tv_tel;//客户电话

    @BindView(R.id.tv_payments_money)
    TextView tv_payments_money;//首付金额
    @BindView(R.id.iv_deposit_money)
    TextView iv_deposit_money;//定金金额

    //佣金
    @BindView(R.id.tv_totalamount)
    TextView tv_totalamount; //应得佣金
    @BindView(R.id.tv_achieveamount)
    TextView tv_achieveamount;  //已结
    @BindView(R.id.tv_taxamount)
    TextView tv_taxamount;   //税款
    @BindView(R.id.tv_unachieveamount)
    TextView tv_unachieveamount;//未结
    @BindView(R.id.tv_commissionstate)
    TextView tv_commissionstate;//佣金状态


    public final static int CAMERA_REQUEST_CODE = 3;
    public static String SAVED_IMAGE_DIR_PATH =
            Environment.getExternalStorageDirectory().getPath()
                    + "/AppName/camera/";// 拍照路径
    private String cameraPath = "";


    private OrderReportPhotoGridAdapter seePhotoGridAdapter;
    private List<NewHousePicBean> seeCameraPaths;//带看图片集合

    private OrderReportPhotoGridAdapter depositPhotoGridAdapter;
    private List<NewHousePicBean> depositCameraPaths;//定金图片集合

    private OrderReportPhotoGridAdapter recordPhotoGridAdapter;
    private List<NewHousePicBean> recordCameraPaths;//备案图片集合


    private LoadingDialog dialog;

    private TitleControl titleControl;

    private int cameraType=0;//1 带看 2定金 3备案



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
        if(!CloseActivityClass.activityList.contains(this))
        {
            CloseActivityClass.activityList.add(this);
        }
    }

    /**
     * 加载网络数据
     */
    private void downLoad() {
        NetHelperNew.getReportingOrderDetail(getIntent().getStringExtra("Id"), new HttpUtils.HttpCallback() {
            @Override
            public void onSuccess(String data) {

                final ReportOrderDetailBean reportOrderDetailBean = MyGson.getInstance().fromJson(data, ReportOrderDetailBean.class);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.showToast(OrderReportActivity.this,reportOrderDetailBean.getContent());
                        if (reportOrderDetailBean.getType()==1)
                        {
//                            titleControl.setTitle(reportOrderDetailBean.getData().getBaseInfo().getBuildingName());
                            initLayout(reportOrderDetailBean);

                            initCommission(reportOrderDetailBean);
                        }

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
     * 佣金填数据
     * @param reportOrderDetailBean
     */
    private void initCommission(ReportOrderDetailBean reportOrderDetailBean) {
         tv_totalamount.setText(reportOrderDetailBean.getData().getTotalAmount()+"元");
        tv_achieveamount.setText(reportOrderDetailBean.getData().getAchieveAmount()+"元");
        tv_taxamount.setText(reportOrderDetailBean.getData().getTaxAmount()+"元");
        tv_unachieveamount.setText(reportOrderDetailBean.getData().getUnAchieveAmount()+"元");

        switch (reportOrderDetailBean.getData().getBrokerageState())
        {
            case 1:
                tv_commissionstate.setText("通过");
                tv_commissionstate.setBackgroundResource(R.drawable.tv_green_bg);
                tv_commissionstate.setTextColor(getResources().getColor(R.color.system_color));
                break;
            case 2:
                tv_commissionstate.setText("审核中");
                tv_commissionstate.setBackgroundResource(R.drawable.tv_powder_bg);
                tv_commissionstate.setTextColor(getResources().getColor(R.color.red));
                break;
        }

    }

    /**
     * 初始化布局
     */
    private void initLayout(ReportOrderDetailBean reportOrderDetailBean) {
//        ReportOrderDetailBean.DataBean.BaseInfoBean data = reportOrderDetailBean.getData().getBaseInfo();
        //1 报备待审 2 报备通过 3 报备驳回 4 已带看 5 已交订金 6 已付首款 7 已备案 8 已完成 9 退房待审 10 已退房
//        int state = data.getState();
//        int state = 2;
//        Log.i("-->","报备状态："+state);
//        switch (state)
//          {
//              case 1:
//                  //报备待审
//                  tv_reportstate.setText("等待审核");
//                  tv_reportstate.setTextColor(getResources().getColor(R.color.orange));
//                  //状态图标
//                  iv_report.setImageResource(R.mipmap.dengdai);
//
//
//                  //数据填充
//
//               break;
//              case 2:
//                  //报备通过
//
//                  cameraType=1;
//                  ll_see.setVisibility(View.VISIBLE);
//                  tv_reportstate.setText("审核通过");
//                  tv_reportstate.setTextColor(getResources().getColor(R.color.system_color));
//
//                  tv_see.setText("可带看");
//                  iv_seecamera.setVisibility(View.VISIBLE);//显示照相机
//
//                  //删除图标显示 加载本地图片
//                  seePhotoGridAdapter.setCance(true);
//
//                  //带看加载网络图片
//                  downLoadPic(1,reportOrderDetailBean,seeCameraPaths,seePhotoGridAdapter);
//                  //状态图标
//                  iv_report.setImageResource(R.mipmap.tongguo);
//                  iv_see.setImageResource(R.mipmap.dengdai);
//               break;
//              case 3:
//                  //报备驳回
//                  tv_reportstate.setText("审核未通过");
//                  tv_reportstate.setTextColor(getResources().getColor(R.color.red));
//                  ll_reportno.setVisibility(View.VISIBLE);
//
//
//                  //状态图标
//                  iv_report.setImageResource(R.mipmap.jujue);
//
//                  //报备审核说明
//                  tv_no_reportreason.setText(data.getFilingAuditingRemark());
//               break;
//              case 4:
//                  //已带看
//                  cameraType=2;
//                  ll_see.setVisibility(View.VISIBLE);
//                  ll_deposit.setVisibility(View.VISIBLE);
//
//                  iv_deposit.setVisibility(View.VISIBLE);
//                  tv_deposit.setText("待交定金");
//                  iv_deposit_money.setVisibility(View.GONE);
//                  //状态图标
//                  iv_report.setImageResource(R.mipmap.tongguo);
//                  iv_see.setImageResource(R.mipmap.tongguo);
//                  iv_depositstate.setImageResource(R.mipmap.dengdai);
//                  depositPhotoGridAdapter.setCance(true);
//                  //带看加载网络图片
//                  downLoadPic(1,reportOrderDetailBean,seeCameraPaths,seePhotoGridAdapter);
//                  //定金加载网络图片
//                  downLoadPic(2,reportOrderDetailBean,depositCameraPaths,depositPhotoGridAdapter);
//
//
//
//               break;
//              case 5:
//                  //已交订金
//                  ll_see.setVisibility(View.VISIBLE);
//                  ll_deposit.setVisibility(View.VISIBLE);
//                  ll_payments.setVisibility(View.VISIBLE);
//                  tv_payments.setText("待交首付");
//
//                  tv_payments_money.setVisibility(View.GONE);
//                  //状态图标
//                  iv_report.setImageResource(R.mipmap.tongguo);
//                  iv_see.setImageResource(R.mipmap.tongguo);
//                  iv_depositstate.setImageResource(R.mipmap.tongguo);
//                  iv_payments.setImageResource(R.mipmap.dengdai);
//                  //带看加载网络图片
//                  downLoadPic(1,reportOrderDetailBean,seeCameraPaths,seePhotoGridAdapter);
//                  //定金加载网络图片
//                  downLoadPic(2,reportOrderDetailBean,depositCameraPaths,depositPhotoGridAdapter);
//               break;
//              case 6:
//                  //已付首款
//                  cameraType=3;
//                  ll_see.setVisibility(View.VISIBLE);
//                  ll_deposit.setVisibility(View.VISIBLE);
//                  ll_payments.setVisibility(View.VISIBLE);
//                  ll_record.setVisibility(View.VISIBLE);
//                  tv_record.setText("待备案");
//                  iv_record.setVisibility(View.VISIBLE);
//
//                  //删除图标显示 加载本地图片
//                  recordPhotoGridAdapter.setCance(true);
//
//                  //状态图标
//                  iv_report.setImageResource(R.mipmap.tongguo);
//                  iv_see.setImageResource(R.mipmap.tongguo);
//                  iv_depositstate.setImageResource(R.mipmap.tongguo);
//                  iv_payments.setImageResource(R.mipmap.tongguo);
//                  iv_recordstate.setImageResource(R.mipmap.dengdai);
//                  //带看加载网络图片
//                  downLoadPic(1,reportOrderDetailBean,seeCameraPaths,seePhotoGridAdapter);
//                  //定金加载网络图片
//                  downLoadPic(2,reportOrderDetailBean,depositCameraPaths,depositPhotoGridAdapter);
//                  //备案加载网络图片
//                  downLoadPic(3,reportOrderDetailBean,recordCameraPaths,recordPhotoGridAdapter);
//               break;
//              case 8:
//              case 7:
//                  //已备案
//
//                  ll_see.setVisibility(View.VISIBLE);
//                  ll_deposit.setVisibility(View.VISIBLE);
//                  ll_payments.setVisibility(View.VISIBLE);
//                  ll_record.setVisibility(View.VISIBLE);
//                  ll_finish.setVisibility(View.VISIBLE);
//
//                  //状态图标
//                  iv_report.setImageResource(R.mipmap.tongguo);
//                  iv_see.setImageResource(R.mipmap.tongguo);
//                  iv_depositstate.setImageResource(R.mipmap.tongguo);
//                  iv_payments.setImageResource(R.mipmap.tongguo);
//                  iv_recordstate.setImageResource(R.mipmap.tongguo);
//                  iv_finish.setImageResource(R.mipmap.tongguo);
//                  //带看加载网络图片
//                  downLoadPic(1,reportOrderDetailBean,seeCameraPaths,seePhotoGridAdapter);
//                  //定金加载网络图片
//                  downLoadPic(2,reportOrderDetailBean,depositCameraPaths,depositPhotoGridAdapter);
//                  //备案加载网络图片
//                  downLoadPic(3,reportOrderDetailBean,recordCameraPaths,recordPhotoGridAdapter);
//               break;
//
//          }
//
//
//        tv_name.setText(data.getCustomerName());
//        tv_tel.setText(data.getCustomerPhone());
//
//        tv_payments_money.setText(data.getDownPaymentMoney()+"");
//        iv_deposit_money.setText(data.getBargainMoney()+"");
//        //时间
//            try {
//                if (data.getFilingTime()!=null)
//                tv_report_time.setText(TimeUtil.dealDateFormatNomm(data.getFilingTime()));
//                if (data.getBandSawTime()!=null)
//                    tv_see_time.setText(TimeUtil.dealDateFormatNomm(data.getBandSawTime()));
//                if (data.getSubscribeTime()!=null)
//                    tv_deposit_tv.setText(TimeUtil.dealDateFormatNomm(data.getSubscribeTime()));
//                if (data.getDownPaymentTime()!=null)
//                    tv_payments_time.setText(TimeUtil.dealDateFormatNomm(data.getDownPaymentTime()));
//                if (data.getPutOnRecordTime()!=null)
//                    tv_record_time.setText(TimeUtil.dealDateFormatNomm(data.getPutOnRecordTime()));
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
    }

    /**
     * 加载网络图片
     * @param n 1带看 2 定金 3 备案
     * @param reportOrderDetailBean
     * @param CameraPaths
     * @param PhotoGridAdapter
     */
//    private void downLoadPic(int n,ReportOrderDetailBean reportOrderDetailBean, List<NewHousePicBean> CameraPaths, OrderReportPhotoGridAdapter PhotoGridAdapter) {
//        if (n==1)
//        {
//            for (int i = 0; i < reportOrderDetailBean.getData().getBandSawImageList().size(); i++) {
//                if (i<6)
//                CameraPaths.add(new NewHousePicBean(reportOrderDetailBean.getData().getBandSawImageList().get(i).getId()+"",reportOrderDetailBean.getData().getBandSawImageList().get(i).getImagePath()+reportOrderDetailBean.getData().getBandSawImageList().get(i).getFileName()));
//            }
//        }else if (n==2)
//        {
//            for (int i = 0; i < reportOrderDetailBean.getData().getDownPaymentImageList().size(); i++) {
//                if (i<6)
//                CameraPaths.add(new NewHousePicBean(reportOrderDetailBean.getData().getDownPaymentImageList().get(i).getId()+"",reportOrderDetailBean.getData().getDownPaymentImageList().get(i).getImagePath()+reportOrderDetailBean.getData().getDownPaymentImageList().get(i).getFileName()));
//            }
//        }else if (n==3)
//        {
//            for (int i = 0; i < reportOrderDetailBean.getData().getPutOnRecordImageList().size(); i++) {
//                if (i<6)
//                CameraPaths.add(new NewHousePicBean(reportOrderDetailBean.getData().getPutOnRecordImageList().get(i).getId()+"",reportOrderDetailBean.getData().getPutOnRecordImageList().get(i).getImagePath()+reportOrderDetailBean.getData().getPutOnRecordImageList().get(i).getFileName()));
//            }
//        }
//
//        PhotoGridAdapter.notifyDataSetChanged();
//    }

    /**
     * 带看控件初始化
     */
    private void initSeeView() {

        //文字叙述加橘色
        Spannable span = new SpannableString(tv_daikan.getText().toString());//获取文字
        span.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.orange)), 10, 15, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);//显示为橘色
        tv_daikan.setText(span);
        seeCameraPaths=new ArrayList<>();
//        seePhotoGridAdapter = new OrderReportPhotoGridAdapter(this, seeCameraPaths, new OrderReportPhotoGridAdapter.OnCancelPhotoListener() {
//            @Override
//            public void cancelPhoto(int i) {
//
//                delPic(i,seeCameraPaths.get(i).getId());
//            }
//        });
        gridview_see.setAdapter(seePhotoGridAdapter);
    }
 /**
     * 定金控件初始化
     */
    private void initDepositView() {
        depositCameraPaths=new ArrayList<>();
//        depositPhotoGridAdapter = new OrderReportPhotoGridAdapter(this, depositCameraPaths, new OrderReportPhotoGridAdapter.OnCancelPhotoListener() {
//            @Override
//            public void cancelPhoto(int i) {
//                delPic(i,depositCameraPaths.get(i).getId());
//
//            }
//        });
        gridview_deposit.setAdapter(depositPhotoGridAdapter);
    }
 /**
     * 备案控件初始化
     */
    private void initrecordView() {
        recordCameraPaths=new ArrayList<>();
//        recordPhotoGridAdapter = new OrderReportPhotoGridAdapter(this, recordCameraPaths, new OrderReportPhotoGridAdapter.OnCancelPhotoListener() {
//            @Override
//            public void cancelPhoto(int i) {
//                delPic(i,recordCameraPaths.get(i).getId());
//
//            }
//        });
        gridview_record.setAdapter(recordPhotoGridAdapter);
    }

    /**\
     * 初始化标题
     */
    private void initTitle() {
         titleControl = new TitleControl(this);

        dialog=new LoadingDialog(this,0);
    }

    @OnClick({R.id.iv_seecamera,R.id.iv_deposit,R.id.iv_record,R.id.ll_customerphone})
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
            case R.id.ll_customerphone:
                //拨打客户电话
                OtherUitl.callPage(OrderReportActivity.this, tv_tel.getText().toString());
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
     * 删除图片
     * @param pos 选择的第几个
     * @param id 对应id
     */
    private void delPic(final int pos, String id)
    {
        dialog.show();
        NetHelperNew.DelImage(id, new HttpUtils.HttpCallback() {
            @Override
            public void onSuccess(String data) {
                dialog.dismiss();
                final BaseBean baseBean = MyGson.getInstance().fromJson(data, BaseBean.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (baseBean.getType().equals("1"))
                        {
                            if (cameraType==1)
                            {
                                seeCameraPaths.remove(pos);
                                seePhotoGridAdapter.notifyDataSetChanged();
                            }else if (cameraType==2)
                            {
                                depositCameraPaths.remove(pos);
                                depositPhotoGridAdapter.notifyDataSetChanged();
                            }else if (cameraType==3)
                            {
                                recordCameraPaths.remove(pos);
                                recordPhotoGridAdapter.notifyDataSetChanged();
                            }
                        }
                        ToastUtil.showToast(OrderReportActivity.this,baseBean.getContent());
                    }
                });
            }

            @Override
            public void onError(String msg) {
                super.onError(msg);
                dialog.dismiss();
                ToastUtil.showToast(OrderReportActivity.this,msg);
            }
        });
    }
    /**
     * 上传图片
     */
    private void upLoadPic(String path) {
        dialog.show();
        NetHelperNew.uploadImage("3","2",""+cameraType,path, new HttpUtils.HttpCallback() {
            @Override
            public void onSuccess(String data) {
                dialog.dismiss();
                final NewHouseUpLoadPicBean upLoadPicBean = MyGson.getInstance().fromJson(data, NewHouseUpLoadPicBean.class);
//                if (upLoadPicBean.getType()==1) {
////
////                    String id = upLoadPicBean.getData().getId()+"";
////                    String path=upLoadPicBean.getData().getImagePath()+upLoadPicBean.getData().getFileName();
//                    Log.i("-->","上传图片成功："+id);
//                    //上传图片成功就刷新图片样式
//                    if (cameraType==1)
//                    {
//                        //带看
//                        seeCameraPaths.add(new NewHousePicBean(id,path));
//                        seePhotoGridAdapter.notifyDataSetChanged();
//                    }
//                     else if (cameraType==2)
//                    {
//                        //定金
//                        depositCameraPaths.add(new NewHousePicBean(id,path));
//                        depositPhotoGridAdapter.notifyDataSetChanged();
//                    }else if (cameraType==3)
//                    {
//                        //备案
//                        recordCameraPaths.add(new NewHousePicBean(id,path));
//                        recordPhotoGridAdapter.notifyDataSetChanged();
//                    }
//
//                }
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
