package com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.stubs;


import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.basic_activity.BMVP_ActivityStub;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.interfaces.iBMVP_PageView;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.interfaces.iBasicViewState;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.view_modes.BasicViewMode;

public class BasicMVPList_ViewStub extends BMVP_ActivityStub implements iBMVP_PageView {

    @Override
    public void setDefaultPageTitle() {

    }

    @Override
    public void runDelayed(@NonNull Runnable runnable, long delay) {

    }

    @Override
    public void assembleMenu() {

    }

    @Override
    public RecyclerView.ItemDecoration createBasicItemDecoration(BasicViewMode viewMode) {
        return null;
    }

    @Override
    public void setViewState(iBasicViewState viewState) {

    }

    @Override
    public Intent getInputIntent() {
        return null;
    }

    @Override
    public void refreshMenu() {

    }

    @Override
    public void restoreSearchView(String filterText) {

    }

    @Override
    public void scroll2position(int position) {

    }

    @Override
    public Context getAppContext() {
        return null;
    }

    @Override
    public Context getPageContext() {
        return null;
    }

    @Override
    public String getText(int stringResourceId, Object... formatArgs) {
        return null;
    }

    @Override
    public void reconfigureRecyclerView() {

    }

    @Override
    public int getListScrollOffset() {
        return 0;
    }

    @Override
    public void setListScrollOffset(int offset) {

    }

}
