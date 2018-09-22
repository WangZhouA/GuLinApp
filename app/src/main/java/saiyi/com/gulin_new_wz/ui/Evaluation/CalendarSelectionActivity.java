package saiyi.com.gulin_new_wz.ui.Evaluation;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;

import com.necer.ncalendar.calendar.MonthCalendar;
import com.necer.ncalendar.listener.OnMonthCalendarChangedListener;

import org.joda.time.LocalDate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;
import saiyi.com.gulin_new_wz.R;
import saiyi.com.gulin_new_wz.base.BaseActivity;
import saiyi.com.gulin_new_wz.utils.CustomDatePicker;
import saiyi.com.gulin_new_wz.utils.TimeUtil;

/**
 * Created by 陈姣姣 on 2018/9/11.
 */
public class CalendarSelectionActivity extends BaseActivity implements View.OnClickListener{


    @BindView(R.id.header_text)
    TextView TV_Text;
    @BindView(R.id.monthcalendar)
    MonthCalendar monthcalendar;
    @Override
    protected void initView() {

        setContentView(R.layout.activity_calendar_selection);
    }

    @Override
    protected void initData() {

        TV_Text.setText(TimeUtil.getCurrentTime());
        showNormeBar();

        Drawable drawableRight = getResources().getDrawable(
                R.mipmap.down_up);

        TV_Text.setCompoundDrawablesWithIntrinsicBounds(null,
                null, drawableRight, null);
        TV_Text.setCompoundDrawablePadding(10);

        initDatePickerSleep();


        monthcalendar.setOnMonthCalendarChangedListener(new OnMonthCalendarChangedListener() {
            @Override
            public void onMonthCalendarChanged(LocalDate dateTime) {
                TV_Text.setText(dateTime.toString());
            }
        });




    }

    CustomDatePicker customDatePickerSleep;
    @OnClick({R.id.header_text})
    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.header_text:

                customDatePickerSleep.show(TimeUtil.getCurrentTime()+" "+"00:00");
                break;

        }
    }

    private void initDatePickerSleep() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        String now = sdf.format(new Date());
        customDatePickerSleep = new CustomDatePicker(this, new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) { // 回调接口，获得选中的时间

                TV_Text.setText(time.split(" ")[0]);
                monthcalendar.setDate(time.split(" ")[0]);


            }
        }, "2010-01-01 01:00", now); // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        customDatePickerSleep.setIsLoop(false); // 不允许循环滚动

    }



}
