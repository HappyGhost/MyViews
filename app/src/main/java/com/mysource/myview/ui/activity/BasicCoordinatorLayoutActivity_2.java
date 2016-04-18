package com.mysource.myview.ui.activity;

import android.os.Bundle;

import com.mysource.myview.R;
import com.mysource.myview.ui.fragment.AccountFragment2;

/**
 * Created by canihelpu on 18-04-16.
 */
public class BasicCoordinatorLayoutActivity_2 extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_container);
        AccountFragment2 accountFragment = new AccountFragment2();
        getSupportFragmentManager().beginTransaction().replace(R.id.container, accountFragment).commit();
    }
}
