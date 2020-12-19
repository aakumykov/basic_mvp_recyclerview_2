package com.github.aakumykov.basic_mvp_recyclerview_2.start_page;

import android.os.Bundle;

import com.github.aakumykov.basic_mvp_recyclerview_2.BMVP_BasicActivity;
import com.github.aakumykov.basic_mvp_recyclerview_2.R;

public class AboutActivity extends BMVP_BasicActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);

        BMVP_setPageTitle(R.string.ABOUT_PAGE_page_title);
        BMVP_activateUpButton();
    }
}