package ercs.com.ercshouseresources.activity;

/**
 * Created by Administrator on 2017/9/13.
 */

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import ercs.com.ercshouseresources.R;

public class Pao extends AppCompatActivity {

    private LinearLayout line;
    private HorizontalScrollView scroll;
    private ArrayList<String> list = new ArrayList<>();
    private MyHandler roolHandler;
    private Timer timer;
    private MyTask task;
    private static final int SPEED = 30;
    private int moveSpeed = 2;
    private int moveSum = 0;
    private int lineWidth = 0;
    private int lineHeight = 0;
    private int moveEnd = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.roll_layout);
        setDate();
        findViewById();
        showRollNews();
    }

    private void setDate() {
        list.add("信息1信息1信息1信息1信息1信息1");
        list.add("信息2信息2信息2信息2信息2信息2");
        list.add("信息3信息3信息3信息3信息3信息3");
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
            TextView textView = new TextView(this);
            int poi = i % listsize;
            if (poi >= listsize) {
                return;
            }
            final String title = list.get(poi);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(80, 0, 0, 0);
            textView.setId(i);
            textView.setTextSize(17);
            textView.setTextColor(getResources().getColor(R.color.system_color));
            textView.setText(title);
            line.addView(textView, i, params);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(Pao.this, "点击了:" + title,
                            Toast.LENGTH_SHORT).show();
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
