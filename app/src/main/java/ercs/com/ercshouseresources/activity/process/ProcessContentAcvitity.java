package ercs.com.ercshouseresources.activity.process;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.activity.BaseActivity;
import ercs.com.ercshouseresources.bean.BaseBean;
import ercs.com.ercshouseresources.bean.ProcessContentBean;
import ercs.com.ercshouseresources.bean.ProcessContentRetroactiveBean;
import ercs.com.ercshouseresources.network.HttpUtils;
import ercs.com.ercshouseresources.network.MyGson;
import ercs.com.ercshouseresources.network.NetHelper;
import ercs.com.ercshouseresources.util.NetWorkUtil;
import ercs.com.ercshouseresources.util.TitleControl;
import ercs.com.ercshouseresources.util.ToastUtil;
import ercs.com.ercshouseresources.util.imageUtil.GlideUtil;
import ercs.com.ercshouseresources.view.dialog.LoadingDialog;

/**
 * Created by Administrator on 2017/6/23.
 * 流程审批详细信息
 */

public class ProcessContentAcvitity extends BaseActivity {
    private String SUCCESS = "1";
    @BindView(R.id.iv_photo)
    ImageView iv_photo;//头像
    @BindView(R.id.tv_name)
    TextView tv_name;//姓名
    @BindView(R.id.tv_time)
    TextView tv_time;//时间
    @BindView(R.id.tv_processtype)
    TextView tv_processtype;//流程类型
    @BindView(R.id.tv_starttime)
    TextView tv_starttime;//开始时间
    @BindView(R.id.tv_endtime)
    TextView tv_endtime;//结束时间
    @BindView(R.id.tv_timelong)
    TextView tv_timelong;//时长
    @BindView(R.id.tv_leavereason)
    TextView tv_leavereason;//请假理由
    @BindView(R.id.edit_content)
    EditText edit_content;//主管批语
    @BindView(R.id.ll_retroactive)
    LinearLayout ll_retroactive;//补签块
     @BindView(R.id.ll_rest)
    LinearLayout ll_rest;//外出 休息块
    @BindView(R.id.tv_retroactivetime)
    TextView tv_retroactivetime;//补签时间
     @BindView(R.id.tv_retroactivetype)
    TextView tv_retroactivetype;//补签类型
    private String ApprovalUserId = "";

    private LoadingDialog loadingDialog;

    /**
     * 页面跳转
     *
     * @param mActiivty
     * @param name
     * @param time
     * @param photo
     */
    public static void start(Activity mActiivty, String name, String time, String photo, String Id, String LeaveType) {
        Intent intent = new Intent(mActiivty, ProcessContentAcvitity.class);
        intent.putExtra("name", name);
        intent.putExtra("time", time);
        intent.putExtra("photo", photo);
        intent.putExtra("Id", Id);
        intent.putExtra("LeaveType", LeaveType);
        mActiivty.startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pca);
        ButterKnife.bind(this);
        initTitle();
        if (NetWorkUtil.check(getApplicationContext()))
            if ("2".equals(getLeaveType()))
            {
                ll_retroactive.setVisibility(View.VISIBLE);
                ll_rest.setVisibility(View.GONE);
                //补签
                loadRetroactiveData();

            }else
            {
                ll_retroactive.setVisibility(View.GONE);
                ll_rest.setVisibility(View.VISIBLE);
                loadData();
            }

        setData();
    }

    /**
     * 获取 补签数据
     */
    private void loadRetroactiveData() {
        loadingDialog.show();
        NetHelper.processContent(getId(), getLeaveType(), new HttpUtils.HttpCallback() {
            @Override
            public void onSuccess(String data) {
                loadingDialog.dismiss();
                final ProcessContentRetroactiveBean processContentBean = MyGson.getInstance().fromJson(data, ProcessContentRetroactiveBean.class);
                if (processContentBean.getType()==1) {
                    setRetroactiveData(processContentBean.getData());
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.showToast(getApplicationContext(), processContentBean.getContent());
                    }
                });
            }

            @Override
            public void onError(String msg) {
                super.onError(msg);
                loadingDialog.dismiss();
                ToastUtil.showToast(getApplicationContext(), msg);
            }
        });
    }

    /**
     * 补签数据
     * @param data
     */
    private void setRetroactiveData(ProcessContentRetroactiveBean.DataBean data) {
        tv_retroactivetime.setText(data.getRetroactiveTime().substring(0,data.getRetroactiveTime().indexOf("T")));

        tv_leavereason.setText(data.getApplicationContent());
        ApprovalUserId = data.getApprovalUserId()+"";
        tv_retroactivetype.setText(getRetroactiveState(data.getRetroactiveClass()));
   }

    /**
     * 点击事件的处理
     *
     * @param view
     */
    @OnClick({R.id.btn_nopass, R.id.btn_pass})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_nopass://不同意
                if (NetWorkUtil.check(getApplicationContext()))
                    postData("3");
                break;
            case R.id.btn_pass://同意
                if (NetWorkUtil.check(getApplicationContext()))
                    postData("2");
                break;
        }
    }

    /**
     * 设置标题栏
     */
    private void initTitle() {
        TitleControl t = new TitleControl(this);
        t.setTitle(getString(R.string.str_process));
        loadingDialog=new LoadingDialog(this,0);
    }

    /**
     * 设置数据
     */
    private void setData() {
        GlideUtil.loadCircleImage(NetHelper.URL + getPhoto(), iv_photo);
        tv_name.setText(getName());
        tv_time.setText(getTime());
        tv_processtype.setText(getProcesstype(getLeaveType()));
    }

    private void setNetData(ProcessContentBean.DataBean Data) {
        tv_starttime.setText(getDateFormat(Data.getStartTime()));
        tv_endtime.setText(getDateFormat(Data.getEndTime()));
        tv_timelong.setText(Data.getTimeCount());
        tv_leavereason.setText(Data.getLeaveContent());
        ApprovalUserId = Data.getApprovalUserId();
    }

    /**
     * 获取网络数据 休息 外出
     */
    private void loadData() {
        loadingDialog.show();
        NetHelper.processContent(getId(), getLeaveType(), new HttpUtils.HttpCallback() {
            @Override
            public void onSuccess(String data) {
                loadingDialog.dismiss();
                final ProcessContentBean processContentBean = MyGson.getInstance().fromJson(data, ProcessContentBean.class);
                if (processContentBean.getType().equals(SUCCESS)) {
                    setNetData(processContentBean.getData());
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.showToast(getApplicationContext(), processContentBean.getContent());
                    }
                });
            }

            @Override
            public void onError(String msg) {
                super.onError(msg);
                loadingDialog.dismiss();
                ToastUtil.showToast(getApplicationContext(), msg);
            }
        });
    }


    private String getName() {
        return getIntent().getStringExtra("name");
    }

    private String getTime() {
        return getIntent().getStringExtra("time");
    }

    private String getPhoto() {
        return getIntent().getStringExtra("photo");
    }

    private String getLeaveType() {
        return getIntent().getStringExtra("LeaveType");
    }

    private String getId() {
        return getIntent().getStringExtra("Id");
    }

    private String getApprovalUserId() {
        return getIntent().getStringExtra("Id");
    }

    /**
     * 判断流程类型
     *
     * @param count
     * @return
     */
    private String getProcesstype(String count) {
        if (count.equals("1"))
            return getResources().getString(R.string.str_rest);
        else if (count.equals("3"))
            return getResources().getString(R.string.str_out);
        else if (count.equals("2"))
            return getResources().getString(R.string.str_retroactive);
        else
            return "";
    }


    /**
     * 判断补签类型
     *  1上班 2 下班 4上班和下班
     * @param count
     * @return
     */
    private String getRetroactiveState(String count) {
        if (count.equals("1"))
            return "上班";
        else if (count.equals("3"))
            return "下班";
        else if (count.equals("4"))
            return "上班和下班";
        else
            return "";
    }
    /**
     * 提交数据
     */
    private void postData(String LeaveState) {
        NetHelper.processReview(getId(), getLeaveType(), LeaveState, edit_content.getText().toString(), ApprovalUserId, new HttpUtils.HttpCallback() {
            @Override
            public void onSuccess(String data) {
                final BaseBean baseBean = MyGson.getInstance().fromJson(data, BaseBean.class);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.showToast(getApplicationContext(), baseBean.getContent());
                        finish();
                    }
                });
            }

            @Override
            public void onError(String msg) {
                super.onError(msg);
                ToastUtil.showToast(getApplicationContext(), msg);
            }
        });

    }

    /**
     * 将2017-07-14T13:53:02.507转为 2017-07—14 13:53
     * @param date
     * @return
     */
    public String getDateFormat(String date)
    {
        if (date!=null)
        {
            String t = date.replace("T", " ");
            String substring = t.substring(0, t.lastIndexOf(":"));
            Log.i("-->","时间转换："+substring);
            return substring;

        }

        return "";
    }
}
