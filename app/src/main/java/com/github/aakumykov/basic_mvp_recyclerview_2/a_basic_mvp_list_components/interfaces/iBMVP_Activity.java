package com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.interfaces;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public interface iBMVP_Activity {

    void BMVP_setPageTitle(int stringResourceId);
    void BMVP_setPageTitle(@NonNull String text);

    void BMVP_activateUpButton();

    void showToast(int messageId);
    void showToast(String message);

    void showSnackbar(int msgId, int dismissStringResourceId);
    void showSnackbar(int msgId, int dismissStringResourceId, @Nullable Integer duration);
    void showSnackbar(String text, int dismissStringResourceId);
    void showSnackbar(String text, int dismissStringResourceId, @Nullable Integer duration);

    void closePage();
}
