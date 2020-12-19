package com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.interfaces;


import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.list_items.BasicMVPList_DataItem;

import java.util.List;

public interface iBasicSelectableList {

    boolean isSelectionMode();

    void toggleItemSelection(int position);

    Integer getSelectedItemsCount();
    List<BasicMVPList_DataItem> getSelectedItems();

    void selectAll();
    void clearSelection();
    void invertSelection();
}
