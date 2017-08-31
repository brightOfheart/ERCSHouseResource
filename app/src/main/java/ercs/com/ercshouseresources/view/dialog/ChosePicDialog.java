package ercs.com.ercshouseresources.view.dialog;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import ercs.com.ercshouseresources.R;

/**
 * Created by Administrator on 2017/8/28.
 */

public class ChosePicDialog extends BaseDialog {

    private TextView tv_xj;
    private TextView tv_xc;
    private TextView tv_cancel;
    private OnClickLister listener;


    public ChosePicDialog(Activity mActivity, OnClickLister listener) {
        super(mActivity);
        this.listener = listener;
    }


    @Override
    public int getAnimStyle() {
        return 0;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.dialog_pic;
    }

    @Override
    public void initView() {
        tv_xj = (TextView) findViewById(R.id.tv_xj);
        tv_xc = (TextView) findViewById(R.id.tv_xc);
        tv_cancel = (TextView) findViewById(R.id.tv_cancel);
        tv_xj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                listener.camera();
            }
        });

        tv_xc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                listener.pic();
            }
        });
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    public interface OnClickLister {
        void camera();

        void pic();

    }
}
