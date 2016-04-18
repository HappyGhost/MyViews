package com.mysource.myview.ui.activity;

import android.os.Bundle;

import com.mysource.myview.R;
import com.mysource.myview.ui.fragment.AccountFragment;

/**
 * Created by canihelpu on 12-02-16.
 */
public class BasicCoordinatorLayoutActivity_1 extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_container);
        AccountFragment accountFragment = new AccountFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.container, accountFragment).commit();
    }
}
