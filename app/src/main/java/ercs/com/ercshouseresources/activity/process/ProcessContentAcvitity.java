package ercs.com.ercshouseresources.activity.process;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.activity.BaseActivity;
import ercs.com.ercshouseresources.bean.BaseBean;
import ercs.com.ercshouseresources.bean.ProcessContentBean;
import ercs.com.ercshouseresources.network.HttpUtils;
import ercs.com.ercshouseresources.network.MyGson;
import ercs.com.ercshouseresources.network.NetHelper;
import ercs.com.ercshouseresources.util.NetWorkUtil;
import ercs.com.ercshouseresources.util.TitleControl;
import ercs.com.ercshouseresources.util.ToastUtil;
import ercs.com.ercshouseresources.util.imageUtil.GlideUtil;

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
    private String ApprovalUserId = "";

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
            loadData();
        setData();
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
        tv_starttime.setText(Data.getStartTime());
        tv_endtime.setText(Data.getEndTime());
        tv_timelong.setText(Data.getTimeCount());
        tv_leavereason.setText(Data.getLeaveContent());
        ApprovalUserId = Data.getApprovalUserId();
    }

    /**
     * 获取网络数据
     */
    private void loadData() {
        NetHelper.processContent(getId(), getLeaveType(), new HttpUtils.HttpCallback() {
            @Override
            public void onSuccess(String data) {
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

}
