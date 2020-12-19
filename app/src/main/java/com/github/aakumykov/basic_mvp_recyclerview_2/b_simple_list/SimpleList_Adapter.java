package com.github.aakumykov.basic_mvp_recyclerview_2.b_simple_list;

import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.BasicMVPList_DataAdapter;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.adapter_utils.BasicMVPList_ViewHolderBinder;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.adapter_utils.BasicMVPList_ViewHolderCreator;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.adapter_utils.BasicMVPList_ViewTypeDetector;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.enums.eSortingOrder;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.interfaces.iBasicMVP_ItemClickListener;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.interfaces.iItemsComparator;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.interfaces.iSortingMode;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.view_modes.BasicViewMode;
import com.github.aakumykov.basic_mvp_recyclerview_2.b_simple_list.adapter_utils.SimpleList_ViewHolderBinder;
import com.github.aakumykov.basic_mvp_recyclerview_2.b_simple_list.adapter_utils.SimpleList_ViewHolderCreator;
import com.github.aakumykov.basic_mvp_recyclerview_2.b_simple_list.adapter_utils.SimpleList_ViewTypeDetector;

public class SimpleList_Adapter extends BasicMVPList_DataAdapter {

    public SimpleList_Adapter(BasicViewMode defaultViewMode, iBasicMVP_ItemClickListener itemClickListener) {
        super(defaultViewMode, itemClickListener);
    }

    @Override
    protected BasicMVPList_ViewHolderCreator prepareViewHolderCreator() {
        return new SimpleList_ViewHolderCreator(mItemClickListener);
    }

    @Override
    protected BasicMVPList_ViewHolderBinder prepareViewHolderBinder() {
        return new SimpleList_ViewHolderBinder();
    }

    @Override
    protected BasicMVPList_ViewTypeDetector prepareViewTypeDetector() {
        return new SimpleList_ViewTypeDetector();
    }

    @Override
    public iItemsComparator getItemsComparator(iSortingMode sortingMode, eSortingOrder sortingOrder) {
        return null;
    }
}
