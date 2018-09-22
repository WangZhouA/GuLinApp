package saiyi.com.gulin_new_wz.ui.communication_data;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import saiyi.com.gulin_new_wz.R;
import saiyi.com.gulin_new_wz.adapter.MyFragmentPagerAdapter;
import saiyi.com.gulin_new_wz.base.BaseActivity;

/**
 * Created by 陈姣姣 on 2018/9/13.
 */
public class DataHomeActivity extends BaseActivity implements View.OnClickListener{


    CommunicationDataActivity informationFragment;
    DerviceDateFragment evaluationHistoryFragment;

    ArrayList<Fragment> fragmentList;
    @BindView(R.id.tv_tab_InformationCollection)
    TextView tvTabInformationCollection;
    @BindView(R.id.lin_tab_InformationCollection)
    LinearLayout linTabInformationCollection;
    @BindView(R.id.tv_tab_EvaluationHistory)
    TextView tvTabEvaluationHistory;
    @BindView(R.id.lin_tab_EvaluationHistory)
    LinearLayout linTabEvaluationHistory;
    @BindView(R.id.pager)
    ViewPager mPager;
    @BindView(R.id.view_InformationCollection)
    View viewInformationCollection;
    @BindView(R.id.view_EvaluationHistory)
    View viewEvaluationHistory;


    @BindView(R.id.header_right)
    ImageButton imRigeht;
    @BindView(R.id.header_text)
    TextView tvText;

    IFragmentListener iFragmentListener;
    public void setiFragmentListener(IFragmentListener iFragmentListener) {
        this.iFragmentListener = iFragmentListener;
    }

    @Override
    protected void initView() {

        setContentView(R.layout.activity_date);
    }

    @Override
    protected void initData() {

        showNormeBar();
        setTile(getResources().getString(R.string.data_view));
        imRigeht.setVisibility(View.VISIBLE);
        imRigeht.setImageResource(R.mipmap.empty);
        InitViewPager();
        setListener();

    }

    private void InitViewPager() {


        fragmentList = new ArrayList<>();
        informationFragment = new CommunicationDataActivity();
        evaluationHistoryFragment = new DerviceDateFragment();

        fragmentList.add(informationFragment);
        fragmentList.add(evaluationHistoryFragment);

        mPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), fragmentList));
        mPager.setOnPageChangeListener(new MyOnPageChangeListener());

        mPager.setCurrentItem(0);
        viewInformationCollection.setVisibility(View.VISIBLE);
        viewEvaluationHistory.setVisibility(View.GONE);
        tvTabEvaluationHistory.setTextColor(Color.parseColor("#00DAB0"));
        tvTabEvaluationHistory.setTextColor(Color.parseColor("#4E5969"));
    }


    @OnClick({R.id.header_right})
    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.header_right:

                iFragmentListener.setdeleteButton();


                break;

        }

    }


    private void setListener() {

        linTabInformationCollection.setOnClickListener(new MyOnClickListener(0));
        linTabEvaluationHistory.setOnClickListener(new MyOnClickListener(1));



    }


    /**
     * view pager
     */
    private int currIndex = 0;  //fragment 下标



    public class MyOnClickListener implements View.OnClickListener {
        private int index = 0;

        public MyOnClickListener(int i) {
            index = i;
        }

        @Override
        public void onClick(View v) {
            mPager.setCurrentItem(index);
        }
    }

    public class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageSelected(int arg0) {

            switch (arg0) {
                case 0:
                    if (currIndex == 1) {

                        iFragmentListener.setLeftFragment();

                        imRigeht.setVisibility(View.VISIBLE);
//                        tvText.setText(getResources().getString(R.string.communication_data_text));
                        viewInformationCollection.setVisibility(View.VISIBLE);
                        viewEvaluationHistory.setVisibility(View.GONE);
                        tvTabEvaluationHistory.setTextColor(Color.parseColor("#00DAB0"));
                        tvTabEvaluationHistory.setTextColor(Color.parseColor("#4E5969"));

                    }

                    break;
                case 1:
                    if (currIndex == 0) {
                        imRigeht.setVisibility(View.GONE);
//                        tvText.setText(getResources().getString(R.string.Evaluation_history));
                        iFragmentListener.setRightFragment();
                        viewInformationCollection.setVisibility(View.GONE);
                        viewEvaluationHistory.setVisibility(View.VISIBLE);
                        tvTabEvaluationHistory.setTextColor(Color.parseColor("#4E5969"));
                        tvTabEvaluationHistory.setTextColor(Color.parseColor("#00DAB0"));

                    }

                    break;
            }
            currIndex = arg0;

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }
    }


}
