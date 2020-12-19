package com.github.aakumykov.basic_mvp_recyclerview_2.b_simple_list.adapter_utils;

import android.view.ViewGroup;

import com.github.aakumykov.basic_mvp_recyclerview_2.R;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.adapter_utils.BasicMVPList_ViewHolderCreator;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.interfaces.iBasicMVP_ItemClickListener;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.view_holders.BasicMVPList_DataViewHolder;
import com.github.aakumykov.basic_mvp_recyclerview_2.b_simple_list.view_holders.SimpleList_ViewHolder_Feed;
import com.github.aakumykov.basic_mvp_recyclerview_2.b_simple_list.view_holders.SimpleList_ViewHolder_Grid;
import com.github.aakumykov.basic_mvp_recyclerview_2.b_simple_list.view_holders.SimpleList_ViewHolder_List;

public class SimpleList_ViewHolderCreator extends BasicMVPList_ViewHolderCreator {

    public SimpleList_ViewHolderCreator(iBasicMVP_ItemClickListener itemClickListener) {
        super(itemClickListener);
    }

    @Override
    public BasicMVPList_DataViewHolder createViewHolder4listMode(ViewGroup parent) {
        return new SimpleList_ViewHolder_List(inflateItemView(parent, R.layout.basic_list_item));
    }

    @Override
    public BasicMVPList_DataViewHolder createViewHolder4feedMode(ViewGroup parent) {
        return new SimpleList_ViewHolder_Feed(inflateItemView(parent, R.layout.basic_feed_item)) ;
    }

    @Override
    public BasicMVPList_DataViewHolder createViewHolder4gridMode(ViewGroup parent) {
        return new SimpleList_ViewHolder_Grid(inflateItemView(parent, R.layout.basic_grid_item));
    }
}
