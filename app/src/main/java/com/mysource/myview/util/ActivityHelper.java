package com.mysource.myview.util;

import android.content.Context;
import android.content.Intent;

import com.mysource.myview.ui.activity.ChartViewExampleActivity;
import com.mysource.myview.ui.activity.CycleAnimationActivity;
import com.mysource.myview.ui.activity.FloatActionSampleActivity;

/**
 * Created by Tung.Hoang on 02-12-15.
 */
public class ActivityHelper {

    public static void openCycleAnimationExample(Context context){
        Intent intent = new Intent(context, CycleAnimationActivity.class);
        context.startActivity(intent);
    }

    public static void openChartViewExample(Context context){
        Intent intent = new Intent(context, ChartViewExampleActivity.class);
        context.startActivity(intent);
    }

    public static void openFLoatActionExample(Context context){
        Intent intent = new Intent(context, FloatActionSampleActivity.class);
        context.startActivity(intent);
    }
}
