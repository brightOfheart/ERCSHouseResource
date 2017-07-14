package ercs.com.ercshouseresources.activity.process;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bigkoo.pickerview.TimePickerView;
import java.text.SimpleDateFormat;
import java.util.Date;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.activity.BaseActivity;
import ercs.com.ercshouseresources.base.BaseApplication;
import ercs.com.ercshouseresources.bean.ProcessApplicationBean;
import ercs.com.ercshouseresources.network.HttpUtils;
import ercs.com.ercshouseresources.network.MyGson;
import ercs.com.ercshouseresources.network.NetHelper;
import ercs.com.ercshouseresources.util.NetWorkUtil;
import ercs.com.ercshouseresources.util.SPUtil;
import ercs.com.ercshouseresources.util.TitleControl;
import ercs.com.ercshouseresources.util.ToastUtil;
import ercs.com.ercshouseresources.view.dialog.LoadingDialog;
import ercs.com.ercshouseresources.view.dialog.TypeSelectDialog;

/**
 * Created by Administrator on 2017/7/6.
 * 流程申请
 */

public class ProcessApplyAcvitity extends BaseActivity {
    private LoadingDialog dialog;
    @BindView(R.id.tv_protype)
    TextView tv_protype;//流程类型
    @BindView(R.id.tv_retroactivedata)
    TextView tv_retroactivedata;//补签日期
    @BindView(R.id.tv_processtypes)
    TextView tv_processtypes;//补签类型
    @BindView(R.id.edit_reason)
    EditText edit_reason;//输入事由的文本框
    @BindView(R.id.iv_photo)
    ImageView iv_photo;//选择图片
    @BindView(R.id.gridview)
    GridView gridview;
    @BindView(R.id.ll_rest)
    LinearLayout ll_rest;//休息，外出
     @BindView(R.id.ll_retroactive)
    LinearLayout ll_retroactive;//补签
    @BindView(R.id.tv_starttime)
    TextView tv_starttime;//开始时间
    @BindView(R.id.tv_endtime)
    TextView tv_endtime;//结束时间
     @BindView(R.id.tv_timelong)
    TextView tv_timelong;//时长
    private TimePickerView pvTime,pvTimeDay;//年月时分时间选择器，年月日时间选择器
    private int n=1;//1开始时间 2结束时间 3补签日期
    private Long startLong=0L;//开始时间
    private Long endlong=0L;// 结束时间
    private  SimpleDateFormat sd,ymd;

    private int protype=0;//流程类型 0 未选择 1休息 2 外出 3补签
    private int retroactivetype=0;//补签类型 0 未选择 1上班 2 下班 4上班和下班


    private  long hours;//间隔时间

    private SPUtil spUtil;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_processapply);
        ButterKnife.bind(this);
        initTitle();
        //年月日时分
        initPvTime();
        //年月日
        initPvTimeYear();

    }

    /**
     * 年月时分时间选择器
     */
    private void initPvTime() {
         sd = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        //时间选择器
        pvTime = new TimePickerView(this, TimePickerView.Type.ALL);
        //设置范围
        pvTime.setRange(2017, 2030);
        //设置选择时间
        pvTime.setTime(new Date());
        //设置是否循环滚动
        pvTime.setCyclic(false);
        pvTime.setCancelable(true);

        //时间选择后回调
        pvTime.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {

            @Override
            public void onTimeSelect(Date date) {
                if (n == 1) {

                    //开始时间
                    tv_starttime.setText(sd.format(date));
                    startLong=date.getTime();
                    calculationTime();
                } else if (n==2){
                    //结束时间
                    tv_endtime.setText(sd.format(date));
                    endlong=date.getTime();
                    calculationTime();
                }

            }


        });
    }

    /**
     * 年月日时间选择器
     */
    private void initPvTimeYear() {
         ymd = new SimpleDateFormat("yyyy-MM-dd");
        //时间选择器
        pvTimeDay = new TimePickerView(this, TimePickerView.Type.YEAR_MONTH_DAY);
        //设置范围
        pvTimeDay.setRange(2017, 2030);
        //设置选择时间
        pvTimeDay.setTime(new Date());
        //设置是否循环滚动
        pvTimeDay.setCyclic(false);
        pvTimeDay.setCancelable(true);

        //时间选择后回调
        pvTimeDay.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {

            @Override
            public void onTimeSelect(Date date) {
                tv_retroactivedata.setText(ymd.format(date));

            }


        });
    }
    /**
     * 计算时长
     */
    private void calculationTime() {
        if (0!=startLong&&0!=endlong)
        {
            Long timelong= endlong-startLong;


             hours =timelong/(1000* 60 * 60); //换算成小时

//
//            long days = times/ (1000 * 60* 60 * 24); //换算成天数
//
//            long hours =(times-days*(1000 * 60 * 60 * 24))/(1000* 60 * 60); //换算成小时
//
//            long minutes =(times-days*(1000 * 60 * 60 * 24)-hours*(1000* 60 * 60))/(1000* 60); //换算成分钟
            tv_timelong.setText(hours+"小时");
        }
    }
    /**
     * 设置标题栏
     */
    private void initTitle() {
        TitleControl t = new TitleControl(this);
        t.setTitle(getString(R.string.str_processpay));
        dialog = new LoadingDialog(ProcessApplyAcvitity.this, 0);
        if (spUtil == null)
            spUtil = new SPUtil(this, "fileName");
    }

    /**
     * 点击事件的处理
     *
     * @param view
     */
    @OnClick({R.id.tv_protype, R.id.tv_retroactivedata, R.id.tv_processtypes, R.id.btn_post,R.id.tv_starttime,R.id.tv_endtime})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_protype://流程类型
                 TypeSelectDialog typeSelectDialog = new TypeSelectDialog(ProcessApplyAcvitity.this, new TypeSelectDialog.OnTypeSelectListener() {
                    @Override
                    public void getTypeSelect(int m) {

                        protype=m;
                        switch (m) {
                            case 1:
                                //休息
                                tv_protype.setText("休息");
                                ll_retroactive.setVisibility(View.GONE);
                                ll_rest.setVisibility(View.VISIBLE);

                                break;
                            case 2:
                                tv_protype.setText("外出");
                                ll_retroactive.setVisibility(View.GONE);
                                ll_rest.setVisibility(View.VISIBLE);
                                //外出
                                break;
                            case 3:
                                tv_protype.setText("补签");
                                ll_retroactive.setVisibility(View.VISIBLE);
                                ll_rest.setVisibility(View.GONE);
                                //补签
                                break;
                        }
                    }
                },"休息","外出","补签");
                typeSelectDialog.show();
                break;
            case R.id.tv_retroactivedata://补签日期
                n=3;
                pvTimeDay.show();
                break;
            case R.id.tv_processtypes://补签班次
                TypeSelectDialog typeSelectDialog2 = new TypeSelectDialog(ProcessApplyAcvitity.this, new TypeSelectDialog.OnTypeSelectListener() {
                    @Override
                    public void getTypeSelect(int m) {
                        switch (m) {
                            case 1:
                                //上班
                                tv_processtypes.setText("上班");
                                retroactivetype=1;

                                break;
                            case 2:
                                tv_processtypes.setText("下班");
                                retroactivetype=3;
                                //下班
                                break;
                            case 3:
                                tv_processtypes.setText("上班和下班");
                                retroactivetype=4;
                                //补签
                                break;
                        }
                    }
                },"上班","下班","上班和下班");
                typeSelectDialog2.show();
                break;
            case R.id.btn_post://提交

                if (check())
                submitApply(protype);
                break;
            case R.id.tv_starttime://开始时间
                n=1;
                pvTime.show();
                break;
            case R.id.tv_endtime://结束时间
                n=2;
                pvTime.show();
                break;
        }
    }

    /**
     * 提交的检测
     *
     * @return
     */
    private boolean check() {

        if (protype==0)
        {
            ToastUtil.showToast(getApplicationContext(), getString(R.string.error_tyve));
            return false;
        }
        if ("".equals(edit_reason.getText().toString()))
        {
            ToastUtil.showToast(getApplicationContext(), getString(R.string.error_reson));
            return false;
        }

        if (!NetWorkUtil.checkNet(getApplicationContext())) {
            ToastUtil.showToast(getApplicationContext(), getString(R.string.error_net));
            return false;
        }
        return true;
    }

    /**
     * 获取id
     * @return
     */
    private String getId()
    {
        return spUtil.getString(BaseApplication.ID,"");
    }
    /**
     * 提交数据
     * 休息1 外出3
     * 申请1 同意2 驳回3
     * 上班1 下班3 上班和下班4
     * @param protype
     */
    private void submitApply(int protype) {
        switch (protype)
        {
            case 1:
                //休息
                if (getString(R.string.str_pleasechose).equals(tv_starttime.getText().toString()))
                {
                    ToastUtil.showToast(getApplicationContext(), getString(R.string.error_starttime));
                }else if (getString(R.string.str_pleasechose).equals(tv_endtime.getText().toString()))
                {
                    ToastUtil.showToast(getApplicationContext(), getString(R.string.error_endtime));
                }else {
                    dialog.show();
                    NetHelper.processApplyOutside(getId(), tv_starttime.getText().toString(), tv_endtime.getText().toString(), hours + "", "1", "1", edit_reason.getText().toString(), new HttpUtils.HttpCallback() {
                        @Override
                        public void onSuccess(String data) {
                            dialog.dismiss();


                            final ProcessApplicationBean processApplicationBean = MyGson.getInstance().fromJson(data, ProcessApplicationBean.class);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    ToastUtil.showToast(getApplicationContext(), processApplicationBean.getContent());

                                    finish();
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
                break;

            case 2:
                //外出


                Log.i("-->","请求参数："+tv_starttime.getText().toString()+" 结束时间："+tv_endtime.getText().toString()+" 当前时间："+hours+" 输入内容："+edit_reason.getText().toString());

                if (getString(R.string.str_pleasechose).equals(tv_starttime.getText().toString()))
                {
                    ToastUtil.showToast(getApplicationContext(), getString(R.string.error_starttime));
                }else if (getString(R.string.str_pleasechose).equals(tv_endtime.getText().toString()))
                {
                    ToastUtil.showToast(getApplicationContext(), getString(R.string.error_endtime));
                }else
                {
                    dialog.show();
                    NetHelper.processApplyOutside(getId(), tv_starttime.getText().toString(), tv_endtime.getText().toString(),  hours+"", "3", "1", edit_reason.getText().toString(), new HttpUtils.HttpCallback() {
                        @Override
                        public void onSuccess(String data) {
                            dialog.dismiss();


                            final ProcessApplicationBean processApplicationBean = MyGson.getInstance().fromJson(data, ProcessApplicationBean.class);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    ToastUtil.showToast(getApplicationContext(), processApplicationBean.getContent());

                                    finish();
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

                break;
            case 3:
                //补签
                if (getString(R.string.str_pleasechose).equals(tv_retroactivedata.getText().toString()))
                {
                    ToastUtil.showToast(getApplicationContext(), getString(R.string.error_retroactivedata));
                }else if (getString(R.string.str_retroactivecount).equals(tv_processtypes.getText().toString()))
                {
                    ToastUtil.showToast(getApplicationContext(), getString(R.string.error_retroactivetype));
                }else {
                    dialog.show();
                    NetHelper.processRetroactive(getId(), retroactivetype+"", tv_retroactivedata.getText().toString(), "1", edit_reason.getText().toString(), tv_retroactivedata.getText().toString(), new HttpUtils.HttpCallback() {
                        @Override
                        public void onSuccess(String data) {
                            dialog.dismiss();

                            final ProcessApplicationBean processApplicationBean = MyGson.getInstance().fromJson(data, ProcessApplicationBean.class);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    ToastUtil.showToast(getApplicationContext(), processApplicationBean.getContent());

                                    finish();
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
                break;
        }
    }



}
