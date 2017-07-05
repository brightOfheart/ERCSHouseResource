package ercs.com.ercshouseresources.view.dialog;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import ercs.com.ercshouseresources.R;

/**
 * Created by Administrator on 2017/6/22.
 * 底部弹出的dialog
 */

public class BottomDialog extends BaseDialog {

    private TextView tv_name;
    private TextView tv_callphone;
    private TextView tv_selectattendance;
    private TextView tv_selectfield;
    private TextView tv_cancel;
    private OnSelectListener listener;
    private String name="";

    public BottomDialog(Activity mActivity, OnSelectListener listener,String name ) {
        super(mActivity);
        this.listener = listener;
        this.name=name;

    }


    @Override
    public int getAnimStyle() {
        return 0;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.dialog_bottom;
    }

    @Override
    public void initView() {
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_callphone = (TextView) findViewById(R.id.tv_callphone);
        tv_selectattendance = (TextView) findViewById(R.id.tv_selectattendance);
        tv_selectfield = (TextView) findViewById(R.id.tv_selectfield);
        tv_cancel= (TextView) findViewById(R.id.tv_cancel);
        tv_name.setText(name);
        tv_callphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                listener.callphone();
            }
        });

        tv_selectattendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                listener.selectattendance();
            }
        });
        tv_selectfield.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                listener.selectfield();
            }
        });
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    public interface OnSelectListener {
        void callphone();//拨打电话
        void selectattendance();//查看考勤
        void selectfield();//查看外勤
    }
}