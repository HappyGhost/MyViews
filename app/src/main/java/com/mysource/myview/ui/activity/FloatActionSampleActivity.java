package com.mysource.myview.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.mysource.myview.R;
import com.mysource.myview.model.ActionModel;
import com.mysource.myview.ui.widget.FloatActionView;

import butterknife.Bind;

/**
 * Created by Tung.Hoang on 12-12-15.
 */
public class FloatActionSampleActivity extends BaseActivity {

    @Bind(R.id.floatActionView)
    FloatActionView floatActionView;

    @Bind(R.id.mainLayout)
    RelativeLayout mainLayout;

    int[] actionResources = {R.drawable.online_savings, R.drawable.pay_bill, R.drawable.bank_transfer, R.drawable.pay_debt, R.drawable.q_r_pay};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_float_action_sample);
        init();
    }

    private void init() {
        floatActionView.setFloatActionListener(new FloatActionView.FloatActionListener() {
            @Override
            public void onFloatActionItemClicked(View view, int position) {
                switch (position) {

                    case 0:
                        Toast.makeText(mContext, "action 1", Toast.LENGTH_SHORT).show();
                        break;

                    case 1:
                        Toast.makeText(mContext, "action 2", Toast.LENGTH_SHORT).show();
                        break;

                    case 2:
                        Toast.makeText(mContext, "action 3", Toast.LENGTH_SHORT).show();
                        break;

                    case 3:
                        Toast.makeText(mContext, "action 4", Toast.LENGTH_SHORT).show();
                        break;

                    case 4:
                        Toast.makeText(mContext, "action 5", Toast.LENGTH_SHORT).show();
                        break;
                }
            }

            @Override
            public void onFloatActionClicked(boolean isShown) {
                if(isShown){
                    mainLayout.setBackgroundColor(getResources().getColor(R.color.colorWhiteHover));
                }else{
                    mainLayout.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                }
            }
        });

        String[] actionTexts = getResources().getStringArray(R.array.text_float_action);

        for (int i = 0; i < actionResources.length; i++) {
            ActionModel actionModel = new ActionModel();
            actionModel.setActionResource(actionResources[i]);
            actionModel.setActionText(actionTexts[i]);
            floatActionView.addAction(actionModel);
        }
    }
}
