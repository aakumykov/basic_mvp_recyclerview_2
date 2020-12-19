package com.github.aakumykov.basic_mvp_recyclerview_2.about_page;

import android.content.Intent;
import android.os.Bundle;

import com.github.aakumykov.basic_mvp_recyclerview_2.BasicActivity;
import com.github.aakumykov.basic_mvp_recyclerview_2.R;
import com.github.aakumykov.basic_mvp_recyclerview_2.start_page.AboutActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class StartingActivity extends BasicActivity {

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
}