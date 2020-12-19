package com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.basic_activity;

import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.interfaces.iBMVP_Activity;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class BMVP_Activity extends AppCompatActivity implements iBMVP_Activity {

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

    @Override
    public void showToast(int messageId) {
        showToast(getString(messageId));
    }

    @Override
    public void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }


    public void showSnackbar(int msgId, int dismissStringResourceId) {
        showSnackbar(msgId, dismissStringResourceId, null);
    }

    @Override
    public void showSnackbar(int msgId, int dismissStringResourceId, @Nullable Integer duration) {
        String text = getResources().getString(msgId);

        showSnackbar(text, dismissStringResourceId, duration);
    }

    @Override
    public void showSnackbar(String text, int dismissStringResourceId) {
        showSnackbar(text, dismissStringResourceId, null);
    }

    @Override
    public void showSnackbar(String text, int dismissStringResourceId, @Nullable Integer duration) {

        View parentView = findViewById(android.R.id.content);

        int length = (null != duration) ? duration : BaseTransientBottomBar.LENGTH_LONG;

        Snackbar snackbar = Snackbar.make(parentView, text, length);

        snackbar.setAction(dismissStringResourceId, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
            }
        });

        snackbar.show();
    }

    @Override
    public void closePage() {
        finish();
    }
}
