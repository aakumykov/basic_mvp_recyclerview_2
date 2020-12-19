package com.github.aakumykov.basic_mvp_recyclerview_2.b_simple_list.list_items;

import androidx.annotation.NonNull;

import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.list_items.BasicMVPList_DataItem;

public class SimpleListItem extends BasicMVPList_DataItem {

    private String mTitle;

    public SimpleListItem(String title) {
        mTitle = title;
    }

    @Override
    public String getTitle() {
        return mTitle;
    }

    @NonNull
    @Override
    public String toString() {
        return SimpleListItem.class.getSimpleName()+" { "+getTitle()+" }";
    }
}
