package com.github.aakumykov.basic_mvp_recyclerview_2.start_page;

import android.os.Bundle;

import com.github.aakumykov.basic_mvp_recyclerview_2.BasicActivity;
import com.github.aakumykov.basic_mvp_recyclerview_2.R;

public class AboutActivity extends BasicActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);
    }
}