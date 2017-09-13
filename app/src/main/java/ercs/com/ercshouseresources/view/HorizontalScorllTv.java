package ercs.com.ercshouseresources.view;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.activity.GDynamicDetailActivity;
import ercs.com.ercshouseresources.activity.Pao;
import ercs.com.ercshouseresources.adapter.ScroAdapter;

/**
 * Created by Administrator on 2017/9/13.
 */

public class HorizontalScorllTv extends RelativeLayout {
    private LinearLayout linearLayout;
    private LinearLayout line;
    private HorizontalScrollView scroll;
    private ArrayList<String> list = new ArrayList<>();
    private MyHandler roolHandler;
    private Timer timer;
    private MyTask task;
    private static final int SPEED = 10;
    private int moveSpeed = 2;
    private int moveSum = 0;
    private int lineWidth = 0;
    private int lineHeight = 0;
    private int moveEnd = 0;
    private Activity context;
    List<String> listids;

    public HorizontalScorllTv(Activity context, List<String> lists, List<String> listid) {
        super(context);
        this.context = context;
        LayoutInflater mInflater = LayoutInflater.from(context);
        linearLayout = (LinearLayout) mInflater.inflate(
                R.layout.roll_layout, null);
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 0, 0, 0);
        this.addView(linearLayout, params);
        list.addAll(lists);
        listids = listid;
        findViewById();
        showRollNews();


    }


    private void findViewById() {
        line = (LinearLayout) findViewById(R.id.conver);
        scroll = (HorizontalScrollView) findViewById(R.id.scroll);
        scroll.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                return true;
            }
        });
    }

    private void showRollNews() {
        setView(line);
        if (roolHandler == null) {
            roolHandler = new MyHandler();
        }
        line.measure(line.getMeasuredWidth(), line.getMeasuredHeight());
        lineWidth = line.getMeasuredWidth();
        lineHeight = line.getMeasuredHeight();
        moveEnd = -(lineWidth / 2);
        stopTimer();
        runRoll();
    }

    public void setView(LinearLayout line) {
        line.removeAllViews();
        int listsize = list.size();
        for (int i = 0; i < listsize * 2; i++) {
            TextView textView = new TextView(context);
            final int poi = i % listsize;
            if (poi >= listsize) {
                return;
            }
            final String title = list.get(poi);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(80, 0, 0, 0);
            textView.setId(i);
            textView.setTextSize(15);
            textView.setTextColor(getResources().getColor(R.color.clor_fontgray));
            textView.setText(title);
            line.addView(textView, i, params);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    GDynamicDetailActivity.start(context, listids.get(poi));
                }
            });
        }
    }

    public void runRoll() {
        if (timer == null) {
            timer = new Timer();
        }
        if (task == null) {
            task = new MyTask();
            if (roolHandler != null) {
                timer.schedule(task, 0, SPEED);
            }
        }
    }

    private void stopTimer() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        if (task != null) {
            task.cancel();
            task = null;
        }
    }

    private class MyTask extends TimerTask {

        @Override
        public void run() {
            moveSum -= moveSpeed;
            if (moveSum < moveEnd) {
                moveSum = 0;
            } else {
                roolHandler.sendEmptyMessage(1);
            }
        }
    }


    private class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            line.layout(moveSum, 0, moveSum + lineWidth, lineHeight);
        }
    }

}
