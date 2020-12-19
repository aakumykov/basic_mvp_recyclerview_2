package com.github.aakumykov.basic_mvp_recyclerview_2.z_about_page;

import android.os.Bundle;

import com.github.aakumykov.basic_mvp_recyclerview_2.R;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.basic_activity.BMVP_Activity;

public class AboutActivity extends BMVP_Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);

        BMVP_setPageTitle(R.string.ABOUT_PAGE_page_title);
        BMVP_activateUpButton();
    }
}