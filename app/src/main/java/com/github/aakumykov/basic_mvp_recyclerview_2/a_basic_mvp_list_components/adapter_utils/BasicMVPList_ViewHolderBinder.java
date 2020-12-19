package com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.adapter_utils;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.list_items.BasicMVPList_ListItem;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.view_holders.BasicMVPList_LoadmoreViewHolder;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.view_holders.BasicMVPList_ThrobberViewHolder;

public class BasicMVPList_ViewHolderBinder {

    public void bindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position, BasicMVPList_ListItem listItem) {

        if (holder instanceof BasicMVPList_LoadmoreViewHolder)
        {
            BasicMVPList_LoadmoreViewHolder loadmoreItemViewHolder = ((BasicMVPList_LoadmoreViewHolder) holder);
            loadmoreItemViewHolder.initialize(listItem);
        }
        else if (holder instanceof BasicMVPList_ThrobberViewHolder) {
            // Ничего не делаю
        }
        else {
            throw new RuntimeException("Неизвестный тип view holder-а: "+holder);
        }
    }

}