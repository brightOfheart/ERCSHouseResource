package ercs.com.ercshouseresources.view.item;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ercs.com.ercshouseresources.R;

/**
 * Created by Administrator on 2017/8/11.
 */

public class Ren_ResginDetailItem extends RelativeLayout {
    private LinearLayout linearLayout;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.iv_pic)
    ImageView iv_pic;
    @BindView(R.id.tv_content)
    TextView tv_content;
    public Ren_ResginDetailItem(Context context) {
        super(context);
        LayoutInflater mInflater = LayoutInflater.from(context);
        linearLayout = (LinearLayout) mInflater.inflate(
                R.layout.item_resgindetail, null);
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 0, 0, 0);
        this.addView(linearLayout, params);
        ButterKnife.bind(this);

    }
}
