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
    @BindView(R.id.gridview_see)
    MyGridView gridview_see;   //带看图片

    @BindView(R.id.tv_daikan)
    TextView tv_daikan;//带看文字叙述
    @BindView(R.id.iv_deposit)
    ImageView iv_deposit;   //定金照相
    @BindView(R.id.gridview_deposit)
    MyGridView gridview_deposit;   //定金图片

    public final static int CAMERA_REQUEST_CODE = 3;
    public static String SAVED_IMAGE_DIR_PATH =
            Environment.getExternalStorageDirectory().getPath()
                    + "/AppName/camera/";// 拍照路径
    private String cameraPath = "";


    private OrderReportPhotoGridAdapter seePhotoGridAdapter;
    private List<String> seeCameraPaths;//带看图片集合

    private OrderReportPhotoGridAdapter depositPhotoGridAdapter;
    private List<String> depositCameraPaths;//定金图片集合
    private LoadingDialog dialog;


    private int cameraType=0;//1 带看 2定金 3备案
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_report);
        ButterKnife.bind(this);
        initTitle();
        initSeeView();
        initDepositView();
        downLoad();
    }

    /**
     * 加载网络数据
     */
    private void downLoad() {
        NetHelperNew.getReportingOrderDetail("", new HttpUtils.HttpCallback() {
            @Override
            public void onSuccess(String data) {

            }

            @Override
            public void onError(String msg) {
                super.onError(msg);
                ToastUtil.showToast(OrderReportActivity.this,msg);
            }
        });
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

    /**\
     * 初始化标题
     */
    private void initTitle() {
        TitleControl titleControl = new TitleControl(this);
        titleControl.setTitle("华鸿金色柏林");
        dialog=new LoadingDialog(this,0);
    }

    @OnClick({R.id.iv_seecamera,R.id.iv_deposit})
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
