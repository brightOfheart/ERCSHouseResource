package ercs.com.ercshouseresources.activity.renovation;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.activity.BaseActivity;
import ercs.com.ercshouseresources.activity.FollowMsgActivity;
import ercs.com.ercshouseresources.activity.cheaproom.Cheap_OrderReportDetailActivity;
import ercs.com.ercshouseresources.activity.service.OrderReportDetailActivity;
import ercs.com.ercshouseresources.adapter.OrderReportDetailAdapter;
import ercs.com.ercshouseresources.adapter.Ren_OrderReportDetailAdapter;
import ercs.com.ercshouseresources.bean.BaseBean;
import ercs.com.ercshouseresources.bean.NewHouseUpLoadPicBean;
import ercs.com.ercshouseresources.bean.RenReportOrderDetailBean;
import ercs.com.ercshouseresources.bean.ReportOrderDetailBean;
import ercs.com.ercshouseresources.network.HttpUtils;
import ercs.com.ercshouseresources.network.MyGson;
import ercs.com.ercshouseresources.network.NetHelperNew;
import ercs.com.ercshouseresources.util.CloseActivityClass;
import ercs.com.ercshouseresources.util.DisplayUtil;
import ercs.com.ercshouseresources.util.OtherUitl;
import ercs.com.ercshouseresources.util.TitleControl;
import ercs.com.ercshouseresources.util.ToastUtil;
import ercs.com.ercshouseresources.view.NoScrollListView;
import ercs.com.ercshouseresources.view.dialog.ChosePicDialog;
import ercs.com.ercshouseresources.view.dialog.LoadingDialog;

import static ercs.com.ercshouseresources.util.StringUtil.getStr;

/**
 * Created by Administrator on 2017/8/23.
 * 装修订单详情
 */

public class Ren_OrderReportDetailActivity extends BaseActivity {


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
    @BindView(R.id.tv_name)
    TextView tv_name;//客户姓名
    @BindView(R.id.tv_tel)
    TextView tv_tel;//客户电话
    @BindView(R.id.tv_areao)
    TextView tv_areao;
    @BindView(R.id.tv_adress)
    TextView tv_adress;
    @BindView(R.id.tv_bzhu)
    TextView tv_bzhu;
    @BindView(R.id.listview)
    NoScrollListView listview;//状态列表

    private Ren_OrderReportDetailAdapter orderReportDetailAdapter;

    private List<RenReportOrderDetailBean.DataBean.DecorationRunningsInfoShowList> listBeen;
    private LoadingDialog dialog;

    private TitleControl titleControl;

    private int imageType;
    private int pos;//选中列表位置
    public static String SAVED_IMAGE_DIR_PATH =
            Environment.getExternalStorageDirectory().getPath()
                    + "/AppName/camera/";// 拍照路径
    private String cameraPath = "";
    public final static int CAMERA_REQUEST_CODE = 3;
    private String Groupid;
    private RenReportOrderDetailBean reportOrderDetailBean;
    private String Json = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ren_order_report_detail);
        ButterKnife.bind(this);
        initTitle();
        initListview();
        downLoad();
        if (!CloseActivityClass.activityList.contains(this)) {
            CloseActivityClass.activityList.add(this);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    private void chosedialog() {
        ChosePicDialog chosePicDialog = new ChosePicDialog(Ren_OrderReportDetailActivity.this, new ChosePicDialog.OnClickLister() {
            @Override
            public void camera() {
                savePath();
            }

            @Override
            public void pic() {
                //调用相册
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 10);
            }
        });
        chosePicDialog.show();
    }

    /**
     * 初始化状态列表
     */
    private void initListview() {
        listBeen = new ArrayList<>();
        orderReportDetailAdapter = new Ren_OrderReportDetailAdapter(listBeen, this, new OrderReportDetailAdapter.OnCamreaListener() {

            @Override
            public void getImageType(int poss, int ImageType, String GroupID) {
                //进行拍照
                imageType = ImageType;
                pos = poss;
                Groupid = GroupID;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)//判断当前的版本是否大于6.0
                {
                    if (ContextCompat.checkSelfPermission(Ren_OrderReportDetailActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(Ren_OrderReportDetailActivity.this, new String[]{Manifest.permission.CAMERA}, 66);

                    } else {
                        //有权限，直接拍照
                        chosedialog();
                    }
                } else {
                    chosedialog();
                }
            }


            @Override
            public void delPic(int pos, int id, int gridPos) {
                //删除图片
                delPics(pos, id, gridPos);
            }
        });
        listview.setAdapter(orderReportDetailAdapter);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 11) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //申请成功，可以拍照
                chosedialog();
            } else {
                Toast.makeText(this, "请开启拍照权限", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //申请成功，可以拍照
            } else {
                finish();
                Toast.makeText(this, "请开启SD卡读写权限", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    /**
     * 初始化标题
     */
    private void initTitle() {
        titleControl = new TitleControl(this);
        titleControl.setTitle(getIntent().getStringExtra("title"));
        dialog = new LoadingDialog(this, 0);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)//判断当前的版本是否大于6.0
        {
            OtherUitl.verifyStoragePermissions(Ren_OrderReportDetailActivity.this);//开启安卓6.0以上系统开启sd卡写入的权限
        }
    }

    /**
     * 加载网络数据
     */
    private void downLoad() {
        dialog.show();
        NetHelperNew.getRenOrderDetail(getIntent().getStringExtra("Id"), new HttpUtils.HttpCallback() {
            @Override
            public void onSuccess(final String data) {
                dialog.dismiss();
                reportOrderDetailBean = MyGson.getInstance().fromJson(data, RenReportOrderDetailBean.class);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        if (reportOrderDetailBean.getType() == 1) {
                            Json = data;
                            // titleControl.setTitle(reportOrderDetailBean.getData().getBuildingName());
                            tv_areao.setText(reportOrderDetailBean.getData().getArea());
                            tv_adress.setText(reportOrderDetailBean.getData().getCustomerAddress());
                            tv_bzhu.setText(reportOrderDetailBean.getData().getRemark());
                            //更新列表
                            listBeen.addAll(reportOrderDetailBean.getData().getDecorationRunningsInfoShowList());
                            orderReportDetailAdapter.notifyDataSetChanged();
                            initCommission(reportOrderDetailBean);
                        } else {
                            ToastUtil.showToast(Ren_OrderReportDetailActivity.this, reportOrderDetailBean.getContent());
                        }

                    }
                });
            }

            @Override
            public void onError(String msg) {
                super.onError(msg);
                dialog.dismiss();
                ToastUtil.showToast(Ren_OrderReportDetailActivity.this, msg);
            }
        });
    }

    /**
     * 佣金 客户填数据
     *
     * @param reportOrderDetailBean
     */
    private void initCommission(RenReportOrderDetailBean reportOrderDetailBean) {

        tv_name.setText(reportOrderDetailBean.getData().getUserName());
        tv_tel.setText(reportOrderDetailBean.getData().getUserPhone());
        tv_totalamount.setText(reportOrderDetailBean.getData().getTotalAmount());
        tv_achieveamount.setText(reportOrderDetailBean.getData().getAchieveAmount());
        tv_taxamount.setText(reportOrderDetailBean.getData().getTaxAmount());
        tv_unachieveamount.setText(reportOrderDetailBean.getData().getUnAchieveAmount());

        switch (reportOrderDetailBean.getData().getBrokerageState()) {
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

    @OnClick({R.id.ll_customerphone, R.id.frame_follow})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_customerphone:
                //拨打客户电话
                OtherUitl.callPage(Ren_OrderReportDetailActivity.this, tv_tel.getText().toString());
                break;
            case R.id.frame_follow:
                //跟进信息
                if (!Json.equals(""))
                    FollowMsgActivity.start(Ren_OrderReportDetailActivity.this, Json, "4");
                else
                    ToastUtil.showToast(Ren_OrderReportDetailActivity.this, reportOrderDetailBean.getContent());
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
                    Bitmap bitmap = OtherUitl.getBitmapFromUrl(cameraPath, DisplayUtil.getWidthPixels(this), DisplayUtil.getHeightPixels(this));
                    if (OtherUitl.getBitmapDegree(cameraPath) != 0) {
                        Bitmap bitmaps = OtherUitl.rotateBitmapByDegree(bitmap, OtherUitl.getBitmapDegree(cameraPath));
                        String str = OtherUitl.BitmapToString(bitmaps);
                        upLoadPic(str);
                    } else {
                        String str = OtherUitl.BitmapToString(bitmap);
                        upLoadPic(str);
                    }
                } else {
                    ToastUtil.showToast(this, getStr(R.string.str_chosepic));
                }

            }
        }
    }

    /**
     * 删除图片
     *
     * @param pos 选择的第几个
     * @param id  对应id
     */
    private void delPics(final int pos, int id, final int gridPos) {
        dialog.show();
        NetHelperNew.DelImage(id + "", new HttpUtils.HttpCallback() {
            @Override
            public void onSuccess(String data) {
                dialog.dismiss();
                final BaseBean baseBean = MyGson.getInstance().fromJson(data, BaseBean.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (baseBean.getType().equals("1")) {
                            listBeen.get(pos).getImageList().remove(gridPos);
                            orderReportDetailAdapter.notifyDataSetChanged();
                        } else {
                            ToastUtil.showToast(Ren_OrderReportDetailActivity.this, baseBean.getContent());
                        }

                    }
                });
            }

            @Override
            public void onError(String msg) {
                super.onError(msg);
                dialog.dismiss();
                ToastUtil.showToast(Ren_OrderReportDetailActivity.this, msg);
            }
        });
    }

    /**
     * 上传图片
     */
    private void upLoadPic(String path) {
        dialog.show();
        NetHelperNew.uploadImage(Groupid, getIntent().getStringExtra("Id"), "" + imageType, path, new HttpUtils.HttpCallback() {
            @Override
            public void onSuccess(String data) {
                dialog.dismiss();
                final NewHouseUpLoadPicBean upLoadPicBean = MyGson.getInstance().fromJson(data, NewHouseUpLoadPicBean.class);
                if (upLoadPicBean.getType() == 1) {

                    String id = upLoadPicBean.getData().getId() + "";
                    String path = upLoadPicBean.getData().getImagePath() + upLoadPicBean.getData().getFileName();
                    Log.i("-->", "上传图片成功：" + id);
                    //上传图片成功就刷新图片样式
                    listBeen.get(pos).getImageList().add(new RenReportOrderDetailBean.DataBean.DecorationRunningsInfoShowList.ImageListBean(upLoadPicBean.getData().getImagePath(), upLoadPicBean.getData().getFileName(), upLoadPicBean.getData().getId()));
                    orderReportDetailAdapter.notifyDataSetChanged();

                } else {
                    ToastUtil.showToast(getApplicationContext(), upLoadPicBean.getContent());
                }
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
