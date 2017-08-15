package ercs.com.ercshouseresources.view.item;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.activity.renovation.RenovationDetailActivity;
import ercs.com.ercshouseresources.network.NetHelperNew;
import ercs.com.ercshouseresources.util.imageUtil.GlideUtil;

/**
 * Created by Administrator on 2017/8/11.
 */

public class RenovatinDetailItem extends RelativeLayout {
    private LinearLayout linearLayout;
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.tv_1)
    TextView tv_1;
    @BindView(R.id.tv_2)
    TextView tv_2;
    public RenovatinDetailItem(Context context, String icon , String title, String content) {
        super(context);
        LayoutInflater mInflater = LayoutInflater.from(context);
        linearLayout = (LinearLayout) mInflater.inflate(
                R.layout.item_renovatindetail, null);
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 0, 0, 0);
        this.addView(linearLayout, params);
        ButterKnife.bind(this);
        GlideUtil.loadImage(context, NetHelperNew.URL+icon,image,R.mipmap.ic_launcher,R.mipmap.ic_launcher);
        tv_1.setText(title);
        tv_2.setText(content);
    }

}
