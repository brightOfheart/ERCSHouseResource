package ercs.com.ercshouseresources.view.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;

import com.zyao89.view.zloading.ZLoadingDialog;
import com.zyao89.view.zloading.Z_TYPE;

import ercs.com.ercshouseresources.R;

/**
 * Created by Administrator on 2017/6/28.
 * 网络加载的dialog
 */

public class LoadingDialog extends ZLoadingDialog {
    public LoadingDialog(@NonNull Context context, int kind) {
        super(context);
        if (kind == 0)
            init(context);
        else
            initcancel(context);
    }

    /**
     * 设置不可以取消的对话框
     *
     * @param context
     */
    private void init(Context context) {
        this.setLoadingBuilder(Z_TYPE.LEAF_ROTATE)//设置类型
                .setLoadingColor(ContextCompat.getColor(context, R.color.system_color))//颜色
                .setHintText(context.getResources().getString(R.string.str_loading)).setCanceledOnTouchOutside(false).setCancelable(false);
    }

    /**
     * 设置可以取消的加载对话框
     *
     * @param context
     */
    private void initcancel(Context context) {
        this.setLoadingBuilder(Z_TYPE.LEAF_ROTATE)//设置类型
                .setLoadingColor(ContextCompat.getColor(context, R.color.system_color))//颜色
                .setHintText(context.getResources().getString(R.string.str_loading));
    }
}
