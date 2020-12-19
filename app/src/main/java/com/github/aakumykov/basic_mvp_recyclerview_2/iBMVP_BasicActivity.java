package com.github.aakumykov.basic_mvp_recyclerview_2;

import androidx.annotation.NonNull;

public interface iBMVP_BasicActivity {
    void BMVP_setPageTitle(int stringResourceId);

    void BMVP_setPageTitle(@NonNull String text);

    void BMVP_activateUpButton();
}
