package ercs.com.ercshouseresources.receiver;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.WindowManager;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.base.BaseApplication;
import ercs.com.ercshouseresources.util.SPUtil;
import ercs.com.ercshouseresources.view.dialog.CheckDialog;
/**
 * Created by Administrator on 2017/8/4.
 * 监听是否重复登录的广播
 */

public class CheckReceiver extends BroadcastReceiver {
    private CheckDialog dialog;
    private SPUtil spUtil;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (spUtil == null)
            spUtil = new SPUtil(context, "fileName");
        if (intent.getAction().equals("401")) {
            saveLogin(0);
            if (dialog == null)
                dialog = new CheckDialog(BaseApplication.context, R.style.dialog);
            dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_TOAST);

            if (!dialog.isShowing()) {//此时提示框未显示
                dialog.show();
            }

        }
    }

    private void saveLogin(int count) {
        spUtil.putInt(BaseApplication.ISLOGIN, count);
    }
}
