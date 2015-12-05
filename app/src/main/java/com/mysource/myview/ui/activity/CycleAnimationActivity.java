package com.mysource.myview.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.mysource.myview.R;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by Tung.Hoang on 30-11-15.
 */
public class CycleAnimationActivity extends BaseActivity {

    @Bind(R.id.imBulb1_normal)
    ImageView imBulbNormal1;

    @Bind(R.id.imBulb2_normal)
    ImageView imBulbNormal2;

    @Bind(R.id.imBulb3_normal)
    ImageView imBulbNormal3;

    @Bind(R.id.imBulb4_normal)
    ImageView imBulbNormal4;

    @Bind(R.id.imBulb5_normal)
    ImageView imBulbNormal5;

    @Bind(R.id.imBulb6_normal)
    ImageView imBulbNormal6;

    @Bind(R.id.imBulb7_normal)
    ImageView imBulbNormal7;

    @Bind(R.id.imBulb8_normal)
    ImageView imBulbNormal8;

    @Bind(R.id.imBulb9_normal)
    ImageView imBulbNormal9;

    @Bind(R.id.imBulb10_normal)
    ImageView imBulbNormal10;

    @Bind(R.id.imBulb11_normal)
    ImageView imBulbNormal11;

    @Bind(R.id.imBulb12_normal)
    ImageView imBulbNormal12;

    @Bind(R.id.imBulb1_light)
    ImageView imBulbLight1;

    @Bind(R.id.imBulb2_light)
    ImageView imBulbLight2;

    @Bind(R.id.imBulb3_light)
    ImageView imBulbLight3;

    @Bind(R.id.imBulb4_light)
    ImageView imBulbLight4;

    @Bind(R.id.imBulb5_light)
    ImageView imBulbLight5;

    @Bind(R.id.imBulb6_light)
    ImageView imBulbLight6;

    @Bind(R.id.imBulb7_light)
    ImageView imBulbLight7;

    @Bind(R.id.imBulb8_light)
    ImageView imBulbLight8;

    @Bind(R.id.imBulb9_light)
    ImageView imBulbLight9;

    @Bind(R.id.imBulb10_light)
    ImageView imBulbLight10;

    @Bind(R.id.imBulb11_light)
    ImageView imBulbLight11;

    @Bind(R.id.imBulb12_light)
    ImageView imBulbLight12;

    @Bind(R.id.edtBulbNumber)
    EditText edtBulbNumber;

    @Bind(R.id.imWreath)
    ImageView imXmasWreath;

    @Bind(R.id.imFestivalGraphic)
    ImageView imFestivalGraphic;

    /**
     * Objects for Bulbs Animation
     */

    TimerTask loadingAnimationTimerTask;
    Timer timerAnimation;
    int glowedBulbIndex = 0; // bulb's index which is blowing.
    int glowedBulbNumber = 0; // number of bulb will be glowed at the same time

    /**
     * End Objects for Bulbs Animation
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cycle_animation);
    }

    @OnClick(R.id.btnAnimation)
    public void onAnimationButonClicked() {
        String input = edtBulbNumber.getText().toString();
        if (!input.isEmpty()) {
            try {
                int bulbNumber = Integer.parseInt(input);
                startWreathAnimation(bulbNumber);

            } catch (NumberFormatException nfe) {
                System.out.println("Could not parse " + nfe);
            }

        }
    }

    public void startWreathAnimation(int bulbNumber) {

        glowedBulbIndex = 0;
        glowedBulbNumber = bulbNumber;
        runBulbsAnimation();
    }

    public void runBulbsAnimation() {
        stopBuldsAnimation();
        timerAnimation = new Timer();

        initBulbAnimation();

        timerAnimation.schedule(loadingAnimationTimerTask, 300, 100);
    }

    public void stopBuldsAnimation() {
        if (timerAnimation == null) {
            return;
        }

        if (loadingAnimationTimerTask != null) {
            loadingAnimationTimerTask.cancel();
            loadingAnimationTimerTask = null;
        }

        timerAnimation.cancel();
        timerAnimation = null;
    }

    public void initBulbAnimation() {

        loadingAnimationTimerTask = new TimerTask() {
            @Override
            public void run() {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        turnOffBulbs();
                        if (glowedBulbIndex == 12 + glowedBulbNumber + 1) {
                            // after bulb's animation is finished, turn on all bulbs.
                            turnOnBulbs();
                            timerAnimation.cancel();
                            return;
                        }

                        for (int i = glowedBulbIndex - glowedBulbNumber + 1; i <= glowedBulbIndex; i++) {
                            switch (i) {

                                case 1:
                                    imBulbNormal1.setVisibility(View.INVISIBLE);
                                    imBulbLight1.setVisibility(View.VISIBLE);
                                    break;

                                case 2:
                                    imBulbNormal2.setVisibility(View.INVISIBLE);
                                    imBulbLight2.setVisibility(View.VISIBLE);
                                    break;

                                case 3:
                                    imBulbNormal3.setVisibility(View.INVISIBLE);
                                    imBulbLight3.setVisibility(View.VISIBLE);
                                    break;

                                case 4:
                                    imBulbNormal4.setVisibility(View.INVISIBLE);
                                    imBulbLight4.setVisibility(View.VISIBLE);
                                    break;

                                case 5:
                                    imBulbNormal5.setVisibility(View.INVISIBLE);
                                    imBulbLight5.setVisibility(View.VISIBLE);
                                    break;

                                case 6:
                                    imBulbNormal6.setVisibility(View.INVISIBLE);
                                    imBulbLight6.setVisibility(View.VISIBLE);
                                    break;

                                case 7:
                                    imBulbNormal7.setVisibility(View.INVISIBLE);
                                    imBulbLight7.setVisibility(View.VISIBLE);
                                    break;

                                case 8:
                                    imBulbNormal8.setVisibility(View.INVISIBLE);
                                    imBulbLight8.setVisibility(View.VISIBLE);
                                    break;

                                case 9:
                                    imBulbNormal9.setVisibility(View.INVISIBLE);
                                    imBulbLight9.setVisibility(View.VISIBLE);
                                    break;

                                case 10:
                                    imBulbNormal10.setVisibility(View.INVISIBLE);
                                    imBulbLight10.setVisibility(View.VISIBLE);
                                    break;

                                case 11:
                                    imBulbNormal11.setVisibility(View.INVISIBLE);
                                    imBulbLight11.setVisibility(View.VISIBLE);
                                    break;

                                case 0:
                                case 12:
                                    imBulbNormal12.setVisibility(View.INVISIBLE);
                                    imBulbLight12.setVisibility(View.VISIBLE);
                                    break;

                            }
                        }
                        glowedBulbIndex++;
                    }
                });

            }

        };
    }

    private void turnOffBulbs() {
        imBulbLight1.setVisibility(View.INVISIBLE);
        imBulbLight2.setVisibility(View.INVISIBLE);
        imBulbLight3.setVisibility(View.INVISIBLE);
        imBulbLight4.setVisibility(View.INVISIBLE);
        imBulbLight5.setVisibility(View.INVISIBLE);
        imBulbLight6.setVisibility(View.INVISIBLE);
        imBulbLight7.setVisibility(View.INVISIBLE);
        imBulbLight8.setVisibility(View.INVISIBLE);
        imBulbLight9.setVisibility(View.INVISIBLE);
        imBulbLight10.setVisibility(View.INVISIBLE);
        imBulbLight11.setVisibility(View.INVISIBLE);
        imBulbLight12.setVisibility(View.INVISIBLE);

        imBulbNormal1.setVisibility(View.INVISIBLE);
        imBulbNormal2.setVisibility(View.INVISIBLE);
        imBulbNormal3.setVisibility(View.INVISIBLE);
        imBulbNormal4.setVisibility(View.INVISIBLE);
        imBulbNormal5.setVisibility(View.INVISIBLE);
        imBulbNormal6.setVisibility(View.INVISIBLE);
        imBulbNormal7.setVisibility(View.INVISIBLE);
        imBulbNormal8.setVisibility(View.INVISIBLE);
        imBulbNormal9.setVisibility(View.INVISIBLE);
        imBulbNormal10.setVisibility(View.INVISIBLE);
        imBulbNormal11.setVisibility(View.INVISIBLE);
        imBulbNormal12.setVisibility(View.INVISIBLE);
    }

    private void turnOnBulbs() {

        imBulbLight1.setVisibility(View.VISIBLE);
        imBulbLight2.setVisibility(View.VISIBLE);
        imBulbLight3.setVisibility(View.VISIBLE);
        imBulbLight4.setVisibility(View.VISIBLE);
        imBulbLight5.setVisibility(View.VISIBLE);
        imBulbLight6.setVisibility(View.VISIBLE);
        imBulbLight7.setVisibility(View.VISIBLE);
        imBulbLight8.setVisibility(View.VISIBLE);
        imBulbLight9.setVisibility(View.VISIBLE);
        imBulbLight10.setVisibility(View.VISIBLE);
        imBulbLight11.setVisibility(View.VISIBLE);
        imBulbLight12.setVisibility(View.VISIBLE);

        imBulbNormal1.setVisibility(View.INVISIBLE);
        imBulbNormal2.setVisibility(View.INVISIBLE);
        imBulbNormal3.setVisibility(View.INVISIBLE);
        imBulbNormal4.setVisibility(View.INVISIBLE);
        imBulbNormal5.setVisibility(View.INVISIBLE);
        imBulbNormal6.setVisibility(View.INVISIBLE);
        imBulbNormal7.setVisibility(View.INVISIBLE);
        imBulbNormal8.setVisibility(View.INVISIBLE);
        imBulbNormal9.setVisibility(View.INVISIBLE);
        imBulbNormal10.setVisibility(View.INVISIBLE);
        imBulbNormal11.setVisibility(View.INVISIBLE);
        imBulbNormal12.setVisibility(View.INVISIBLE);

    }


}
