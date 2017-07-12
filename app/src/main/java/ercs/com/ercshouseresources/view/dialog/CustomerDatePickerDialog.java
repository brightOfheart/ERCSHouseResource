package ercs.com.ercshouseresources.view.dialog;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import java.lang.reflect.Field;
/**
 * Created by Administrator on 2017/6/30.
 * 自定义的时间选择器
 */

public class CustomerDatePickerDialog extends DatePickerDialog {
    public CustomerDatePickerDialog(Context context,
                                    OnDateSetListener callBack, int year, int monthOfYear,
                                    int dayOfMonth) {
        super(context,DatePickerDialog.THEME_HOLO_LIGHT, callBack, year, monthOfYear, dayOfMonth);
        this.show();
        GoneDay();
        setDatePickerDividerColor(this.getDatePicker());
    }

    public CustomerDatePickerDialog(Context context,
                                    OnDateSetListener callBack, int year, int monthOfYear,
                                    int dayOfMonth,int isDay) {
        super(context,DatePickerDialog.THEME_HOLO_LIGHT, callBack, year, monthOfYear, dayOfMonth);
        this.show();

        setDatePickerDividerColor(this.getDatePicker());
    }
    @Override
    public void onDateChanged(DatePicker view, int year, int month, int day) {
        super.onDateChanged(view, year, month, day);
        this.setTitle("");
    }

    private DatePicker findDatePicker(ViewGroup group) {
        if (group != null) {
            for (int i = 0, j = group.getChildCount(); i < j; i++) {
                View child = group.getChildAt(i);
                if (child instanceof DatePicker) {
                    return (DatePicker) child;
                } else if (child instanceof ViewGroup) {
                    DatePicker result = findDatePicker((ViewGroup) child);
                    if (result != null)
                        return result;
                }
            }
        }
        return null;
    }

    /**
     * 隐藏日
     */
    private void GoneDay()
    {
        DatePicker dp = findDatePicker((ViewGroup) this.getWindow().getDecorView());
        if (dp != null) {
            ((ViewGroup)((ViewGroup) dp.getChildAt(0)).getChildAt(0)).getChildAt(2).setVisibility(View.GONE);
        }
    }

    /**
     * 设置时间选择器的分割线颜色
     *
     * @param datePicker
     */
    private void setDatePickerDividerColor(DatePicker datePicker) {
        // Divider changing:

        // 获取 mSpinners
        LinearLayout llFirst = (LinearLayout) datePicker.getChildAt(0);

        // 获取 NumberPicker
        LinearLayout mSpinners = (LinearLayout) llFirst.getChildAt(0);
        for (int i = 0; i < mSpinners.getChildCount(); i++) {
            NumberPicker picker = (NumberPicker) mSpinners.getChildAt(i);

            Field[] pickerFields = NumberPicker.class.getDeclaredFields();
            for (Field pf : pickerFields) {
                if (pf.getName().equals("mSelectionDivider")) {
                    pf.setAccessible(true);
                    try {
                        pf.set(picker, new ColorDrawable(Color.parseColor("#1AB394")));//设置分割线颜色
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    } catch (Resources.NotFoundException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
        }
    }
}
