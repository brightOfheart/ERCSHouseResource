package ercs.com.ercshouseresources.activity.attendance;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.activity.BaseActivity;
import ercs.com.ercshouseresources.base.BaseApplication;
import ercs.com.ercshouseresources.bean.AtendanceBean;
import ercs.com.ercshouseresources.network.HttpUtils;
import ercs.com.ercshouseresources.network.MyGson;
import ercs.com.ercshouseresources.network.NetHelper;
import ercs.com.ercshouseresources.util.NetWorkUtil;
import ercs.com.ercshouseresources.util.SPUtil;
import ercs.com.ercshouseresources.util.TitleControl;
import ercs.com.ercshouseresources.util.ToastUtil;
import ercs.com.ercshouseresources.util.imageUtil.GlideUtil;
import ercs.com.ercshouseresources.view.atendance.AtendanceItem;
import ercs.com.ercshouseresources.view.dialog.CustomerDatePickerDialog;
import ercs.com.ercshouseresources.view.dialog.LoadingDialog;

/**
 * Created by Administrator on 2017/6/22.
 * 考勤记录
 */

public class AtendanceActivity extends BaseActivity {
    @BindView(R.id.ly1)
    LinearLayout ly1;//中间的数据
    @BindView(R.id.ly2)
    LinearLayout ly2;//中间的数据
    @BindView(R.id.ly3)
    LinearLayout ly3;//中间的数据
    @BindView(R.id.ly4)
    LinearLayout ly4;//中间的数据
    @BindView(R.id.ly5)
    LinearLayout ly5;//中间的数据
    @BindView(R.id.ly6)
    LinearLayout ly6;//中间的数据
    @BindView(R.id.ly7)
    LinearLayout ly7;//中间的数据
    @BindView(R.id.btn_lookanother)
    Button btn_lookanother;//查看其员工
    @BindView(R.id.tv_year)
    TextView tv_year;//年月日
    @BindView(R.id.tv_name)
    TextView tv_name;//姓名
    @BindView(R.id.iv_photo)
    ImageView iv_photo;//头像
    private SPUtil spUtil;
    private LoadingDialog dialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acvitity_atendance);
        ButterKnife.bind(this);
        initTitle();
        createDate();
        if (NetWorkUtil.check(getApplicationContext())) {
            if (isMyself()) {
                getNetData(getId(), getYear() + "-" + getMonth() + "-" + getDay());
            } else {
                getNetData(getOtherId(), getYear() + "-" + getMonth() + "-" + getDay());
            }

        }

    }

    /**
     * 页面跳转
     *
     * @param mactivity
     * @param PhonePaht
     * @param Name
     * @param OtherId
     */
    public static void start(Activity mactivity, String PhonePaht, String Name, String OtherId) {
        Intent intent = new Intent(mactivity, AtendanceActivity.class);
        intent.putExtra("PhonePath", PhonePaht);
        intent.putExtra("Name", Name);
        intent.putExtra("OtherId", OtherId);
        mactivity.startActivity(intent);
    }

    private String getPhonePath() {
        return getIntent().getStringExtra("PhonePath");
    }

    private String getName() {
        return getIntent().getStringExtra("Name");
    }

    private String getOtherId() {
        return getIntent().getStringExtra("OtherId");
    }

    /**
     * 是否展示自己数据
     *
     * @return
     */
    private boolean isMyself() {
        if (null != getIntent().getStringExtra("PhonePath")) {
            return false;
        }
        return true;
    }

    /**
     * 设置标题栏
     */
    private void initTitle() {
        TitleControl t = new TitleControl(this);
        t.setTitle(getString(R.string.str_attendance));
    }

    /**
     * 初始化数据
     */
    private void createDate() {
        if (spUtil == null)
            spUtil = new SPUtil(this, "fileName");
        if (isMyself()) {
            tv_name.setText(spUtil.getString(BaseApplication.NAME, ""));
            GlideUtil.loadCircleImage(NetHelper.URL + spUtil.getString(BaseApplication.PHOTOPATH, ""), iv_photo);
        } else {
            tv_name.setText(getName());
            GlideUtil.loadCircleImage(NetHelper.URL + getPhonePath(), iv_photo);
        }

        tv_year.setText(getYear() + "年" + getMonth() + "月");
        dialog = new LoadingDialog(AtendanceActivity.this, 0);
    }

    /**
     * 点击事件的处理
     *
     * @param view
     */
    @OnClick({R.id.tv_year, R.id.btn_lookanother})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_year://选择年月
                new CustomerDatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        if (NetWorkUtil.check(getApplicationContext())) {
                            tv_year.setText(year + "年" + (month + 1) + "月");
                            clear();
                            if (isMyself()) {
                                getNetData(getId(), year + "-" + (month + 1) + "-" + day);
                            } else {
                                getNetData(getOtherId(), year + "-" + (month + 1) + "-" + day);
                            }

                        }
                    }
                }, getYear(), getMonth(), getDay());
                break;
            case R.id.btn_lookanother://查看其它员工的考勤

                break;

        }
    }

    /**
     * 获取id
     *
     * @return
     */
    private String getId() {
        return spUtil.getString(BaseApplication.ID, "");
    }

    /**
     * 获取网络数据
     */
    private void getNetData(String id, String date) {
        dialog.show();
        NetHelper.atendance(id, date, new HttpUtils.HttpCallback() {
            @Override
            public void onSuccess(String data) {
                final AtendanceBean atendanceBean = MyGson.getInstance().fromJson(data, AtendanceBean.class);
                setData(atendanceBean);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        dialog.dismiss();
                        ToastUtil.showToast(getApplicationContext(), atendanceBean.getContent());
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
     * 设置返回的数据
     *
     * @param atendanceBean
     */
    private void setData(AtendanceBean atendanceBean) {
        String nameStr = "";
        String pathStr = "";
        if (isMyself()) {
            nameStr = spUtil.getString(BaseApplication.NAME, "");
            pathStr = spUtil.getString(BaseApplication.PHOTOPATH, "");
        } else {
            nameStr = getName();
            pathStr = getPhonePath();
        }
        for (int i = 0; i < atendanceBean.getData().size(); i++) {
            if (atendanceBean.getData().get(i).getStatisticsType().equals("1"))
                ly1.addView(new AtendanceItem(AtendanceActivity.this, atendanceBean.getData().get(i), atendanceBean.getData().get(i).getInSideStatisticsListMode(), nameStr, pathStr));
            else if (atendanceBean.getData().get(i).getStatisticsType().equals("2"))
                ly2.addView(new AtendanceItem(AtendanceActivity.this, atendanceBean.getData().get(i), atendanceBean.getData().get(i).getInSideStatisticsListMode(), nameStr, pathStr));
            else if (atendanceBean.getData().get(i).getStatisticsType().equals("3"))
                ly3.addView(new AtendanceItem(AtendanceActivity.this, atendanceBean.getData().get(i), atendanceBean.getData().get(i).getInSideStatisticsListMode(), nameStr, pathStr));
            else if (atendanceBean.getData().get(i).getStatisticsType().equals("4"))
                ly4.addView(new AtendanceItem(AtendanceActivity.this, atendanceBean.getData().get(i), atendanceBean.getData().get(i).getInSideStatisticsListMode(), nameStr, pathStr));
            else if (atendanceBean.getData().get(i).getStatisticsType().equals("5"))
                ly5.addView(new AtendanceItem(AtendanceActivity.this, atendanceBean.getData().get(i), atendanceBean.getData().get(i).getInSideStatisticsListMode(), nameStr, pathStr));
            else if (atendanceBean.getData().get(i).getStatisticsType().equals("6"))
                ly6.addView(new AtendanceItem(AtendanceActivity.this, atendanceBean.getData().get(i), atendanceBean.getData().get(i).getInSideStatisticsListMode(), nameStr, pathStr));
            else if (atendanceBean.getData().get(i).getStatisticsType().equals("7"))
                ly7.addView(new AtendanceItem(AtendanceActivity.this, atendanceBean.getData().get(i), atendanceBean.getData().get(i).getInSideStatisticsListMode(), nameStr, pathStr));
        }

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
     * 清空
     */
    private void clear() {
        ly1.removeAllViews();
        ly2.removeAllViews();
        ly3.removeAllViews();
        ly4.removeAllViews();
        ly5.removeAllViews();
        ly6.removeAllViews();
        ly7.removeAllViews();
    }
}
