package ercs.com.ercshouseresources.view.dialog;

import android.app.Activity;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.util.ToastUtil;

/**
 * 流程申请类型选择
 * Created by Administrator on 2017/7/11.
 */

public class TypeSelectDialog extends BaseDialog {

    private RadioButton rb_rest,rb_out,rb_retroactive;//休息，外出，补签
    private int i=0;//0 未选择 1休息 2 外出 3补签

    private TextView tv_quit,tv_sure;//取消 确定

    private TextView tv_rest,tv_out,tv_retroactive;//休息，外出，补签
    private String s1,s2,s3;
    private OnTypeSelectListener onTypeSelectListener;
    public TypeSelectDialog(Activity mActivity,OnTypeSelectListener onTypeSelectListener,String s1,String s2,String s3) {
        super(mActivity);
        this.onTypeSelectListener=onTypeSelectListener;
        this.s1=s1;
        this.s2=s2;
        this.s3=s3;
    }


    @Override
    public int getAnimStyle() {
        return 0;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.dialog_type_select;
    }

    @Override
    public void initView() {
        rb_rest=findViewById(R.id.rb_rest);
        rb_out=findViewById(R.id.rb_out);
        rb_retroactive=findViewById(R.id.rb_retroactive);

        tv_rest=findViewById(R.id.tv_rest);
        tv_out=findViewById(R.id.tv_out);
        tv_retroactive=findViewById(R.id.tv_retroactive);

        tv_rest.setText(s1);
        tv_out.setText(s2);
        tv_retroactive.setText(s3);
        rb_rest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //休息
                rb_out.setChecked(false);
                rb_retroactive.setChecked(false);
                i=1;
            }
        });

        rb_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 //外出
                rb_rest.setChecked(false);
                rb_retroactive.setChecked(false);
                i=2;
            }
        });

        rb_retroactive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //补签
                rb_out.setChecked(false);
                rb_rest.setChecked(false);
                i=3;
            }
        });

        tv_quit=findViewById(R.id.tv_quit);
        tv_quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //取消
                dismiss();

            }
        });
        tv_sure=findViewById(R.id.tv_sure);
        tv_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //确定
                if (i==0)
                {
                    ToastUtil.showToast(getContext(),"请选择类型");
                }else
                {
                    onTypeSelectListener.getTypeSelect(i);
                    dismiss();
                }


            }
        });
    }
    public interface OnTypeSelectListener{
        void getTypeSelect(int m);
    }

}
