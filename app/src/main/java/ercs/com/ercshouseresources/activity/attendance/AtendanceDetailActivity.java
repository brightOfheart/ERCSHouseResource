package ercs.com.ercshouseresources.activity.attendance;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.activity.BaseActivity;
import ercs.com.ercshouseresources.base.BaseApplication;
import ercs.com.ercshouseresources.bean.AtendanceDetailBean;
import ercs.com.ercshouseresources.network.HttpUtils;
import ercs.com.ercshouseresources.network.MyGson;
import ercs.com.ercshouseresources.network.NetHelper;
import ercs.com.ercshouseresources.util.OtherUitl;
import ercs.com.ercshouseresources.util.SPUtil;
import ercs.com.ercshouseresources.util.TitleControl;
import ercs.com.ercshouseresources.util.ToastUtil;
import ercs.com.ercshouseresources.util.imageUtil.GlideUtil;
import ercs.com.ercshouseresources.view.dialog.LoadingDialog;

import static ercs.com.ercshouseresources.R.id.tv_timelong;

/**
 * Created by Administrator on 2017/7/12.
 * 考勤统计详情
 */

public class AtendanceDetailActivity extends BaseActivity {

    private LoadingDialog loadingDialog;
    private SPUtil spUtil;

    @BindView(R.id.iv_photo)
     ImageView iv_photo;
    @BindView(R.id.tv_name)
     TextView tv_name;
    @BindView(R.id.tv_year)
     TextView tv_year;

    @BindView(R.id.tv_uptime)
     TextView tv_uptime;//上班打卡时间
     @BindView(R.id.tv_upbantime)
     TextView tv_upbantime;//上班时间
        @BindView(R.id.tv_upaddress)
     TextView tv_upaddress;//上班打卡地点

        @BindView(R.id.tv_uptype)
     TextView tv_uptype;//上班打卡类型


    @BindView(R.id.tv_downtime)
     TextView tv_downtime;//下班打卡时间
     @BindView(R.id.tv_downbantime)
     TextView tv_downbantime;//下班时间
        @BindView(R.id.tv_downaddress)
     TextView tv_downaddress;//下班打卡地点

        @BindView(R.id.tv_downtype)
     TextView tv_downtype;//下班打卡类型

     @BindView(R.id.tv_frequency)
     TextView tv_frequency;//打卡次数

    @BindView(R.id.btn_contact)
    Button btn_contact;//联系管理员
    private int n=0;//签到次数
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atendancedetail);
        ButterKnife.bind(this);
        initTitle();
        creatData();

        downLoad(getParamsDate());
    }

    /**\
     * 初始化数据
     */
    private void creatData() {
        if (spUtil==null)
            spUtil = new SPUtil(this, "fileName");

        tv_name.setText(spUtil.getString(BaseApplication.NAME,""));

        GlideUtil.loadCircleImage(NetHelper.URL + spUtil.getString(BaseApplication.PHOTOPATH, ""), iv_photo);
        tv_year.setText(getParamsDate()+" ");
    }

    /**
     * 网络请求数据
     * @param paramsDate
     */
    private void downLoad(String paramsDate) {

        loadingDialog.show();
        int i = paramsDate.indexOf("(");
        String substring = paramsDate.substring(0, i);

        NetHelper.atendanceDetail(spUtil.getString(BaseApplication.ID, ""), substring, new HttpUtils.HttpCallback() {
            @Override
            public void onSuccess(String data) {
                loadingDialog.dismiss();
                final AtendanceDetailBean atendanceDetailBean = MyGson.getInstance().fromJson(data, AtendanceDetailBean.class);
                if (atendanceDetailBean.getData()!=null)
                setData(atendanceDetailBean);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        loadingDialog.dismiss();
                        ToastUtil.showToast(getApplicationContext(),atendanceDetailBean.getContent());
                    }
                });
            }

            @Override
            public void onError(String msg) {
                super.onError(msg);
                loadingDialog.dismiss();
                ToastUtil.showToast(getApplicationContext(),msg);
            }
        });
    }

    /**
     * 计算时长
     */
    private String calculationTime(String starttime,String endtime) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Long startLong = 0L;
        Long endLong = 0L;
        try {
            startLong = simpleDateFormat.parse(starttime.replace("T"," ").replace("Z","")).getTime();
            endLong = simpleDateFormat.parse(endtime.replace("T"," ").replace("Z","")).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (0!=startLong&&0!=endLong)
        {
            Long timelong= endLong-startLong;



            long hours =timelong/(1000* 60 * 60); //换算成小时

            long minutes =(timelong-hours*(1000* 60 * 60))/(1000* 60); //换算成分钟
            return hours+"小时"+minutes+"分钟";
        }
        return "";
    }
    /**
     * 2017-07-12T15:50:40Z 转为15:50
     * @param s
     * @return
     */
    public String getTime(String s)
    {
        if (s==null)
            return "";

        int t = s.indexOf("T");

        int i = s.lastIndexOf(":");
        String substring = s.substring(t+1, i);

        return substring;
    }
    /**
     * 设置返回数据
     * @param atendanceDetailBean
     */
    private void setData(AtendanceDetailBean atendanceDetailBean) {

        tv_uptime.setText("上班打卡时间"+getTime(atendanceDetailBean.getData().getStartTime()));
        tv_downtime.setText("下班打卡时间"+getTime(atendanceDetailBean.getData().getEndTime()));
        tv_upbantime.setText("(上班时间"+atendanceDetailBean.getData().getAttStrtime()+")");
        tv_downbantime.setText("(下班时间"+atendanceDetailBean.getData().getAttEndtime()+")");
        if (atendanceDetailBean.getData().getStartLocation()!=null)
        {
            tv_upaddress.setVisibility(View.VISIBLE);
            tv_upaddress.setText(atendanceDetailBean.getData().getStartLocation());
        }
        if (atendanceDetailBean.getData().getStartLocation()!=null)
        {
            tv_downaddress.setVisibility(View.VISIBLE);
            tv_downaddress.setText(atendanceDetailBean.getData().getEndLocation());
        }


        String startContent = atendanceDetailBean.getData().getStartContent();


        if (startContent!=null)
        {
            switch (startContent)
            {
                case "0":
                    //未签
                    tv_uptype.setText("未签");

                    tv_uptime.setVisibility(View.GONE);
                    tv_upbantime.setVisibility(View.GONE);
                    break;
                case "1":
                    //正常
                    tv_uptype.setText("正常");
                    n++;
                    break;
                case "2":
                    //迟到
                    tv_uptype.setText("迟到");
                    n++;
                    break;
                case "3":
                    //早退
                    tv_uptype.setText("早退");
                    n++;
                    break;
                case "4":
                    //旷工
                    tv_uptype.setText("旷工");
                    tv_uptime.setVisibility(View.GONE);
                    tv_upbantime.setVisibility(View.GONE);
                    break;
                case "5":
                    //补签
                    tv_uptype.setText("补签");
                    n++;
                    break;
            }
        }

        String endContent = atendanceDetailBean.getData().getEndContent();
        if (endContent!=null)
        {
            switch (endContent)
            {
                case "0":
                    //未签
                    tv_downtype.setText("未签");
                    tv_downtime.setVisibility(View.GONE);
                    tv_downbantime.setVisibility(View.GONE);
                    break;
                case "1":
                    //正常
                    tv_downtype.setText("正常");
                    n++;
                    break;
                case "2":
                    //迟到
                    tv_downtype.setText("迟到");
                    n++;
                    break;
                case "3":
                    //早退
                    tv_downtype.setText("早退");
                    n++;
                    break;
                case "4":
                    //旷工
                    tv_downtype.setText("旷工");
                    tv_downtime.setVisibility(View.GONE);
                    tv_downbantime.setVisibility(View.GONE);
                    break;
                case "5":
                    //补签
                    tv_downtype.setText("补签");
                    n++;
                    break;
            }
        }


        if (n==2)
        {
            String s = calculationTime(atendanceDetailBean.getData().getStartTime(), atendanceDetailBean.getData().getEndTime());
            tv_frequency.setText("今日打卡"+n+"次,工时共计"+s);
        }else
        {
            tv_frequency.setText("今日打卡"+n+"次");
        }




    }

    /**
     * 设置标题栏
     */
    private void initTitle() {
        TitleControl t = new TitleControl(this);
        t.setTitle(getString(R.string.str_attendancedetail));
        loadingDialog=new LoadingDialog(this,0);

    }

    /**
     * 页面跳转
     * @param mActivity
     * @param ParamsDate
     */
    public static void start(Activity mActivity,String ParamsDate){

        Intent intent=new Intent(mActivity,AtendanceDetailActivity.class);

        intent.putExtra("ParamsDate",ParamsDate);
        mActivity.startActivity(intent);
    }

    private String getParamsDate()
    {
        return getIntent().getStringExtra("ParamsDate");
    }


    /**
     * 点击事件处理
     * @param view
     */
    @OnClick({R.id.btn_contact})
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btn_contact:
                OtherUitl.callPage(AtendanceDetailActivity.this, "12321235545");
                break;
        }
    }
}
