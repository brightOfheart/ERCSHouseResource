package ercs.com.ercshouseresources.view.dialog;
import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import java.util.Timer;
import java.util.TimerTask;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.activity.GDynamicDetailActivity;
import ercs.com.ercshouseresources.network.NetHelperNew;
import ercs.com.ercshouseresources.util.imageUtil.GlideUtil;
import ercs.com.ercshouseresources.view.OvalTimeView;

/**
 * Created by Administrator on 2017/9/13.
 */

public class PicDialog extends Dialog {
    private ImageView iv;
    private Activity context;
    private OvalTimeView rpb;
    private int time = 6;
    private TimerTask timerTask;
    private Timer timer = new Timer();
    private LinearLayout ly_dis;
    private String string;
    private String id;

    public PicDialog(Activity context, int theme, String path, String ids) {
        super(context, theme);
        this.context = context;
        this.string = path;
        id = ids;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.dialog_pics, null);
        setContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        iv = (ImageView) view.findViewById(R.id.iv);
        GlideUtil.loadImage(context, NetHelperNew.URL + string, iv, R.mipmap.ic_launcher, R.mipmap.ic_launcher);
        ly_dis = (LinearLayout) view.findViewById(R.id.ly_dis);
        rpb = (OvalTimeView) view.findViewById(R.id.rpb);
        rpb.setMax(time);
        rpb.setProgress(time);
        timerTask = new MyTimerTask();
        timer.schedule(timerTask, 1000, 1000);
        ly_dis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();

            }
        });

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GDynamicDetailActivity.start(context, id);
            }
        });
        this.setCanceledOnTouchOutside(false);//点击屏幕不消失


    }

    private class MyTimerTask extends TimerTask {
        @Override
        public void run() {
            rpb.setProgress(time--);
            if (time == -1) {
                //倒计时为0的时候做自己需要处理的业务逻辑
                cancelRestTimerTask();
            }
        }
    }

    /**
     * 取消RestTimerTask
     */
    private void cancelRestTimerTask() {
        if (timerTask != null) {
            timerTask.cancel();
        }
        dismiss();

    }
}
