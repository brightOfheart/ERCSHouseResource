package ercs.com.ercshouseresources.activity.scheduling;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.activity.BaseActivity;
import ercs.com.ercshouseresources.adapter.SchedulingAdapter;
import ercs.com.ercshouseresources.adapter.SchedulingRightAdapter;
import ercs.com.ercshouseresources.bean.SchedulingBean;
import ercs.com.ercshouseresources.network.HttpUtils;
import ercs.com.ercshouseresources.network.MyGson;
import ercs.com.ercshouseresources.network.NetHelper;
import ercs.com.ercshouseresources.util.NetWorkUtil;
import ercs.com.ercshouseresources.util.TitleControl;
import ercs.com.ercshouseresources.util.ToastUtil;
import ercs.com.ercshouseresources.view.dialog.CustomerDatePickerDialog;
import ercs.com.ercshouseresources.view.dialog.LoadingDialog;

/**
 * Created by Administrator on 2017/6/26.
 * 排班情况
 */

public class SchedulingActivity extends BaseActivity {
    @BindView(R.id.listview)
    ListView listview;
    @BindView(R.id.list_right)
    ListView list_right;
    @BindView(R.id.edit_content)
    EditText edit_content;
    private TitleControl t;
    private LoadingDialog dialog;
    private SchedulingRightAdapter schedulingRightAdapter = null;
    private List<String> list = new ArrayList<>();
    private List<SchedulingBean.DataBean> listData = new ArrayList<>();
    private String nowdate = "";//显示当前选择的日期

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheduling);
        ButterKnife.bind(this);
        initTitle();
        createview();
        if (NetWorkUtil.check(getApplicationContext()))
            nowdate = getYear() + "-" + getMonth() + "-" + getDay();
        gatNetData(getYear() + "-" + getMonth() + "-" + getDay(), "");
    }

    /**
     * 点击事件的处理
     *
     * @param view
     */
    @OnClick({R.id.ly_chose, R.id.iv_cancel})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ly_chose://选择年月
                new CustomerDatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        if (NetWorkUtil.check(getApplicationContext())) {
                            t.setTitle(year + "年" + (month + 1) + "月排班管理");
                            gatNetData(year + "-" + (month + 1) + "-" + day, "");
                            nowdate = year + "-" + (month + 1) + "-" + day;
                        }
                    }
                }, getYear(), getMonth(), getDay());
                break;
            case R.id.iv_cancel://清空输入框
                edit_content.setText("");
                break;

        }
    }

    /**
     * 初始化
     */
    private void createview() {
        list_right.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                listview.smoothScrollToPositionFromTop(position, 0, 100);
            }
        });
        edit_content.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH) {
                    if (edit_content.getText().toString().equals("")) {
                        ToastUtil.showToast(getApplicationContext(), getResources().getString(R.string.error_name));
                    } else {
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(edit_content.getWindowToken(), 0);
                        if (NetWorkUtil.check(getApplicationContext()))
                            gatNetData(nowdate, edit_content.getText().toString());
                    }

                }
                return false;
            }
        });
    }

    /**
     * 设置标题栏
     */
    private void initTitle() {
        if (t == null)
            t = new TitleControl(this);
        t.setTitle(getYear() + "年" + getMonth() + "月排班管理");
        dialog = new LoadingDialog(SchedulingActivity.this, 0);
    }

    /**
     * 获取网络数据
     */
    private void gatNetData(String date, final String name) {
        dialog.show();
        NetHelper.scheduling(date, new HttpUtils.HttpCallback() {
            @Override
            public void onSuccess(String data) {
                final SchedulingBean schedulingBean = MyGson.getInstance().fromJson(data, SchedulingBean.class);
                if (schedulingBean.getType().equals("1")) {
                    setData(schedulingBean.getData(), name);
                    setTimeData(schedulingBean.getData(), name);
                }
                dialog.dismiss();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.showToast(getApplicationContext(), schedulingBean.getContent());
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
     * 如果访问网络成功，则设置网络数据
     */
    private void setData(List<SchedulingBean.DataBean> listData, String name) {
        this.listData.clear();
        if (name.equals(""))
            this.listData = listData;
        else {
            for (int i = 0; i < listData.size(); i++) {

                for (int j = 0; j < listData.get(i).getUData().size(); j++) {
                    if (listData.get(i).getUData().get(j).getUserName().equals(name)) {
                        this.listData.add(listData.get(i));
                        break;
                    }
                }


            }
        }

        listview.setAdapter(new SchedulingAdapter(getApplicationContext(), this.listData));


    }

    /**
     * 设置有排班的日期
     *
     * @param listData
     * @return
     */
    private void setTimeData(List<SchedulingBean.DataBean> listData, String name) {
        list.clear();
        for (int i = 0; i < listData.size(); i++) {
            if (name.equals("")) {
                list.add(listData.get(i).getDate().substring(8, 10));
            } else {
                for (int j = 0; j < listData.get(i).getUData().size(); j++) {
                    if (listData.get(i).getUData().get(j).getUserName().equals(name)) {
                        list.add(listData.get(i).getDate().substring(8, 10));
                        break;
                    }
                }
            }

        }

        list_right.setAdapter(new SchedulingRightAdapter(getApplicationContext(), list));


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

}
