package com.github.aakumykov.basic_mvp_recyclerview_2.b_simple_list.list_items;

import androidx.annotation.NonNull;

import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.list_items.BasicMVPList_DataItem;

public class Simple_ListItem extends BasicMVPList_DataItem {

    public Simple_ListItem(String title) {
        setPayload(title);
    }

    @Override
    public String getTitle() {
        return (String) getPayload();
    }

    @Override
    public Long getDate() {
        return 0L;
    }

    @NonNull
    @Override
    public String toString() {
        return Simple_ListItem.class.getSimpleName()+" { "+getTitle()+" }";
    }
}
