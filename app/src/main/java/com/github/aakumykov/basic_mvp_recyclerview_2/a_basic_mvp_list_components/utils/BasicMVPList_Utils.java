package com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.utils;


import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.BasicMVPList_DataAdapter;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.BasicMVPList_Presenter;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.interfaces.iDataAdapterPreparationCallback;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.interfaces.iPresenterPreparationCallback;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.view_model.BasicMVPList_ViewModel;

public class BasicMVPList_Utils {

    public static void configureRecyclerview(
            RecyclerView recyclerView,
            BasicMVPList_DataAdapter dataAdapter,
            RecyclerView.LayoutManager layoutManager,
            @Nullable RecyclerView.ItemDecoration itemDecoration
    ) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(dataAdapter);
        recyclerView.setLayoutManager(layoutManager);

        clearItemDecorations(recyclerView);

        if (null != itemDecoration)
            recyclerView.addItemDecoration(itemDecoration);
    }

    public static BasicMVPList_Presenter prepPresenter(BasicMVPList_ViewModel viewModel, iPresenterPreparationCallback callback) {
        if (viewModel.hasPresenter()) {
            return (BasicMVPList_Presenter) viewModel.getPresenter();
        }
        else {
            BasicMVPList_Presenter presenter = callback.onPresenterPrepared();
            viewModel.setPresenter(presenter);
            return presenter;
        }
    }

    public static BasicMVPList_DataAdapter prepDataAdapter(BasicMVPList_ViewModel viewModel, iDataAdapterPreparationCallback callback) {
        if (viewModel.hasDataAdapter()) {
            return (BasicMVPList_DataAdapter) viewModel.getDataAdapter();
        }
        else {
            BasicMVPList_DataAdapter dataAdapter = callback.onDataAdapterPrepared();
            viewModel.setDataAdapter(dataAdapter);
            return dataAdapter;
        }
    }

    private static void clearItemDecorations(RecyclerView recyclerView) {
        for (int i=0; i<recyclerView.getItemDecorationCount(); i++)
            recyclerView.removeItemDecorationAt(i);
    }

}
