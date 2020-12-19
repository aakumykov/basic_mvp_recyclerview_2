package com.github.aakumykov.basic_mvp_recyclerview_2.b_simple_list;

import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.BasicMVPList_Presenter;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.enums.eSortingOrder;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.interfaces.iSortingMode;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.list_utils.BasicMVPList_ItemsTextFilter;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.view_holders.BasicMVPList_DataViewHolder;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.view_holders.BasicMVPList_ViewHolder;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.view_modes.BasicViewMode;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.view_states.RefreshingViewState;
import com.github.aakumykov.basic_mvp_recyclerview_2.b_simple_list.stubs.SimpleList_ViewStub;

public class SimpleList_Presenter extends BasicMVPList_Presenter {

    public SimpleList_Presenter(BasicViewMode defaultViewMode, iSortingMode defaultSortingMode) {
        super(defaultViewMode, defaultSortingMode);
    }

    @Override
    public void unbindViews() {
        mPageView = new SimpleList_ViewStub();
    }

    @Override
    protected eSortingOrder getDefaultSortingOrderForSortingMode(iSortingMode sortingMode) {
        return eSortingOrder.DIRECT;
    }

    @Override
    protected void onRefreshRequested() {

    }

    @Override
    public void onItemClicked(BasicMVPList_DataViewHolder basicDataViewHolder) {

    }

    @Override
    public void onItemLongClicked(BasicMVPList_DataViewHolder basicDataViewHolder) {

    }

    @Override
    public void onLoadMoreClicked(BasicMVPList_ViewHolder basicViewHolder) {

    }

    @Override
    protected BasicMVPList_ItemsTextFilter getItemsTextFilter() {
        return null;
    }

    @Override
    public boolean onBackPressed() {
        if (!super.onBackPressed()) {
            mPageView.closePage();
        }
        return true;
    }


    @Override
    protected void onColdStart() {
        super.onColdStart();

        loadList();
    }

    private void loadList() {
        setViewState(new RefreshingViewState());

        mPageView.runDelayed(new Runnable() {
            @Override
            public void run() {
                setNeutralViewState();
            }
        }, 1000);
    }
}
