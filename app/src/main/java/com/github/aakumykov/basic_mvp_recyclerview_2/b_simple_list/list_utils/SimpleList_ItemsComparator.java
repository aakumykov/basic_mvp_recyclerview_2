package com.github.aakumykov.basic_mvp_recyclerview_2.b_simple_list.list_utils;

import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.enums.eSortingOrder;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.interfaces.iSortingMode;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.list_items.BasicMVPList_ListItem;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.list_utils.BasicMVPList_ItemsComparator;

public class SimpleList_ItemsComparator extends BasicMVPList_ItemsComparator {

    public SimpleList_ItemsComparator(iSortingMode sortingMode, eSortingOrder sortingOrder) {
        super(sortingMode, sortingOrder);
    }

    @Override
    protected int sortSortableItems(BasicMVPList_ListItem o1, BasicMVPList_ListItem o2) {
        return super.compare(o1, o2);
    }
}
