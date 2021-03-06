package com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.view_holders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.github.aakumykov.basic_mvp_recyclerview_2.R;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.list_items.BasicMVPList_ListItem;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.list_items.BasicMVPList_LoadmoreItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BasicMVPList_LoadmoreViewHolder extends BasicMVPList_ViewHolder {

    @BindView(R.id.titleView) TextView titleView;

    public BasicMVPList_LoadmoreViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void initialize(BasicMVPList_ListItem basicListItem) {
        BasicMVPList_LoadmoreItem loadmoreItem = (BasicMVPList_LoadmoreItem) basicListItem;
        if (loadmoreItem.hasTitleId())
            titleView.setText(loadmoreItem.getTitleId());
    }

    @OnClick(R.id.itemView)
    public void onLoadMoreClicked() {
        getItemClickListener().onLoadMoreClicked(this);
    }
}
