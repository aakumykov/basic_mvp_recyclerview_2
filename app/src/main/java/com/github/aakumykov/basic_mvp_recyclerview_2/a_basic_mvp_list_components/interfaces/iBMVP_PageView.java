package com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.interfaces;


import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.view_modes.BasicViewMode;

public interface iBMVP_PageView extends iBMVP_Activity {

    void setDefaultPageTitle();

    void runDelayed(@NonNull Runnable runnable, long delay);

    void assembleMenu();

    RecyclerView.ItemDecoration createBasicItemDecoration(BasicViewMode viewMode);

    void setViewState(iBasicViewState viewState);

    void refreshMenu();

    void restoreSearchView(String filterText);

    void scroll2position(int position);

    // Эти два здесь неуместны
    Intent getInputIntent();
    Context getAppContext();
    Context getPageContext();

    String getText(int stringResourceId, Object... formatArgs);

    void reconfigureRecyclerView();

    int getListScrollOffset();
    void setListScrollOffset(int offset);
}
