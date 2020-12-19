package com.github.aakumykov.basic_mvp_recyclerview_2.b_simple_list;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.github.aakumykov.basic_mvp_recyclerview_2.R;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.BasicMVPList_DataAdapter;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.BasicMVPList_Presenter;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.BasicMVPList_View;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.enums.eBasicSortingMode;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.interfaces.iDataAdapterPreparationCallback;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.interfaces.iPresenterPreparationCallback;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.utils.BasicMVPList_Utils;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.view_modes.BasicViewMode;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.view_modes.ListViewMode;

import butterknife.ButterKnife;

public class SimpleList_View extends BasicMVPList_View {

    private static final BasicViewMode DEFAULT_VIEW_MODE = new ListViewMode();
    private static final eBasicSortingMode DEFAULT_SORTING_MODE = eBasicSortingMode.BY_NAME;

    private RecyclerView mRecyclerView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BMVP_activateUpButton();
    }

    @Override
    protected void setActivityView() {
        setContentView(R.layout.activity_simple_list);
        ButterKnife.bind(this);

        mRecyclerView = findViewById(R.id.recyclerView);
    }

    @Override
    protected BasicMVPList_Presenter preparePresenter() {
        return BasicMVPList_Utils.prepPresenter(mViewModel, new iPresenterPreparationCallback() {
            @Override
            public BasicMVPList_Presenter onPresenterPrepared() {
                return new SimpleList_Presenter(DEFAULT_VIEW_MODE, DEFAULT_SORTING_MODE);
            }
        });
    }

    @Override
    protected BasicMVPList_DataAdapter prepareDataAdapter() {
        return BasicMVPList_Utils.prepDataAdapter(mViewModel, new iDataAdapterPreparationCallback() {
            @Override
            public BasicMVPList_DataAdapter onDataAdapterPrepared() {
                return new SimpleList_Adapter(DEFAULT_VIEW_MODE, mPresenter);
            }
        });
    }

    @Override
    public void setDefaultPageTitle() {
        BMVP_setPageTitle(R.string.SIMPLE_LIST_default_page_title);
    }

    @Override
    public int getListScrollOffset() {
        return mRecyclerView.computeVerticalScrollOffset();
    }

    @Override
    public void setListScrollOffset(int offset) {
        mRecyclerView.scrollBy(0, offset);
    }

    @Override
    public void assembleMenu() {
        clearMenu();
        addSearchView();

        inflateMenu(R.menu.sort);
        addSortByNameMenu();
        makeMenuItemVisible(R.id.actionSort);
    }

    @Override
    public RecyclerView.ItemDecoration prepareItemDecoration(BasicViewMode viewMode) {
        return createBasicItemDecoration(viewMode);
    }

    @Override
    protected RecyclerView getRecyclerView() {
        return mRecyclerView;
    }
}
