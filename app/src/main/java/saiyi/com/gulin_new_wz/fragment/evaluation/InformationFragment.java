package saiyi.com.gulin_new_wz.fragment.evaluation;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import saiyi.com.gulin_new_wz.R;
import saiyi.com.gulin_new_wz.view.MyDialogForText;

/**
 * Created by 陈姣姣 on 2018/8/24.
 */
public class InformationFragment extends Fragment {


    View view;
    @BindView(R.id.timer)
    Chronometer timer;
    @BindView(R.id.btn_stop)
    TextView btnStop;

    MyDialogForText  myDialogForText=null;
    Unbinder unbinder;

    boolean selector=true;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_information, null);

        unbinder = ButterKnife.bind(this, view);
        myDialogForText =new MyDialogForText(getActivity());


        return view;

    }

//    public void btnClick(View view) {
//        timer.setBase(SystemClock.elapsedRealtime());//计时器清零
//        int hour = (int) ((SystemClock.elapsedRealtime() - timer.getBase()) / 1000 / 60);
//        timer.setFormat("0"+String.valueOf(hour)+":%s");
//        timer.start();
//    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btn_stop)
    public void onViewClicked() {


        if (selector){
            showDialog(getResources().getString(R.string.Collection_distance)+"(0`100m)");
            selector=!selector;
        }else {
            timer.stop();
            btnStop.setText(getResources().getString(R.string.CollectionDate));
            selector=!selector;
        }

    }

    public  void  showDialog(String title){
        if (myDialogForText!=null) {

            myDialogForText.setTitle(title);
            myDialogForText.setMessageToNull();
            myDialogForText.setYesOnclickListener(getActivity().getResources().getString(R.string.sure), new MyDialogForText.onYesOnclickListener() {
                @Override
                public void onYesClick() {

                    myDialogForText.dismiss();
                    String value = myDialogForText.getMessage();
                    btnStop.setText(getResources().getString(R.string.stop));
                    timer.setBase(SystemClock.elapsedRealtime());//计时器清零
                    timer.start();

                }
            });
            myDialogForText.setNoOnclickListener(getActivity().getResources().getString(R.string.cancel), new MyDialogForText.onNoOnclickListener() {
                @Override
                public void onNoClick() {

                    myDialogForText.dismiss();

                }
            });

            myDialogForText.show();
        }
    }


}
