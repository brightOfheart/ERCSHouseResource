package ercs.com.ercshouseresources.util;
import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import ercs.com.ercshouseresources.R;
/**
 * Created by Administrator on 2017/5/8.
 * 标题栏的工具类
 */

public class TitleControl {
    private Activity mActivity;
    private ImageView left;
    private ImageView right_iv;
    private TextView center;
    private TextView right;
    private ImageView right_img;

    public TitleControl(Activity mActivity) {
        this.mActivity = mActivity;
        initLayout();
    }

    private void initLayout() {
        left = (ImageView) mActivity.findViewById(R.id.title_left);
        center = (TextView) mActivity.findViewById(R.id.title_center);
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.finish();
            }
        });
    }

    public void setTitle(String title) {
        center.setText(title);
    }

    public void setRightText(String s, final OnClickRight clickRight) {
        right = (TextView) mActivity.findViewById(R.id.title_right);
        right.setVisibility(View.VISIBLE);
        right.setText(s);
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickRight.onRight();
            }
        });
    }

    public void setRightImg(int res, final OnClickRight clickRight) {
        right_img = (ImageView) mActivity.findViewById(R.id.title_right_img);
        right_img.setBackgroundResource(res);
        right_img.setVisibility(View.VISIBLE);
        right_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickRight.onRight();
            }
        });
    }

    public interface OnClickRight {
        void onRight();
    }
}
