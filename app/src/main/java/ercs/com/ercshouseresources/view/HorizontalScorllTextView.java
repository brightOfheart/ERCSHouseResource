package ercs.com.ercshouseresources.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.TextView;

import ercs.com.ercshouseresources.R;

/**
 * Created by Administrator on 2017/9/13.
 */

public class HorizontalScorllTextView extends HorizontalScrollView implements Runnable {

    int currentScrollX = 0;// 当前滚动的位置
    TextView tv;

    public HorizontalScorllTextView(Context context) {
        super(context);
        initView(context);
    }

    public HorizontalScorllTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public HorizontalScorllTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context);
    }

    void initView(Context context) {
        View v = LayoutInflater.from(context).inflate(R.layout.scroll_layout, null);
        tv = (TextView) v.findViewById(R.id.tv);
        this.addView(v);
        this.setVerticalScrollBarEnabled(false);
        this.setHorizontalScrollBarEnabled(false);
    }

    public void setText(String text) {
        tv.setText(text);
        startScroll();
    }

    private void startScroll() {
        this.removeCallbacks(this);
        post(this);
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        currentScrollX = currentScrollX + 8;// 滚动速度
        scrollTo(currentScrollX, 0);

        if (currentScrollX >= tv.getWidth()) {
            scrollTo(0, 0);
            currentScrollX = 0;
        }
        postDelayed(this, 50);
    }
}
