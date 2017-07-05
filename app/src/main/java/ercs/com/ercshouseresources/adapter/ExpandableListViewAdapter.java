package ercs.com.ercshouseresources.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import java.util.List;
import java.util.Map;
import ercs.com.ercshouseresources.R;

/**
 * Created by Administrator on 2017/6/22.
 * 考查统计的二级列表
 */

public class ExpandableListViewAdapter extends BaseExpandableListAdapter {
    private Map<String, List<String>> dataset;
    private String[] parentList;
    private Context context;

    public ExpandableListViewAdapter(Context context, Map<String, List<String>> dataset, String[] parentList) {
        this.context = context;
        this.dataset = dataset;
        this.parentList = parentList;
    }

    //  获得某个父项的某个子项
    @Override
    public Object getChild(int parentPos, int childPos) {
        return dataset.get(parentList[parentPos]).get(childPos);
    }

    //  获得父项的数量
    @Override
    public int getGroupCount() {
        return dataset.size();
    }

    //  获得某个父项的子项数目
    @Override
    public int getChildrenCount(int parentPos) {
        return dataset.get(parentList[parentPos]).size();
    }

    //  获得某个父项
    @Override
    public Object getGroup(int parentPos) {
        return dataset.get(parentList[parentPos]);
    }

    //  获得某个父项的id
    @Override
    public long getGroupId(int parentPos) {
        return parentPos;
    }

    //  获得某个父项的某个子项的id
    @Override
    public long getChildId(int parentPos, int childPos) {
        return childPos;
    }

    //  按函数的名字来理解应该是是否具有稳定的id，这个方法目前一直都是返回false，没有去改动过
    @Override
    public boolean hasStableIds() {
        return false;
    }

    //  获得父项显示的view
    @Override
    public View getGroupView(int parentPos, boolean b, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.adapter_parent_item, null);
        }
        view.setTag(R.layout.adapter_parent_item, parentPos);
        view.setTag(R.layout.adapter_child_item, -1);
        TextView tv_kind = (TextView) view.findViewById(R.id.tv_kind);
        tv_kind.setText(parentList[parentPos]);
        TextView tv_day = (TextView) view.findViewById(R.id.tv_day);
        tv_day.setText(parentList[parentPos]);
        return view;
    }

    //  获得子项显示的view
    @Override
    public View getChildView(int parentPos, int childPos, boolean b, View view, ViewGroup viewGroup) {
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.adapter_child_item, null);
        }
        view.setTag(R.layout.adapter_parent_item, parentPos);
        view.setTag(R.layout.adapter_child_item, childPos);
        TextView tv_day = (TextView) view.findViewById(R.id.tv_day);
        tv_day.setText(dataset.get(parentList[parentPos]).get(childPos));
        TextView tv_hours = (TextView) view.findViewById(R.id.tv_hours);
        tv_hours.setText(dataset.get(parentList[parentPos]).get(childPos));
        tv_hours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return view;
    }

    //  子项是否可选中，如果需要设置子项的点击事件，需要返回true
    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }

    /**
     * 数据返回类型
     *
     * @param str
     * @return
     */
    private String getType(String str) {
        if (str.equals("1"))
            return "出勤";
        else if (str.equals("2"))
            return "休息";
        else if (str.equals("3"))
            return "迟到";
        else if (str.equals("4"))
            return "早退";
        else if (str.equals("5"))
            return "缺卡";
        else if (str.equals("6"))
            return "旷工";
        else if (str.equals("7"))
            return "外出";
        else
            return "";
    }
}
