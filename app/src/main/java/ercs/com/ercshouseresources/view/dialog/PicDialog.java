package ercs.com.ercshouseresources.view.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.activity.GDynamicDetailActivity;
import ercs.com.ercshouseresources.network.HttpUtils;
import ercs.com.ercshouseresources.network.NetHelperNew;
import ercs.com.ercshouseresources.util.CloseActivityClass;
import ercs.com.ercshouseresources.util.DisplayUtil;
import ercs.com.ercshouseresources.util.imageUtil.GlideUtil;
import ercs.com.ercshouseresources.view.CountDownProgress;

/**
 * Created by Administrator on 2017/9/13.
 */

public class PicDialog extends Dialog {
    private ImageView iv;
    private Activity context;
    private CountDownProgress countdownpro;
    private LinearLayout ly_dis;
    private String string;
    private String id;
    public PicDialog(Activity context, int theme, String path, String ids) {
        super(context, theme);
        this.context = context;
        this.string = path;
        id=ids;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.dialog_pics, null);
        setContentView(view, new ViewGroup.LayoutParams(DisplayUtil.getWidthPixels(context),
                ViewGroup.LayoutParams.MATCH_PARENT));
        iv = (ImageView) view.findViewById(R.id.iv);
        GlideUtil.loadImage(context, NetHelperNew.URL + string, iv, R.mipmap.ic_launcher, R.mipmap.ic_launcher);
        countdownpro = (CountDownProgress) view.findViewById(R.id.countdownpro);
        ly_dis = (LinearLayout) view.findViewById(R.id.ly_dis);
        countdownpro.setCountdownTime(10 * 600);
        ly_dis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();

            }
        });
        countdownpro.startCountDownTime(new CountDownProgress.OnCountdownFinishListener() {
            @Override
            public void countdownFinished() {
                dismiss();
            }
        });
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GDynamicDetailActivity.start(context,id);
            }
        });
        this.setCanceledOnTouchOutside(false);//点击屏幕不消失


    }


}
