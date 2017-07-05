package ercs.com.ercshouseresources.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.activity.PhotoViewActivity;
import ercs.com.ercshouseresources.view.lazyviewpager.LazyFragmentPagerAdapter;

/**
 * Created by Administrator on 2017/6/22.
 * 消息页面
 */

public class MessageFragment extends Fragment implements LazyFragmentPagerAdapter.Laziable {
    @BindView(R.id.iv)
    ImageView iv;//年月日
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, container, false);
        ButterKnife.bind(this, view);
        initview();
        return view;

    }

    /**
     * 初始化
     */
    private void initview()
    {
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), PhotoViewActivity.class));
            }
        });
    }
}
