package ercs.com.ercshouseresources.activity.field;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.activity.BaseActivity;
import ercs.com.ercshouseresources.base.BaseApplication;
import ercs.com.ercshouseresources.bean.FieldBean;
import ercs.com.ercshouseresources.bean.FieldCustomBean;
import ercs.com.ercshouseresources.bean.FieldCustomContentBean;
import ercs.com.ercshouseresources.network.HttpUtils;
import ercs.com.ercshouseresources.network.MyGson;
import ercs.com.ercshouseresources.network.NetHelper;
import ercs.com.ercshouseresources.util.NetWorkUtil;
import ercs.com.ercshouseresources.util.SPUtil;
import ercs.com.ercshouseresources.util.TimeUtil;
import ercs.com.ercshouseresources.util.TitleControl;
import ercs.com.ercshouseresources.util.ToastUtil;
import ercs.com.ercshouseresources.util.imageUtil.GlideUtil;
import ercs.com.ercshouseresources.view.dialog.CustomerDatePickerDialog;
import ercs.com.ercshouseresources.view.dialog.LoadingDialog;
import ercs.com.ercshouseresources.view.item.FieldItem;

/**
 * Created by Administrator on 2017/6/27.
 * 外勤统计
 */

public class FieldActivity extends BaseActivity {
    private String SUCCESS = "1";
    @BindView(R.id.ly_all)
    LinearLayout ly_all;
    private List<String> list_days = new ArrayList<>();
    private List<FieldCustomBean> list1 = new ArrayList<>();
    private List<FieldCustomBean> list2 = new ArrayList<>();
    private List<FieldCustomBean> list3 = new ArrayList<>();
    private List<FieldCustomBean> list4 = new ArrayList<>();
    private List<FieldCustomBean> list5 = new ArrayList<>();
    private List<FieldCustomBean> list6 = new ArrayList<>();
    private List<FieldCustomBean> list7 = new ArrayList<>();
    private List<FieldCustomBean> list8 = new ArrayList<>();
    private List<FieldCustomBean> list9 = new ArrayList<>();
    private List<FieldCustomBean> list10 = new ArrayList<>();
    private List<FieldCustomBean> list11 = new ArrayList<>();
    private List<FieldCustomBean> list12 = new ArrayList<>();
    private List<FieldCustomBean> list13 = new ArrayList<>();
    private List<FieldCustomBean> list14 = new ArrayList<>();
    private List<FieldCustomBean> list15 = new ArrayList<>();
    private List<FieldCustomBean> list16 = new ArrayList<>();
    private List<FieldCustomBean> list17 = new ArrayList<>();
    private List<FieldCustomBean> list18 = new ArrayList<>();
    private List<FieldCustomBean> list19 = new ArrayList<>();
    private List<FieldCustomBean> list20 = new ArrayList<>();
    private List<FieldCustomBean> list21 = new ArrayList<>();
    private List<FieldCustomBean> list22 = new ArrayList<>();
    private List<FieldCustomBean> list23 = new ArrayList<>();
    private List<FieldCustomBean> list24 = new ArrayList<>();
    private List<FieldCustomBean> list25 = new ArrayList<>();
    private List<FieldCustomBean> list26 = new ArrayList<>();
    private List<FieldCustomBean> list27 = new ArrayList<>();
    private List<FieldCustomBean> list28 = new ArrayList<>();
    private List<FieldCustomBean> list29 = new ArrayList<>();
    private List<FieldCustomBean> list30 = new ArrayList<>();
    private List<FieldCustomBean> list31 = new ArrayList<>();
    private LoadingDialog dialog;
    @BindView(R.id.tv_year)
    TextView tv_year;//年月日
    @BindView(R.id.tv_name)
    TextView tv_name;//姓名
    @BindView(R.id.iv_photo)
    ImageView iv_photo;//头像
    private SPUtil spUtil;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_field);
        ButterKnife.bind(this);
        initTitle();
        createData();
        setDays();
        if (NetWorkUtil.check(getApplicationContext()))
            loadData(getYear() + "-" + getMonth() + "-" + getDay());
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
                            loadData(year + "-" + (month + 1) + "-" + day);
                        }
                    }
                }, getYear(), getMonth(), getDay());
                break;
            case R.id.btn_lookanother://查看其它员工的考勤

                break;

        }
    }

    private void createData() {
        if (spUtil == null)
            spUtil = new SPUtil(this, "fileName");
        tv_name.setText(spUtil.getString(BaseApplication.NAME, ""));
        GlideUtil.loadCircleImage(NetHelper.URL + spUtil.getString(BaseApplication.PHOTOPATH, ""), iv_photo);
        tv_year.setText(getYear() + "年" + getMonth() + "月");
    }

    /**
     * 设置31个工作日
     */
    private void setDays() {
        for (int i = 1; i < 32; i++) {
            if (i < 10)
                list_days.add("0" + i);
            else
                list_days.add(i + "");
        }
    }

    /**
     * 设置标题栏
     */
    private void initTitle() {
        TitleControl t = new TitleControl(this);
        t.setTitle(getString(R.string.str_field));
        dialog = new LoadingDialog(FieldActivity.this, 0);
    }

    /**
     * 获取网络数据
     */
    private void loadData(String date) {
        dialog.show();
        NetHelper.outside("", date, new HttpUtils.HttpCallback() {
            @Override
            public void onSuccess(String data) {
                final FieldBean fieldBean = MyGson.getInstance().fromJson(data, FieldBean.class);
                if (fieldBean.getType().equals("1")) {
                    getData(fieldBean);
                    setDate();
                }
                dialog.dismiss();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.showToast(getApplicationContext(), fieldBean.getContent());

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
     * 设置显示数据
     */
    private void setDate() {
        Log.d("xxx", list3.size() + "/");
        if (list1.size() > 0)
            ly_all.addView(new FieldItem(this, list1, getChild(list1)));
        if (list2.size() > 0)
            ly_all.addView(new FieldItem(this, list2, getChild(list2)));
        if (list3.size() > 0)
            ly_all.addView(new FieldItem(this, list3, getChild(list3)));
        if (list4.size() > 0)
            ly_all.addView(new FieldItem(this, list4, getChild(list4)));
        if (list5.size() > 0)
            ly_all.addView(new FieldItem(this, list5, getChild(list5)));
        if (list6.size() > 0)
            ly_all.addView(new FieldItem(this, list6, getChild(list6)));
        if (list7.size() > 0)
            ly_all.addView(new FieldItem(this, list7, getChild(list7)));
        if (list8.size() > 0)
            ly_all.addView(new FieldItem(this, list8, getChild(list8)));
        if (list9.size() > 0)
            ly_all.addView(new FieldItem(this, list9, getChild(list9)));
        if (list10.size() > 0)
            ly_all.addView(new FieldItem(this, list10, getChild(list10)));
        if (list11.size() > 0)
            ly_all.addView(new FieldItem(this, list11, getChild(list11)));
        if (list12.size() > 0)
            ly_all.addView(new FieldItem(this, list12, getChild(list12)));
        if (list13.size() > 0)
            ly_all.addView(new FieldItem(this, list13, getChild(list13)));
        if (list14.size() > 0)
            ly_all.addView(new FieldItem(this, list14, getChild(list14)));
        if (list15.size() > 0)
            ly_all.addView(new FieldItem(this, list15, getChild(list15)));
        if (list16.size() > 0)
            ly_all.addView(new FieldItem(this, list16, getChild(list16)));
        if (list17.size() > 0)
            ly_all.addView(new FieldItem(this, list17, getChild(list17)));
        if (list18.size() > 0)
            ly_all.addView(new FieldItem(this, list18, getChild(list18)));
        if (list19.size() > 0)
            ly_all.addView(new FieldItem(this, list19, getChild(list19)));
        if (list20.size() > 0)
            ly_all.addView(new FieldItem(this, list20, getChild(list20)));
        if (list21.size() > 0)
            ly_all.addView(new FieldItem(this, list21, getChild(list21)));
        if (list22.size() > 0)
            ly_all.addView(new FieldItem(this, list22, getChild(list22)));
        if (list23.size() > 0)
            ly_all.addView(new FieldItem(this, list23, getChild(list23)));
        if (list24.size() > 0)
            ly_all.addView(new FieldItem(this, list24, getChild(list24)));
        if (list25.size() > 0)
            ly_all.addView(new FieldItem(this, list25, getChild(list25)));
        if (list26.size() > 0)
            ly_all.addView(new FieldItem(this, list26, getChild(list26)));
        if (list27.size() > 0)
            ly_all.addView(new FieldItem(this, list27, getChild(list27)));
        if (list28.size() > 0)
            ly_all.addView(new FieldItem(this, list28, getChild(list28)));
        if (list29.size() > 0)
            ly_all.addView(new FieldItem(this, list29, getChild(list29)));
        if (list30.size() > 0)
            ly_all.addView(new FieldItem(this, list30, getChild(list30)));
        if (list31.size() > 0)
            ly_all.addView(new FieldItem(this, list31, getChild(list31)));
    }

    /**
     * 重新封装网络获取的数据
     *
     * @param fieldBean
     */
    private void getData(FieldBean fieldBean) {
        for (int i = 0; i < fieldBean.getData().size(); i++) {
            if ("01".equals(getDays(fieldBean.getData().get(i).getCreatedTime()))) {
                FieldCustomBean fieldCustomBean = new FieldCustomBean();
                fieldCustomBean.setTime(getDay(fieldBean.getData().get(i).getCreatedTime()) + "（" + TimeUtil.getWeekOfDate(getDay(fieldBean.getData().get(i).getCreatedTime())) + ")");
                FieldCustomContentBean contentBean = new FieldCustomContentBean();
                contentBean.setAddress(fieldBean.getData().get(i).getLocation());
                contentBean.setContent(fieldBean.getData().get(i).getOutSideContent());
                contentBean.setPath(fieldBean.getData().get(i).getImagePage());
                contentBean.setHour(getHour(getAllDay(fieldBean.getData().get(i).getCreatedTime())));
                fieldCustomBean.setContentBean(contentBean);
                list1.add(fieldCustomBean);
            } else if ("02".equals(getDays(fieldBean.getData().get(i).getCreatedTime()))) {
                FieldCustomBean fieldCustomBean = new FieldCustomBean();
                fieldCustomBean.setTime(getDay(fieldBean.getData().get(i).getCreatedTime()) + "（" + TimeUtil.getWeekOfDate(getDay(fieldBean.getData().get(i).getCreatedTime())) + ")");
                FieldCustomContentBean contentBean = new FieldCustomContentBean();
                contentBean.setAddress(fieldBean.getData().get(i).getLocation());
                contentBean.setContent(fieldBean.getData().get(i).getOutSideContent());
                contentBean.setPath(fieldBean.getData().get(i).getImagePage());
                contentBean.setHour(getHour(getAllDay(fieldBean.getData().get(i).getCreatedTime())));
                fieldCustomBean.setContentBean(contentBean);
                list2.add(fieldCustomBean);
            } else if ("03".equals(getDays(fieldBean.getData().get(i).getCreatedTime()))) {
                FieldCustomBean fieldCustomBean = new FieldCustomBean();
                fieldCustomBean.setTime(getDay(fieldBean.getData().get(i).getCreatedTime()) + "（" + TimeUtil.getWeekOfDate(getDay(fieldBean.getData().get(i).getCreatedTime())) + ")");
                FieldCustomContentBean contentBean = new FieldCustomContentBean();
                contentBean.setAddress(fieldBean.getData().get(i).getLocation());
                contentBean.setContent(fieldBean.getData().get(i).getOutSideContent());
                contentBean.setPath(fieldBean.getData().get(i).getImagePage());
                contentBean.setHour(getHour(getAllDay(fieldBean.getData().get(i).getCreatedTime())));
                fieldCustomBean.setContentBean(contentBean);
                list3.add(fieldCustomBean);
            } else if ("04".equals(getDays(fieldBean.getData().get(i).getCreatedTime()))) {
                FieldCustomBean fieldCustomBean = new FieldCustomBean();
                fieldCustomBean.setTime(getDay(fieldBean.getData().get(i).getCreatedTime()) + "（" + TimeUtil.getWeekOfDate(getDay(fieldBean.getData().get(i).getCreatedTime())) + ")");
                FieldCustomContentBean contentBean = new FieldCustomContentBean();
                contentBean.setAddress(fieldBean.getData().get(i).getLocation());
                contentBean.setContent(fieldBean.getData().get(i).getOutSideContent());
                contentBean.setPath(fieldBean.getData().get(i).getImagePage());
                contentBean.setHour(getHour(getAllDay(fieldBean.getData().get(i).getCreatedTime())));
                fieldCustomBean.setContentBean(contentBean);
                list4.add(fieldCustomBean);
            } else if ("05".equals(getDays(fieldBean.getData().get(i).getCreatedTime()))) {
                FieldCustomBean fieldCustomBean = new FieldCustomBean();
                fieldCustomBean.setTime(getDay(fieldBean.getData().get(i).getCreatedTime()) + "（" + TimeUtil.getWeekOfDate(getDay(fieldBean.getData().get(i).getCreatedTime())) + ")");
                FieldCustomContentBean contentBean = new FieldCustomContentBean();
                contentBean.setAddress(fieldBean.getData().get(i).getLocation());
                contentBean.setContent(fieldBean.getData().get(i).getOutSideContent());
                contentBean.setPath(fieldBean.getData().get(i).getImagePage());
                contentBean.setHour(getHour(getAllDay(fieldBean.getData().get(i).getCreatedTime())));
                fieldCustomBean.setContentBean(contentBean);
                list5.add(fieldCustomBean);
            } else if ("06".equals(getDays(fieldBean.getData().get(i).getCreatedTime()))) {
                FieldCustomBean fieldCustomBean = new FieldCustomBean();
                fieldCustomBean.setTime(getDay(fieldBean.getData().get(i).getCreatedTime()) + "（" + TimeUtil.getWeekOfDate(getDay(fieldBean.getData().get(i).getCreatedTime())) + ")");
                FieldCustomContentBean contentBean = new FieldCustomContentBean();
                contentBean.setAddress(fieldBean.getData().get(i).getLocation());
                contentBean.setContent(fieldBean.getData().get(i).getOutSideContent());
                contentBean.setPath(fieldBean.getData().get(i).getImagePage());
                contentBean.setHour(getHour(getAllDay(fieldBean.getData().get(i).getCreatedTime())));
                fieldCustomBean.setContentBean(contentBean);
                list6.add(fieldCustomBean);
            } else if ("07".equals(getDays(fieldBean.getData().get(i).getCreatedTime()))) {
                FieldCustomBean fieldCustomBean = new FieldCustomBean();
                fieldCustomBean.setTime(getDay(fieldBean.getData().get(i).getCreatedTime()) + "（" + TimeUtil.getWeekOfDate(getDay(fieldBean.getData().get(i).getCreatedTime())) + ")");
                FieldCustomContentBean contentBean = new FieldCustomContentBean();
                contentBean.setAddress(fieldBean.getData().get(i).getLocation());
                contentBean.setContent(fieldBean.getData().get(i).getOutSideContent());
                contentBean.setPath(fieldBean.getData().get(i).getImagePage());
                contentBean.setHour(getHour(getAllDay(fieldBean.getData().get(i).getCreatedTime())));
                fieldCustomBean.setContentBean(contentBean);
                list7.add(fieldCustomBean);
            } else if ("08".equals(getDays(fieldBean.getData().get(i).getCreatedTime()))) {
                FieldCustomBean fieldCustomBean = new FieldCustomBean();
                fieldCustomBean.setTime(getDay(fieldBean.getData().get(i).getCreatedTime()) + "（" + TimeUtil.getWeekOfDate(getDay(fieldBean.getData().get(i).getCreatedTime())) + ")");
                FieldCustomContentBean contentBean = new FieldCustomContentBean();
                contentBean.setAddress(fieldBean.getData().get(i).getLocation());
                contentBean.setContent(fieldBean.getData().get(i).getOutSideContent());
                contentBean.setPath(fieldBean.getData().get(i).getImagePage());
                contentBean.setHour(getHour(getAllDay(fieldBean.getData().get(i).getCreatedTime())));
                fieldCustomBean.setContentBean(contentBean);
                list8.add(fieldCustomBean);
            } else if ("09".equals(getDays(fieldBean.getData().get(i).getCreatedTime()))) {
                FieldCustomBean fieldCustomBean = new FieldCustomBean();
                fieldCustomBean.setTime(getDay(fieldBean.getData().get(i).getCreatedTime()) + "（" + TimeUtil.getWeekOfDate(getDay(fieldBean.getData().get(i).getCreatedTime())) + ")");
                FieldCustomContentBean contentBean = new FieldCustomContentBean();
                contentBean.setAddress(fieldBean.getData().get(i).getLocation());
                contentBean.setContent(fieldBean.getData().get(i).getOutSideContent());
                contentBean.setPath(fieldBean.getData().get(i).getImagePage());
                contentBean.setHour(getHour(getAllDay(fieldBean.getData().get(i).getCreatedTime())));
                fieldCustomBean.setContentBean(contentBean);
                list9.add(fieldCustomBean);
            } else if ("10".equals(getDays(fieldBean.getData().get(i).getCreatedTime()))) {
                FieldCustomBean fieldCustomBean = new FieldCustomBean();
                fieldCustomBean.setTime(getDay(fieldBean.getData().get(i).getCreatedTime()) + "（" + TimeUtil.getWeekOfDate(getDay(fieldBean.getData().get(i).getCreatedTime())) + ")");
                FieldCustomContentBean contentBean = new FieldCustomContentBean();
                contentBean.setAddress(fieldBean.getData().get(i).getLocation());
                contentBean.setContent(fieldBean.getData().get(i).getOutSideContent());
                contentBean.setPath(fieldBean.getData().get(i).getImagePage());
                contentBean.setHour(getHour(getAllDay(fieldBean.getData().get(i).getCreatedTime())));
                fieldCustomBean.setContentBean(contentBean);
                list10.add(fieldCustomBean);
            } else if ("11".equals(getDays(fieldBean.getData().get(i).getCreatedTime()))) {
                FieldCustomBean fieldCustomBean = new FieldCustomBean();
                fieldCustomBean.setTime(getDay(fieldBean.getData().get(i).getCreatedTime()) + "（" + TimeUtil.getWeekOfDate(getDay(fieldBean.getData().get(i).getCreatedTime())) + ")");
                FieldCustomContentBean contentBean = new FieldCustomContentBean();
                contentBean.setAddress(fieldBean.getData().get(i).getLocation());
                contentBean.setContent(fieldBean.getData().get(i).getOutSideContent());
                contentBean.setPath(fieldBean.getData().get(i).getImagePage());
                contentBean.setHour(getHour(getAllDay(fieldBean.getData().get(i).getCreatedTime())));
                fieldCustomBean.setContentBean(contentBean);
                list11.add(fieldCustomBean);
            } else if ("12".equals(getDays(fieldBean.getData().get(i).getCreatedTime()))) {
                FieldCustomBean fieldCustomBean = new FieldCustomBean();
                fieldCustomBean.setTime(getDay(fieldBean.getData().get(i).getCreatedTime()) + "（" + TimeUtil.getWeekOfDate(getDay(fieldBean.getData().get(i).getCreatedTime())) + ")");
                FieldCustomContentBean contentBean = new FieldCustomContentBean();
                contentBean.setAddress(fieldBean.getData().get(i).getLocation());
                contentBean.setContent(fieldBean.getData().get(i).getOutSideContent());
                contentBean.setPath(fieldBean.getData().get(i).getImagePage());
                contentBean.setHour(getHour(getAllDay(fieldBean.getData().get(i).getCreatedTime())));
                fieldCustomBean.setContentBean(contentBean);
                list12.add(fieldCustomBean);
            } else if ("13".equals(getDays(fieldBean.getData().get(i).getCreatedTime()))) {
                FieldCustomBean fieldCustomBean = new FieldCustomBean();
                fieldCustomBean.setTime(getDay(fieldBean.getData().get(i).getCreatedTime()) + "（" + TimeUtil.getWeekOfDate(getDay(fieldBean.getData().get(i).getCreatedTime())) + ")");
                FieldCustomContentBean contentBean = new FieldCustomContentBean();
                contentBean.setAddress(fieldBean.getData().get(i).getLocation());
                contentBean.setContent(fieldBean.getData().get(i).getOutSideContent());
                contentBean.setPath(fieldBean.getData().get(i).getImagePage());
                contentBean.setHour(getHour(getAllDay(fieldBean.getData().get(i).getCreatedTime())));
                fieldCustomBean.setContentBean(contentBean);
                list13.add(fieldCustomBean);
            } else if ("14".equals(getDays(fieldBean.getData().get(i).getCreatedTime()))) {
                FieldCustomBean fieldCustomBean = new FieldCustomBean();
                fieldCustomBean.setTime(getDay(fieldBean.getData().get(i).getCreatedTime()) + "（" + TimeUtil.getWeekOfDate(getDay(fieldBean.getData().get(i).getCreatedTime())) + ")");
                FieldCustomContentBean contentBean = new FieldCustomContentBean();
                contentBean.setAddress(fieldBean.getData().get(i).getLocation());
                contentBean.setContent(fieldBean.getData().get(i).getOutSideContent());
                contentBean.setPath(fieldBean.getData().get(i).getImagePage());
                contentBean.setHour(getHour(getAllDay(fieldBean.getData().get(i).getCreatedTime())));
                fieldCustomBean.setContentBean(contentBean);
                list14.add(fieldCustomBean);
            } else if ("15".equals(getDays(fieldBean.getData().get(i).getCreatedTime()))) {
                FieldCustomBean fieldCustomBean = new FieldCustomBean();
                fieldCustomBean.setTime(getDay(fieldBean.getData().get(i).getCreatedTime()) + "（" + TimeUtil.getWeekOfDate(getDay(fieldBean.getData().get(i).getCreatedTime())) + ")");
                FieldCustomContentBean contentBean = new FieldCustomContentBean();
                contentBean.setAddress(fieldBean.getData().get(i).getLocation());
                contentBean.setContent(fieldBean.getData().get(i).getOutSideContent());
                contentBean.setPath(fieldBean.getData().get(i).getImagePage());
                contentBean.setHour(getHour(getAllDay(fieldBean.getData().get(i).getCreatedTime())));
                fieldCustomBean.setContentBean(contentBean);
                list15.add(fieldCustomBean);
            } else if ("16".equals(getDays(fieldBean.getData().get(i).getCreatedTime()))) {
                FieldCustomBean fieldCustomBean = new FieldCustomBean();
                fieldCustomBean.setTime(getDay(fieldBean.getData().get(i).getCreatedTime()) + "（" + TimeUtil.getWeekOfDate(getDay(fieldBean.getData().get(i).getCreatedTime())) + ")");
                FieldCustomContentBean contentBean = new FieldCustomContentBean();
                contentBean.setAddress(fieldBean.getData().get(i).getLocation());
                contentBean.setContent(fieldBean.getData().get(i).getOutSideContent());
                contentBean.setPath(fieldBean.getData().get(i).getImagePage());
                contentBean.setHour(getHour(getAllDay(fieldBean.getData().get(i).getCreatedTime())));
                fieldCustomBean.setContentBean(contentBean);
                list16.add(fieldCustomBean);
            } else if ("17".equals(getDays(fieldBean.getData().get(i).getCreatedTime()))) {
                FieldCustomBean fieldCustomBean = new FieldCustomBean();
                fieldCustomBean.setTime(getDay(fieldBean.getData().get(i).getCreatedTime()) + "（" + TimeUtil.getWeekOfDate(getDay(fieldBean.getData().get(i).getCreatedTime())) + ")");
                FieldCustomContentBean contentBean = new FieldCustomContentBean();
                contentBean.setAddress(fieldBean.getData().get(i).getLocation());
                contentBean.setContent(fieldBean.getData().get(i).getOutSideContent());
                contentBean.setPath(fieldBean.getData().get(i).getImagePage());
                contentBean.setHour(getHour(getAllDay(fieldBean.getData().get(i).getCreatedTime())));
                fieldCustomBean.setContentBean(contentBean);
                list17.add(fieldCustomBean);
            } else if ("18".equals(getDays(fieldBean.getData().get(i).getCreatedTime()))) {
                FieldCustomBean fieldCustomBean = new FieldCustomBean();
                fieldCustomBean.setTime(getDay(fieldBean.getData().get(i).getCreatedTime()) + "（" + TimeUtil.getWeekOfDate(getDay(fieldBean.getData().get(i).getCreatedTime())) + ")");
                FieldCustomContentBean contentBean = new FieldCustomContentBean();
                contentBean.setAddress(fieldBean.getData().get(i).getLocation());
                contentBean.setContent(fieldBean.getData().get(i).getOutSideContent());
                contentBean.setPath(fieldBean.getData().get(i).getImagePage());
                contentBean.setHour(getHour(getAllDay(fieldBean.getData().get(i).getCreatedTime())));
                fieldCustomBean.setContentBean(contentBean);
                list18.add(fieldCustomBean);
            } else if ("19".equals(getDays(fieldBean.getData().get(i).getCreatedTime()))) {
                FieldCustomBean fieldCustomBean = new FieldCustomBean();
                fieldCustomBean.setTime(getDay(fieldBean.getData().get(i).getCreatedTime()) + "（" + TimeUtil.getWeekOfDate(getDay(fieldBean.getData().get(i).getCreatedTime())) + ")");
                FieldCustomContentBean contentBean = new FieldCustomContentBean();
                contentBean.setAddress(fieldBean.getData().get(i).getLocation());
                contentBean.setContent(fieldBean.getData().get(i).getOutSideContent());
                contentBean.setPath(fieldBean.getData().get(i).getImagePage());
                contentBean.setHour(getHour(getAllDay(fieldBean.getData().get(i).getCreatedTime())));
                fieldCustomBean.setContentBean(contentBean);
                list19.add(fieldCustomBean);
            } else if ("20".equals(getDays(fieldBean.getData().get(i).getCreatedTime()))) {
                FieldCustomBean fieldCustomBean = new FieldCustomBean();
                fieldCustomBean.setTime(getDay(fieldBean.getData().get(i).getCreatedTime()) + "（" + TimeUtil.getWeekOfDate(getDay(fieldBean.getData().get(i).getCreatedTime())) + ")");
                FieldCustomContentBean contentBean = new FieldCustomContentBean();
                contentBean.setAddress(fieldBean.getData().get(i).getLocation());
                contentBean.setContent(fieldBean.getData().get(i).getOutSideContent());
                contentBean.setPath(fieldBean.getData().get(i).getImagePage());
                contentBean.setHour(getHour(getAllDay(fieldBean.getData().get(i).getCreatedTime())));
                fieldCustomBean.setContentBean(contentBean);
                list20.add(fieldCustomBean);
            } else if ("21".equals(getDays(fieldBean.getData().get(i).getCreatedTime()))) {
                FieldCustomBean fieldCustomBean = new FieldCustomBean();
                fieldCustomBean.setTime(getDay(fieldBean.getData().get(i).getCreatedTime()) + "（" + TimeUtil.getWeekOfDate(getDay(fieldBean.getData().get(i).getCreatedTime())) + ")");
                FieldCustomContentBean contentBean = new FieldCustomContentBean();
                contentBean.setAddress(fieldBean.getData().get(i).getLocation());
                contentBean.setContent(fieldBean.getData().get(i).getOutSideContent());
                contentBean.setPath(fieldBean.getData().get(i).getImagePage());
                contentBean.setHour(getHour(getAllDay(fieldBean.getData().get(i).getCreatedTime())));
                fieldCustomBean.setContentBean(contentBean);
                list21.add(fieldCustomBean);
            } else if ("22".equals(getDays(fieldBean.getData().get(i).getCreatedTime()))) {
                FieldCustomBean fieldCustomBean = new FieldCustomBean();
                fieldCustomBean.setTime(getDay(fieldBean.getData().get(i).getCreatedTime()) + "（" + TimeUtil.getWeekOfDate(getDay(fieldBean.getData().get(i).getCreatedTime())) + ")");
                FieldCustomContentBean contentBean = new FieldCustomContentBean();
                contentBean.setAddress(fieldBean.getData().get(i).getLocation());
                contentBean.setContent(fieldBean.getData().get(i).getOutSideContent());
                contentBean.setPath(fieldBean.getData().get(i).getImagePage());
                contentBean.setHour(getHour(getAllDay(fieldBean.getData().get(i).getCreatedTime())));
                fieldCustomBean.setContentBean(contentBean);
                list22.add(fieldCustomBean);
            } else if ("23".equals(getDays(fieldBean.getData().get(i).getCreatedTime()))) {
                FieldCustomBean fieldCustomBean = new FieldCustomBean();
                fieldCustomBean.setTime(getDay(fieldBean.getData().get(i).getCreatedTime()) + "（" + TimeUtil.getWeekOfDate(getDay(fieldBean.getData().get(i).getCreatedTime())) + ")");
                FieldCustomContentBean contentBean = new FieldCustomContentBean();
                contentBean.setAddress(fieldBean.getData().get(i).getLocation());
                contentBean.setContent(fieldBean.getData().get(i).getOutSideContent());
                contentBean.setPath(fieldBean.getData().get(i).getImagePage());
                contentBean.setHour(getHour(getAllDay(fieldBean.getData().get(i).getCreatedTime())));
                fieldCustomBean.setContentBean(contentBean);
                list23.add(fieldCustomBean);
            } else if ("24".equals(getDays(fieldBean.getData().get(i).getCreatedTime()))) {
                FieldCustomBean fieldCustomBean = new FieldCustomBean();
                fieldCustomBean.setTime(getDay(fieldBean.getData().get(i).getCreatedTime()) + "（" + TimeUtil.getWeekOfDate(getDay(fieldBean.getData().get(i).getCreatedTime())) + ")");
                FieldCustomContentBean contentBean = new FieldCustomContentBean();
                contentBean.setAddress(fieldBean.getData().get(i).getLocation());
                contentBean.setContent(fieldBean.getData().get(i).getOutSideContent());
                contentBean.setPath(fieldBean.getData().get(i).getImagePage());
                contentBean.setHour(getHour(getAllDay(fieldBean.getData().get(i).getCreatedTime())));
                fieldCustomBean.setContentBean(contentBean);
                list24.add(fieldCustomBean);
            } else if ("25".equals(getDays(fieldBean.getData().get(i).getCreatedTime()))) {
                FieldCustomBean fieldCustomBean = new FieldCustomBean();
                fieldCustomBean.setTime(getDay(fieldBean.getData().get(i).getCreatedTime()) + "（" + TimeUtil.getWeekOfDate(getDay(fieldBean.getData().get(i).getCreatedTime())) + ")");
                FieldCustomContentBean contentBean = new FieldCustomContentBean();
                contentBean.setAddress(fieldBean.getData().get(i).getLocation());
                contentBean.setContent(fieldBean.getData().get(i).getOutSideContent());
                contentBean.setPath(fieldBean.getData().get(i).getImagePage());
                contentBean.setHour(getHour(getAllDay(fieldBean.getData().get(i).getCreatedTime())));
                fieldCustomBean.setContentBean(contentBean);
                list25.add(fieldCustomBean);
            } else if ("26".equals(getDays(fieldBean.getData().get(i).getCreatedTime()))) {
                FieldCustomBean fieldCustomBean = new FieldCustomBean();
                fieldCustomBean.setTime(getDay(fieldBean.getData().get(i).getCreatedTime()) + "（" + TimeUtil.getWeekOfDate(getDay(fieldBean.getData().get(i).getCreatedTime())) + ")");
                FieldCustomContentBean contentBean = new FieldCustomContentBean();
                contentBean.setAddress(fieldBean.getData().get(i).getLocation());
                contentBean.setContent(fieldBean.getData().get(i).getOutSideContent());
                contentBean.setPath(fieldBean.getData().get(i).getImagePage());
                contentBean.setHour(getHour(getAllDay(fieldBean.getData().get(i).getCreatedTime())));
                fieldCustomBean.setContentBean(contentBean);
                list26.add(fieldCustomBean);
            } else if ("27".equals(getDays(fieldBean.getData().get(i).getCreatedTime()))) {
                FieldCustomBean fieldCustomBean = new FieldCustomBean();
                fieldCustomBean.setTime(getDay(fieldBean.getData().get(i).getCreatedTime()) + "（" + TimeUtil.getWeekOfDate(getDay(fieldBean.getData().get(i).getCreatedTime())) + ")");
                FieldCustomContentBean contentBean = new FieldCustomContentBean();
                contentBean.setAddress(fieldBean.getData().get(i).getLocation());
                contentBean.setContent(fieldBean.getData().get(i).getOutSideContent());
                contentBean.setPath(fieldBean.getData().get(i).getImagePage());
                contentBean.setHour(getHour(getAllDay(fieldBean.getData().get(i).getCreatedTime())));
                fieldCustomBean.setContentBean(contentBean);
                list27.add(fieldCustomBean);
            } else if ("28".equals(getDays(fieldBean.getData().get(i).getCreatedTime()))) {
                FieldCustomBean fieldCustomBean = new FieldCustomBean();
                fieldCustomBean.setTime(getDay(fieldBean.getData().get(i).getCreatedTime()) + "（" + TimeUtil.getWeekOfDate(getDay(fieldBean.getData().get(i).getCreatedTime())) + ")");
                FieldCustomContentBean contentBean = new FieldCustomContentBean();
                contentBean.setAddress(fieldBean.getData().get(i).getLocation());
                contentBean.setContent(fieldBean.getData().get(i).getOutSideContent());
                contentBean.setPath(fieldBean.getData().get(i).getImagePage());
                contentBean.setHour(getHour(getAllDay(fieldBean.getData().get(i).getCreatedTime())));
                fieldCustomBean.setContentBean(contentBean);
                list28.add(fieldCustomBean);
            } else if ("29".equals(getDays(fieldBean.getData().get(i).getCreatedTime()))) {
                FieldCustomBean fieldCustomBean = new FieldCustomBean();
                fieldCustomBean.setTime(getDay(fieldBean.getData().get(i).getCreatedTime()) + "（" + TimeUtil.getWeekOfDate(getDay(fieldBean.getData().get(i).getCreatedTime())) + ")");
                FieldCustomContentBean contentBean = new FieldCustomContentBean();
                contentBean.setAddress(fieldBean.getData().get(i).getLocation());
                contentBean.setContent(fieldBean.getData().get(i).getOutSideContent());
                contentBean.setPath(fieldBean.getData().get(i).getImagePage());
                contentBean.setHour(getHour(getAllDay(fieldBean.getData().get(i).getCreatedTime())));
                fieldCustomBean.setContentBean(contentBean);
                list29.add(fieldCustomBean);
            } else if ("30".equals(getDays(fieldBean.getData().get(i).getCreatedTime()))) {
                FieldCustomBean fieldCustomBean = new FieldCustomBean();
                fieldCustomBean.setTime(getDay(fieldBean.getData().get(i).getCreatedTime()) + "（" + TimeUtil.getWeekOfDate(getDay(fieldBean.getData().get(i).getCreatedTime())) + ")");
                FieldCustomContentBean contentBean = new FieldCustomContentBean();
                contentBean.setAddress(fieldBean.getData().get(i).getLocation());
                contentBean.setContent(fieldBean.getData().get(i).getOutSideContent());
                contentBean.setPath(fieldBean.getData().get(i).getImagePage());
                contentBean.setHour(getHour(getAllDay(fieldBean.getData().get(i).getCreatedTime())));
                fieldCustomBean.setContentBean(contentBean);
                list30.add(fieldCustomBean);
            } else if ("31".equals(getDays(fieldBean.getData().get(i).getCreatedTime()))) {
                FieldCustomBean fieldCustomBean = new FieldCustomBean();
                fieldCustomBean.setTime(getDay(fieldBean.getData().get(i).getCreatedTime()) + "（" + TimeUtil.getWeekOfDate(getDay(fieldBean.getData().get(i).getCreatedTime())) + ")");
                FieldCustomContentBean contentBean = new FieldCustomContentBean();
                contentBean.setAddress(fieldBean.getData().get(i).getLocation());
                contentBean.setContent(fieldBean.getData().get(i).getOutSideContent());
                contentBean.setPath(fieldBean.getData().get(i).getImagePage());
                contentBean.setHour(getHour(getAllDay(fieldBean.getData().get(i).getCreatedTime())));
                fieldCustomBean.setContentBean(contentBean);
                list31.add(fieldCustomBean);
            }
        }
    }

    /**
     * 获取当前的日子
     *
     * @param str
     * @return
     */
    private String getDays(String str) {
        String day = "";
        try {
            String date = TimeUtil.dealDateFormat(str);
            int m = date.indexOf(" ");
            String newdate = date.substring(0, m);
            int n = newdate.lastIndexOf("-");
            day = newdate.substring(n + 1, newdate.length());

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return day;
    }

    /**
     * 获取当前的年月日
     *
     * @param str
     * @return
     */
    private String getDay(String str) {
        String newdate = "";
        try {
            String date = TimeUtil.dealDateFormat(str);
            int m = date.indexOf(" ");
            newdate = date.substring(0, m);


        } catch (ParseException e) {
            e.printStackTrace();
        }
        return newdate;
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

    /**
     * 获取小时
     */
    private String getHour(String str) {
        int m = str.indexOf(":");
        return str.substring(m + 1, str.length());
    }

    /**
     * 获取孩子内容的链表
     *
     * @param lists
     * @return
     */
    private List<FieldCustomContentBean> getChild(List<FieldCustomBean> lists) {
        List<FieldCustomContentBean> list = new ArrayList<>();
        for (int i = 0; i < lists.size(); i++) {
            list.add(lists.get(i).getContentBean());
        }

        return list;
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
     * 清空数据
     */
    private void clear() {
        list1.clear();
        list2.clear();
        list3.clear();
        list4.clear();
        list5.clear();
        list6.clear();
        list7.clear();
        list8.clear();
        list9.clear();
        list10.clear();
        list11.clear();
        list12.clear();
        list13.clear();
        list14.clear();
        list15.clear();
        list16.clear();
        list17.clear();
        list18.clear();
        list19.clear();
        list20.clear();
        list21.clear();
        list22.clear();
        list23.clear();
        list24.clear();
        list25.clear();
        list26.clear();
        list27.clear();
        list28.clear();
        list29.clear();
        list30.clear();
        list31.clear();
        ly_all.removeAllViews();

    }
}
