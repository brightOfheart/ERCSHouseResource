package ercs.com.ercshouseresources.activity.service;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.adapter.OrderReportDetailAdapter;
import ercs.com.ercshouseresources.bean.BaseBean;
import ercs.com.ercshouseresources.bean.NewHousePicBean;
import ercs.com.ercshouseresources.bean.NewHouseUpLoadPicBean;
import ercs.com.ercshouseresources.bean.ReportOrderDetailBean;
import ercs.com.ercshouseresources.network.HttpUtils;
import ercs.com.ercshouseresources.network.MyGson;
import ercs.com.ercshouseresources.network.NetHelperNew;
import ercs.com.ercshouseresources.util.OtherUitl;
import ercs.com.ercshouseresources.util.TitleControl;
import ercs.com.ercshouseresources.util.ToastUtil;
import ercs.com.ercshouseresources.view.NoScrollListView;
import ercs.com.ercshouseresources.view.dialog.LoadingDialog;

import static ercs.com.ercshouseresources.util.StringUtil.getStr;

/**
 * 报备订单详情
 */
public class OrderReportDetailActivity extends AppCompatActivity {


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
    @BindView(R.id.listview)
    NoScrollListView listview;//状态列表

    private OrderReportDetailAdapter orderReportDetailAdapter;

    private List<ReportOrderDetailBean.DataBean.NewHouseRunningsInfoShowListBean> listBeen;
    private LoadingDialog dialog;

    private TitleControl titleControl;

    private int imageType;
    private int pos;//选中列表位置
    public static String SAVED_IMAGE_DIR_PATH =
            Environment.getExternalStorageDirectory().getPath()
                    + "/AppName/camera/";// 拍照路径
    private String cameraPath = "";
    public final static int CAMERA_REQUEST_CODE = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_report_detail);
        ButterKnife.bind(this);
        initTitle();
        initListview();
        downLoad();
    }

    /**
     * 初始化状态列表
     */
    private void initListview() {
        listBeen=new ArrayList<>();
        orderReportDetailAdapter=new OrderReportDetailAdapter(listBeen, this, new OrderReportDetailAdapter.OnCamreaListener() {

            @Override
            public void getImageType(int poss, int ImageType) {
                //进行拍照
                imageType=ImageType;
                pos=poss;
                savePath();
            }


            @Override
            public void delPic(int pos, int id, int gridPos) {
                //删除图片
                delPics(pos,id,gridPos);
            }
        });
        listview.setAdapter(orderReportDetailAdapter);
    }

    /**
     * 初始化标题
     */
    private void initTitle() {
        titleControl = new TitleControl(this);

        dialog=new LoadingDialog(this,0);
    }

    /**
     * 加载网络数据
     */
    private void downLoad() {
        dialog.show();
        NetHelperNew.getReportingOrderDetail(getIntent().getStringExtra("Id"), new HttpUtils.HttpCallback() {
            @Override
            public void onSuccess(String data) {
                dialog.dismiss();
                final ReportOrderDetailBean reportOrderDetailBean  = MyGson.getInstance().fromJson(data, ReportOrderDetailBean.class);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.showToast(OrderReportDetailActivity.this,reportOrderDetailBean.getContent());
                        if (reportOrderDetailBean.getType()==1)
                        {
                            titleControl.setTitle(reportOrderDetailBean.getData().getBuildingName());

                            //更新列表
                            listBeen.addAll(reportOrderDetailBean.getData().getNewHouseRunningsInfoShowList());
                            orderReportDetailAdapter.notifyDataSetChanged();
                            initCommission(reportOrderDetailBean);
                        }

                    }
                });
            }

            @Override
            public void onError(String msg) {
                super.onError(msg);
                dialog.dismiss();
                ToastUtil.showToast(OrderReportDetailActivity.this,msg);
            }
        });
    }

    /**
     * 佣金 客户填数据
     * @param reportOrderDetailBean
     */
    private void initCommission(ReportOrderDetailBean reportOrderDetailBean) {

        tv_name.setText(reportOrderDetailBean.getData().getUserName());
        tv_tel.setText(reportOrderDetailBean.getData().getUserPhone());
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

    @OnClick({R.id.ll_customerphone})
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.ll_customerphone:
                //拨打客户电话
                OtherUitl.callPage(OrderReportDetailActivity.this, tv_tel.getText().toString());
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
    private void delPics(final int pos, int id, final int gridPos)
    {
        dialog.show();
        NetHelperNew.DelImage(id+"", new HttpUtils.HttpCallback() {
            @Override
            public void onSuccess(String data) {
                dialog.dismiss();
                final BaseBean baseBean = MyGson.getInstance().fromJson(data, BaseBean.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (baseBean.getType().equals("1"))
                        {
                            listBeen.get(pos).getImageList().remove(gridPos);
                            orderReportDetailAdapter.notifyDataSetChanged();
                        }
                        ToastUtil.showToast(OrderReportDetailActivity.this,baseBean.getContent());
                    }
                });
            }

            @Override
            public void onError(String msg) {
                super.onError(msg);
                dialog.dismiss();
                ToastUtil.showToast(OrderReportDetailActivity.this,msg);
            }
        });
    }
    /**
     * 上传图片
     */
    private void upLoadPic(String path) {
        dialog.show();
        NetHelperNew.uploadImage("3","2",""+imageType,path, new HttpUtils.HttpCallback() {
            @Override
            public void onSuccess(String data) {
                dialog.dismiss();
                final NewHouseUpLoadPicBean upLoadPicBean = MyGson.getInstance().fromJson(data, NewHouseUpLoadPicBean.class);
                if (upLoadPicBean.getType()==1) {

                    String id = upLoadPicBean.getData().getId()+"";
                    String path=upLoadPicBean.getData().getImagePath()+upLoadPicBean.getData().getFileName();
                    Log.i("-->","上传图片成功："+id);
                    //上传图片成功就刷新图片样式
                    listBeen.get(pos).getImageList().add(new ReportOrderDetailBean.DataBean.NewHouseRunningsInfoShowListBean.ImageListBean(upLoadPicBean.getData().getImagePath(),upLoadPicBean.getData().getFileName(),upLoadPicBean.getData().getId()));
                    orderReportDetailAdapter.notifyDataSetChanged();

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
