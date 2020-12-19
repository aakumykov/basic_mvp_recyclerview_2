package com.github.aakumykov.basic_mvp_recyclerview_2.b_simple_list.list_utils;

import androidx.annotation.NonNull;

import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.list_items.BasicMVPList_DataItem;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.list_utils.BasicMVPList_ItemsTextFilter;
import com.github.aakumykov.basic_mvp_recyclerview_2.b_simple_list.list_items.Simple_ListItem;

public class SimpleList_ItemsTextFilter extends BasicMVPList_ItemsTextFilter {

    @Override
    protected boolean testDataItem(@NonNull BasicMVPList_DataItem dataItem, @NonNull String filterPattern) {
        if ("".equals(filterPattern))
            return true;

        Simple_ListItem simpleListItem = (Simple_ListItem) dataItem.getPayload();
        return simpleListItem.getTitle().contains(filterPattern.toLowerCase());
    }
}
