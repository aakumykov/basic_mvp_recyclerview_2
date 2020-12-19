package com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.list_utils;

import androidx.annotation.NonNull;

import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.list_items.BasicMVPList_DataItem;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.list_items.BasicMVPList_ListItem;

import java.util.function.Predicate;

public abstract class BasicMVPList_ItemsTextFilter implements Predicate<BasicMVPList_ListItem> {

    private String mFilterPattern;

    @Override
    public boolean test(BasicMVPList_ListItem listItem) {

        if (listItem instanceof BasicMVPList_DataItem)
        {
            if (null == mFilterPattern)
                return true;

            if ("".equals(mFilterPattern))
                return true;

            BasicMVPList_DataItem dataItem = (BasicMVPList_DataItem) listItem;

            return testDataItem(dataItem, mFilterPattern);
        }
        return false;
    }

    protected abstract boolean testDataItem(@NonNull BasicMVPList_DataItem dataItem, @NonNull String filterPattern);

    public void setFilterPattern(String filterPattern) {
        mFilterPattern = filterPattern;
    }

    public String getFilterText() {
        return mFilterPattern;
    }

    public boolean alreadyFilteredWith(String textPattern) {
        return null != mFilterPattern && mFilterPattern.equals(textPattern);
    }
}
