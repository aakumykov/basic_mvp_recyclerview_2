package com.github.aakumykov.basic_mvp_recyclerview_2.b_simple_list.adapter_utils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.adapter_utils.BasicMVPList_ViewHolderBinder;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.list_items.BasicMVPList_ListItem;
import com.github.aakumykov.basic_mvp_recyclerview_2.b_simple_list.list_items.Simple_ListItem;
import com.github.aakumykov.basic_mvp_recyclerview_2.b_simple_list.view_holders.SimpleList_ViewHolder;

public class SimpleList_ViewHolderBinder extends BasicMVPList_ViewHolderBinder {

    @Override
    public void bindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position, BasicMVPList_ListItem listItem) {

        if (listItem instanceof Simple_ListItem) {
            SimpleList_ViewHolder simpleListBasicViewHolder = (SimpleList_ViewHolder) holder;
            simpleListBasicViewHolder.initialize(listItem);
        }
        else
            super.bindViewHolder(holder, position, listItem);
    }
}
