package ercs.com.ercshouseresources.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;

import ercs.com.ercshouseresources.R;
import ercs.com.ercshouseresources.base.BaseApplication;
import ercs.com.ercshouseresources.util.CloseActivityClass;
import ercs.com.ercshouseresources.util.DisplayUtil;
import ercs.com.ercshouseresources.util.SPUtil;

/**
 * Created by Administrator on 2017/8/4.
 * 自定义的检查是否被重复登录的dialog
 */

public class CheckDialog extends Dialog {
    private Button button;
    private Context context;


    public CheckDialog(Context context, int theme) {
        super(context, theme);
        this.context = context;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.dialog_check, null);
        setContentView(view, new LayoutParams(DisplayUtil.getWidthPixels(context) / 2,
                LayoutParams.WRAP_CONTENT));
        button = (Button) view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
                CloseActivityClass.exitClient();
            }
        });
        this.setCanceledOnTouchOutside(false);//点击屏幕不消失


    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }


}
