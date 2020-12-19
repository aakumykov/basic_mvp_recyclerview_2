package com.github.aakumykov.basic_mvp_recyclerview_2.b_simple_list.adapter_utils;

import androidx.annotation.NonNull;

import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.adapter_utils.BasicMVPList_ViewTypeDetector;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.data_types.BasicMVPList_ItemTypes;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.list_items.BasicMVPList_ListItem;
import com.github.aakumykov.basic_mvp_recyclerview_2.b_simple_list.list_items.Simple_ListItem;

public class SimpleList_ViewTypeDetector extends BasicMVPList_ViewTypeDetector {

    @Override
    public int getItemType(@NonNull BasicMVPList_ListItem listItem) {

        if (listItem instanceof Simple_ListItem)
            return BasicMVPList_ItemTypes.DATA_ITEM;
        else
            return super.getItemType(listItem);
    }
}
