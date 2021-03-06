package com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components;


import android.content.Context;

import androidx.annotation.NonNull;

import com.github.aakumykov.basic_mvp_recyclerview_2.R;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.enums.eSortingOrder;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.interfaces.iBMVP_ListView;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.interfaces.iBMVP_PageView;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.interfaces.iBasicMVP_ItemClickListener;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.interfaces.iBasicViewState;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.interfaces.iSearchViewListener;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.interfaces.iSelectionCommandsListener;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.interfaces.iSortingMode;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.list_utils.BasicMVPList_ItemsTextFilter;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.utils.TextUtils;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.view_holders.BasicMVPList_DataViewHolder;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.view_holders.BasicMVPList_ViewHolder;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.view_modes.BasicViewMode;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.view_modes.FeedViewMode;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.view_modes.GridViewMode;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.view_modes.ListViewMode;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.view_states.AllItemsSelectedViewState;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.view_states.NeutralViewState;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.view_states.SomeItemsSelectedViewState;

public abstract class BasicMVPList_Presenter
        implements
        iBasicMVP_ItemClickListener,
        iSearchViewListener,
        iSelectionCommandsListener
{
    private static final String TAG = BasicMVPList_Presenter.class.getSimpleName();

    protected iBMVP_PageView mPageView;
    protected iBMVP_ListView mListView;

    protected BasicViewMode mCurrentViewMode;
    protected iBasicViewState mCurrentViewState;
    protected iSortingMode mCurrentSortingMode;
    protected eSortingOrder mCurrentSortingOrder;

    private boolean mInterruptFlag = false;


    public BasicMVPList_Presenter(BasicViewMode defaultViewMode, iSortingMode defaultSortingMode) {
        mCurrentViewMode = defaultViewMode;
        mCurrentSortingMode = defaultSortingMode;
        mCurrentSortingOrder = getDefaultSortingOrderForSortingMode(mCurrentSortingMode);
    }

    public void bindViews(iBMVP_PageView pageView, iBMVP_ListView listView) {
        mPageView = pageView;
        mListView = listView;
    }

    public abstract void unbindViews();

    public  void onOptionsMenuCreated() {
        if (isColdStart())
            onColdStart();
        else
            onConfigChanged();
    }

    public void updateSelectionModeMenu() {

        int visibleDataItemsCount = mListView.getVisibleDataItemsCount();
        int selectedItemsCount = mListView.getSelectedItemsCount();

        if (selectedItemsCount == visibleDataItemsCount) {
            setViewState(new AllItemsSelectedViewState(selectedItemsCount));
        }
        else if (0 == selectedItemsCount) {
            setViewState(new NeutralViewState());
        }
        else {
            setViewState(new SomeItemsSelectedViewState(selectedItemsCount));
        }
    }

    public BasicViewMode getCurrentViewMode() {
        return mCurrentViewMode;
    }

    public iSortingMode getCurrentSortingMode() {
        return mCurrentSortingMode;
    }

    public eSortingOrder getCurrentSortingOrder() {
        return mCurrentSortingOrder;
    }

    public void onSortMenuItemClicked(iSortingMode sortingMode) {

        // !!! Присваивание sortingOrder должно предшествовать присваиванию sortingMode !!!
        mCurrentSortingOrder = getSortingOrderForSortingMode(sortingMode);

        // !!! Присваивание sortingMode должно производиться после sortingOrder !!!
        mCurrentSortingMode = sortingMode;

        mListView.sortList(mCurrentSortingMode, mCurrentSortingOrder);

        mPageView.refreshMenu();
    }


    // Переключение режимов просмотра
    public void onViewModeListClicked() {
        changeLayoutTo(new ListViewMode());
    }

    public void onViewModeGridClicked() {
        changeLayoutTo(new GridViewMode());
    }

    public void onViewModeFeedClicked() {
        changeLayoutTo(new FeedViewMode());
    }

    protected void onResume() {
//        if (null != mCurrentViewState)
//            setViewState(mCurrentViewState);
    }

    protected void onStop() {
        unbindViews();
    }

    public boolean onBackPressed() {
        if (mListView.isSelectionMode()) {
            mListView.clearSelection();
            setViewState(new NeutralViewState());
            return true;
        }
        return false;
    }

    protected abstract eSortingOrder getDefaultSortingOrderForSortingMode(iSortingMode sortingMode);

    protected eSortingOrder getSortingOrderForSortingMode(iSortingMode sortingMode) {
        if (null != mCurrentSortingOrder && mCurrentSortingMode.equals(sortingMode))
            return mCurrentSortingOrder.reverse();
        else
            return getDefaultSortingOrderForSortingMode(sortingMode);
    }

    protected void onColdStart() {
        //setNeutralViewState(); // Не место! Дочерние компоненты сами решают, какой статус делать вначале.
    }

    protected void onConfigChanged() {
        if (null != mCurrentViewState)
            setViewState(mCurrentViewState);
    }

    protected void onRefreshRequested() {
        resetSelection();
    }

    protected void setViewState(@NonNull iBasicViewState viewState) {
        mCurrentViewState = viewState;

        mPageView.setViewState(viewState);
    }

    protected void onInterruptRunningProcessClicked() {
        setInterruptFlag();
    }

    protected final void setInterruptFlag() {
        mInterruptFlag = true;
    }

    protected final void clearInterruptFlag() {
        mInterruptFlag = false;
    }

    protected boolean hasInterruptFlag() {
        return mInterruptFlag;
    }


    // iBasicMVP_ItemClickListener
    @Override
    public abstract void onItemClicked(BasicMVPList_DataViewHolder basicDataViewHolder);

    @Override
    public abstract void onItemLongClicked(BasicMVPList_DataViewHolder basicDataViewHolder);

    @Override
    public abstract void onLoadMoreClicked(BasicMVPList_ViewHolder basicViewHolder);


    // iSearchViewListener
    @Override
    public void onSearchViewCreated() {
        if (mListView.isFiltered())
            mPageView.restoreSearchView(mListView.getFilterText());
    }

    @Override
    public void onSearchViewOpened() {
        mListView.setTextFilter(getItemsTextFilter());
    }

    @Override
    public void onSearchViewTextChanged(String pattern) {
        mListView.filterList(pattern);
    }

    @Override
    public void onSearchViewTextSubmitted(String pattern) {

    }

    @Override
    public void onSearchViewClosed() {
        mListView.removeTextFilter();
    }


    protected abstract BasicMVPList_ItemsTextFilter getItemsTextFilter();


    // iSelectionCommandsListener
    @Override
    public void onSelectItemClicked(BasicMVPList_DataViewHolder basicDataViewHolder) {
        mListView.toggleItemSelection(basicDataViewHolder.getAdapterPosition());
        updateSelectionModeMenu();
    }

    @Override
    public void onSelectAllClicked() {
        mListView.selectAll();
        setViewState(new AllItemsSelectedViewState(mListView.getSelectedItemsCount()));
    }

    @Override
    public void onClearSelectionClicked() {
        mListView.clearSelection();
        setViewState(new NeutralViewState());
    }

    @Override
    public void onInvertSelectionClicked() {
        mListView.invertSelection();
        setViewState(new SomeItemsSelectedViewState(mListView.getSelectedItemsCount()));
    }


    protected void notifyAboutFilteredOutItems(int allItemsCount, int addedItemsCount, int filteredOutItemsCount) {

        Context context = mPageView.getAppContext();

        String addedCardsMsg = TextUtils.getPluralString(context, R.plurals.cards_are_added, addedItemsCount);
        String filteredOutCardsMsg = TextUtils.getPluralString(context, R.plurals.cards_are_filtered_out, filteredOutItemsCount);

        String msg = TextUtils.getText(
                context,
                R.string.two_words_with_new_line,
                addedCardsMsg,
                filteredOutCardsMsg
        );

        mPageView.showToast(msg);
    }


    private boolean isColdStart() {
        return mListView.isVirgin();
    }

    private void changeLayoutTo(BasicViewMode viewMode) {
        if (null != mCurrentViewMode && mCurrentViewMode.equals(viewMode))
            return;

        mCurrentViewMode = viewMode;
        mListView.setViewMode(viewMode);
        mPageView.reconfigureRecyclerView();
    }

    private void resetSelection() {
        if (mListView.isSelectionMode()) {
            mListView.clearSelection();
            mPageView.resetMenu();
            mPageView.setDefaultPageTitle();
        }
    }
}
