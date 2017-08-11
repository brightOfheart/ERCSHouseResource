package ercs.com.ercshouseresources.activity.renovation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.activity.BaseActivity;
import ercs.com.ercshouseresources.activity.cheaproom.CheapReportingClientsActivity;
import ercs.com.ercshouseresources.activity.service.AddClientActivity;
import ercs.com.ercshouseresources.adapter.DesignSchemeAdapter;
import ercs.com.ercshouseresources.adapter.ReportingClientsAdapter;
import ercs.com.ercshouseresources.util.TitleControl;
import ercs.com.ercshouseresources.view.renpop.RenDesignPop;

/**
 * Created by Administrator on 2017/8/11.
 * 设计方案
 */

public class Ren_DesignSchemeActivity extends BaseActivity {
    @BindView(R.id.recyleview)
    LRecyclerView mRecyclerView;
    @BindView(R.id.v_line)
    View v_line;
    private LRecyclerViewAdapter mLRecyclerViewAdapter;
    private RenDesignPop renDesignPop=null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_designscheme);
        ButterKnife.bind(this);
        initTitle();
    }

    /**
     * 设置标题栏
     */
    private void initTitle() {
        TitleControl t = new TitleControl(this);
        t.setTitle("设计方案");

    }
    private void setData()
    {
           renDesignPop=new RenDesignPop(this, new RenDesignPop.OnSelectContentListener() {
            @Override
            public void selectContent(String s) {

            }
        });
    }

    @OnClick({R.id.ly_style, R.id.ly_room, R.id.ly_areo})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ly_style://风格
                setData();
                if (renDesignPop!=null)
                    renDesignPop.showAsDropDown(v_line,0,0);
                    renDesignPop=null;
                break;
            case R.id.ly_room:
                setData();
                if (renDesignPop!=null)
                    renDesignPop.showAsDropDown(v_line,0,0);
                    renDesignPop=null;
                break;
            case R.id.ly_areo:
                setData();
                if (renDesignPop!=null)
                    renDesignPop.showAsDropDown(v_line,0,0);
                    renDesignPop=null;
                break;
        }
    }

    /**
     * 初始化
     */
    private void createview() {
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(new DesignSchemeAdapter(this, null, ""));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mLRecyclerViewAdapter);
    }
}
