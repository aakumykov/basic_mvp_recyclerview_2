package com.github.aakumykov.basic_mvp_recyclerview_2.z_start_page;

import android.content.Intent;
import android.os.Bundle;

import com.github.aakumykov.basic_mvp_recyclerview_2.R;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.basic_activity.BMVP_Activity;
import com.github.aakumykov.basic_mvp_recyclerview_2.b_simple_list.SimpleList_View;
import com.github.aakumykov.basic_mvp_recyclerview_2.z_about_page.AboutActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class StartingActivity extends BMVP_Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.aboutButton)
    void onAboutButtonClicked() {
        startActivity(new Intent(this, AboutActivity.class));
    }

    @OnClick(R.id.simpleListButton)
    void onSimpleListButtonClicked() {
        startActivity(new Intent(this, SimpleList_View.class));
    }
}