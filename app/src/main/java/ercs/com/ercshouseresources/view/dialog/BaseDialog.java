package ercs.com.ercshouseresources.view.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import ercs.com.ercshouseresources.R;

/**
 * Created by Administrator on 2017/6/22.
 * 基类的dialog
 */

public abstract class BaseDialog extends Dialog {

    public Activity mActivity;
    public BaseDialog(Activity mActivity) {
        super(mActivity, R.style.dialogs);
        this.mActivity = mActivity;
    }

    public BaseDialog(Activity mActivity, int style) {
        super(mActivity, style);
        this.mActivity = mActivity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = getLayoutInflater().inflate(getLayoutRes(), null);
        setContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        Window window = getWindow();
        // 设置显示动画
        if (0!=getAnimStyle()){
            window.setWindowAnimations(getAnimStyle());
        }
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.x = 0;
        wl.y = mActivity.getWindowManager().getDefaultDisplay().getHeight();
        // 以下这两句是为了可以水平满屏
        wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
        wl.height = ViewGroup.LayoutParams.MATCH_PARENT;

        // 设置显示位置
        onWindowAttributesChanged(wl);
        initView();
    }

    public void onDestory(){
        if (isShowing()){
            dismiss();
        }
    }


    public abstract int getAnimStyle();
    public abstract int getLayoutRes();
    public abstract void initView();
}
