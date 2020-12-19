package com.github.aakumykov.basic_mvp_recyclerview_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class BMVP_BasicActivity extends AppCompatActivity implements iBMVP_BasicActivity {

    @Override
    public void BMVP_setPageTitle(int stringResourceId) {
        BMVP_setPageTitle( getString(stringResourceId) );
    }

    @Override
    public void BMVP_setPageTitle(@NonNull String text) {
        ActionBar actionBar = getSupportActionBar();
        if (null != actionBar)
            actionBar.setTitle(text);
    }

    @Override
    public void BMVP_activateUpButton() {
        ActionBar actionBar = getSupportActionBar();
        if (null != actionBar)
            actionBar.setDisplayHomeAsUpEnabled(true);
    }
}
