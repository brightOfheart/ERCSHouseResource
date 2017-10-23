package ercs.com.ercshouseresources.view.item;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.activity.Text;
import ercs.com.ercshouseresources.network.NetHelperNew;
import ercs.com.ercshouseresources.util.imageUtil.GlideUtil;

/**
 * Created by Administrator on 2017/8/11.
 */

public class Ren_ResginDetailItem extends RelativeLayout {
    private LinearLayout linearLayout;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.ly_pic)
    LinearLayout ly_pic;
    @BindView(R.id.tv_content)
    TextView tv_content;

    public Ren_ResginDetailItem(Context context, String title, List<String> pic, String content) {
        super(context);
        LayoutInflater mInflater = LayoutInflater.from(context);
        linearLayout = (LinearLayout) mInflater.inflate(
                R.layout.item_resgindetail, null);
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 0, 0, 0);
        this.addView(linearLayout, params);
        ButterKnife.bind(this);
        tv_title.setText(title);
        tv_content.setText(content);
        for (int i = 0; i < pic.size(); i++) {
            ImageView imageView = new ImageView(context);
            LayoutParams paramss = new LayoutParams(LayoutParams.MATCH_PARENT,
                    LayoutParams.WRAP_CONTENT);
            paramss.setMargins(50, 50, 50, 50);
            imageView.setLayoutParams(paramss);
            ly_pic.addView(imageView);
            TextView textView = new TextView(context);
            LayoutParams paramsss = new LayoutParams(LayoutParams.MATCH_PARENT,
                    20);
            textView.setLayoutParams(paramsss);
            ly_pic.addView(textView);
            GlideUtil.loadImage(context, NetHelperNew.URL + pic.get(i), imageView, R.mipmap.ic_launcher, R.mipmap.ic_launcher);
        }

    }
}
