package ercs.com.ercshouseresources.view.item;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.network.NetHelperNew;
import ercs.com.ercshouseresources.util.DisplayUtil;
import ercs.com.ercshouseresources.util.imageUtil.GlideUtil;

/**
 * Created by Administrator on 2017/8/26.
 */

public class PicItem extends RelativeLayout {
    private LinearLayout linearLayout;
    @BindView(R.id.iv_pic)
    ImageView iv_pic;

    public PicItem(Context context, String icon) {
        super(context);
        LayoutInflater mInflater = LayoutInflater.from(context);
        linearLayout = (LinearLayout) mInflater.inflate(
                R.layout.item_pic, null);
        LayoutParams params = new LayoutParams(new LinearLayout.LayoutParams(DisplayUtil.getWidthPixels(context), ViewGroup.LayoutParams.MATCH_PARENT));
        params.setMargins(0, 0, 0, 0);
        this.addView(linearLayout, params);
        ButterKnife.bind(this);
        if (icon.equals("")) {
            iv_pic.setImageResource(R.mipmap.ic_launcher);
        } else {
            GlideUtil.loadImage(context, NetHelperNew.URL + icon, iv_pic, R.mipmap.ic_launcher, R.mipmap.ic_launcher);
        }


    }
}
