package ercs.com.ercshouseresources.activity;
import android.os.Bundle;
import android.util.Log;
import java.util.Timer;
import java.util.TimerTask;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.view.OvalTimeView;


/**
 * Created by Administrator on 2017/9/20.
 */

public class Text extends BaseActivity {
    private OvalTimeView rpb;
    private int time = 6;
    private TimerTask timerTask;
    private Timer timer = new Timer();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text);
        rpb = (OvalTimeView) findViewById(R.id.rpb);
        rpb.setMax(time);
        rpb.setProgress(time);
        timerTask = new MyTimerTask();
        timer.schedule(timerTask, 1000, 1000);
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
        Log.d("xxxx","====");

    }
}
