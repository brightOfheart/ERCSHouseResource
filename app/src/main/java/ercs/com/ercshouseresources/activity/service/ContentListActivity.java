package ercs.com.ercshouseresources.activity.service;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.activity.BaseActivity;
import ercs.com.ercshouseresources.util.CloseActivityClass;
import ercs.com.ercshouseresources.util.TitleControl;
import ercs.com.ercshouseresources.view.item.ContentListItem;

/**
 * Created by Administrator on 2017/7/24.
 * 楼盘卖点
 */

public class ContentListActivity extends BaseActivity {
    @BindView(R.id.ly_all)
    LinearLayout ly_all;

    /**
     * 页面跳转
     */
    public static void start(Activity mactivity, String str1, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9) {
        Intent intent = new Intent(mactivity, ContentListActivity.class);
        intent.putExtra("str1", str1);
        intent.putExtra("str2", str2);
        intent.putExtra("str3", str3);
        intent.putExtra("str4", str4);
        intent.putExtra("str5", str5);
        intent.putExtra("str6", str6);
        intent.putExtra("str7", str7);
        intent.putExtra("str8", str8);
        intent.putExtra("str9", str9);
        mactivity.startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contentlist);
        ButterKnife.bind(this);
        initTitle();
        initview();
        if(!CloseActivityClass.activityList.contains(this))
        {
            CloseActivityClass.activityList.add(this);
        }
    }

    /**
     * 设置标题栏
     */
    private void initTitle() {
        TitleControl t = new TitleControl(this);
        t.setTitle("楼盘卖点");

    }

    /**
     * 初始化
     */
    private void initview() {
        if (!getStr1().equals("")) {
            ly_all.addView(new ContentListItem(this, R.mipmap.lp_jgys, "价格优势", getStr1()));
        }
        if (!getStr2().equals("")) {
            ly_all.addView(new ContentListItem(this, R.mipmap.lp_hxmj, "户型面积", getStr2()));
        }
        if (!getStr3().equals("")) {
            ly_all.addView(new ContentListItem(this, R.mipmap.lp_shpt, "生活配套", getStr3()));
        }
        if (!getStr4().equals("")) {
            ly_all.addView(new ContentListItem(this, R.mipmap.lp_xqpt, "学区配套", getStr4()));
        }
        if (!getStr5().equals("")) {
            ly_all.addView(new ContentListItem(this, R.mipmap.lp_jtcx, "交通出行", getStr5()));
        }
        if (!getStr6().equals("")) {
            ly_all.addView(new ContentListItem(this, R.mipmap.lp_qyfz, "区域发展", getStr6()));
        }
        if (!getStr7().equals("")) {
            ly_all.addView(new ContentListItem(this, R.mipmap.lp_xmts, "项目特色", getStr7()));
        }
        if (!getStr8().equals("")) {
            ly_all.addView(new ContentListItem(this, R.mipmap.lp_ppys, "品牌优势", getStr8()));
        }
        if (!getStr9().equals("")) {
            ly_all.addView(new ContentListItem(this, R.mipmap.lp_jpdb, "精品对比", getStr9()));
        }
    }

    private String getStr1() {

        return getIntent().getStringExtra("str1");
    }

    private String getStr2() {

        return getIntent().getStringExtra("str2");
    }

    private String getStr3() {

        return getIntent().getStringExtra("str3");
    }

    private String getStr4() {

        return getIntent().getStringExtra("str4");
    }

    private String getStr5() {

        return getIntent().getStringExtra("str5");
    }

    private String getStr6() {

        return getIntent().getStringExtra("str6");
    }

    private String getStr7() {

        return getIntent().getStringExtra("str7");
    }

    private String getStr8() {

        return getIntent().getStringExtra("str8");
    }

    private String getStr9() {

        return getIntent().getStringExtra("str9");
    }
}
