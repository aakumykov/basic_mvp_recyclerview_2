package com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.view_states;


import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.interfaces.iBasicViewState;

public class ItemsSelectedViewState implements iBasicViewState {

    private int mSelectedItemsCount = -1;

    public ItemsSelectedViewState(int selectedItemsCount) {
        mSelectedItemsCount = selectedItemsCount;
    }

    public int getSelectedItemsCount() {
        return mSelectedItemsCount;
    }
}
