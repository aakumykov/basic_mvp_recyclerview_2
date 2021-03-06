package com.github.aakumykov.basic_mvp_recyclerview_2.b_simple_list;

import com.github.aakumykov.basic_mvp_recyclerview_2.R;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.BasicMVPList_Presenter;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.enums.eSortingOrder;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.interfaces.iBMVP_ListView;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.interfaces.iSortingMode;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.list_items.BasicMVPList_DataItem;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.list_items.BasicMVPList_ListItem;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.list_utils.BasicMVPList_ItemsTextFilter;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.view_holders.BasicMVPList_DataViewHolder;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.view_holders.BasicMVPList_ViewHolder;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.view_modes.BasicViewMode;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.view_states.NeutralViewState;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.view_states.RefreshingViewState;
import com.github.aakumykov.basic_mvp_recyclerview_2.b_simple_list.data_model.SimpleData;
import com.github.aakumykov.basic_mvp_recyclerview_2.b_simple_list.list_items.Simple_ListItem;
import com.github.aakumykov.basic_mvp_recyclerview_2.b_simple_list.list_utils.SimpleList_ItemsTextFilter;
import com.github.aakumykov.basic_mvp_recyclerview_2.b_simple_list.stubs.SimpleList_ViewStub;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SimpleList_Presenter extends BasicMVPList_Presenter {

    public SimpleList_Presenter(BasicViewMode defaultViewMode, iSortingMode defaultSortingMode) {
        super(defaultViewMode, defaultSortingMode);
    }

    @Override
    public void unbindViews() {
        mPageView = new SimpleList_ViewStub();
    }

    @Override
    protected eSortingOrder getDefaultSortingOrderForSortingMode(iSortingMode sortingMode) {
        return eSortingOrder.DIRECT;
    }

    @Override
    protected void onRefreshRequested() {
        super.onRefreshRequested();
        loadList();
    }

    @Override
    public void onItemClicked(BasicMVPList_DataViewHolder basicDataViewHolder) {
        if (mListView.isSelectionMode())
            onSelectItemClicked(basicDataViewHolder);
        else
            onItemClickedReal(basicDataViewHolder);
    }

    @Override
    public void onItemLongClicked(BasicMVPList_DataViewHolder basicDataViewHolder) {
        onSelectItemClicked(basicDataViewHolder);
    }

    @Override
    public void onLoadMoreClicked(BasicMVPList_ViewHolder basicViewHolder) {
        loadMoreItems();
    }

    @Override
    protected BasicMVPList_ItemsTextFilter getItemsTextFilter() {
        return new SimpleList_ItemsTextFilter();
    }

    @Override
    public boolean onBackPressed() {
        if (!super.onBackPressed()) {
            mPageView.closePage();
        }
        return true;
    }


    @Override
    protected void onColdStart() {
        super.onColdStart();

        loadList();
    }

    private void onItemClickedReal(BasicMVPList_DataViewHolder dataViewHolder) {
        BasicMVPList_ListItem listItem = mListView.getItem(dataViewHolder.getAdapterPosition());
        BasicMVPList_DataItem dataItem = (BasicMVPList_DataItem) listItem;
        SimpleData simpleData = (SimpleData) dataItem.getPayload();
        mPageView.showToast(simpleData.getTitle());
    }

    private void loadList() {

        setViewState(new RefreshingViewState());

        mPageView.runDelayed(new Runnable() {
            @Override
            public void run() {
                setViewState(new NeutralViewState());
                mListView.setList(createItemsList(1, 5, 20));
            }
        }, 3000);
    }

    private void loadMoreItems() {

        mListView.hideLoadmoreItem();
        mListView.showThrobberItem();

        mPageView.runDelayed(() -> {
                    int position2scroll = mListView.getVisibleListSize();

                    Simple_ListItem lastItem = (Simple_ListItem) mListView.getTailDataItem();
                    int startIndex = ((SimpleData) lastItem.getPayload()).getNumber() + 1;

                    mListView.appendList(createItemsList(startIndex, 5, 20), new iBMVP_ListView.iFilterListCallback() {
                        @Override
                        public void onListFiltered(int allItemsCount, int addedItemsCount, int filteredOutItemsCount) {
                            notifyAboutFilteredOutItems(allItemsCount, addedItemsCount, filteredOutItemsCount);
                        }
                    });

                    updateSelectionModeMenu();
                    mPageView.scroll2position(position2scroll);
                },
                1000
        );
    }

    private List<BasicMVPList_ListItem> createItemsList(int startIndex, int minSize, int maxSize) {

        List<BasicMVPList_ListItem> list = new ArrayList<>();
        Random random = new Random();

        for (int i=startIndex; i<startIndex+random.nextInt(maxSize)+minSize; i++) {
            String title = mPageView.getText(R.string.SIMPLE_LIST_list_item_title) + "-" + i;
            list.add(new Simple_ListItem(new SimpleData(i, title)));
        }

        return list;
    }
}
