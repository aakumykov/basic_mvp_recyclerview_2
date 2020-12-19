package com.github.aakumykov.basic_mvp_recyclerview_2.b_simple_list.view_holders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.github.aakumykov.basic_mvp_recyclerview_2.R;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.list_items.BasicMVPList_DataItem;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.list_items.BasicMVPList_ListItem;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.utils.ViewUtils;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.view_holders.BasicMVPList_DataViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;

public abstract class SimpleList_ViewHolder extends BasicMVPList_DataViewHolder {

    @BindView(R.id.titleView) TextView titleView;
    @BindView(R.id.checkingOverlay) View checkingOverlay;
    @BindView(R.id.highlightingOverlay) View highlightingOverlay;

    public SimpleList_ViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void initialize(BasicMVPList_ListItem basicListItem) {
        BasicMVPList_DataItem dataItem = (BasicMVPList_DataItem) basicListItem;

        titleView.setText(dataItem.getBasicData().getTitle());

        displayIsSelected(dataItem.isSelected());
        displayIsHighlighted(dataItem.isHighLighted());
    }

    @Override
    public void displayIsSelected(boolean selected) {
        ViewUtils.setVisibility(checkingOverlay, selected);
    }

    @Override
    public void displayIsHighlighted(boolean isHighLighted) {
        ViewUtils.setVisibility(highlightingOverlay, isHighLighted);
    }


    @OnClick(R.id.titleView)
    void onTitleClicked() {
        mItemClickListener.onItemClicked(this);
    }

    @OnLongClick(R.id.titleView)
    void onTitleLongClicked() {
        mItemClickListener.onItemLongClicked(this);
    }
}
