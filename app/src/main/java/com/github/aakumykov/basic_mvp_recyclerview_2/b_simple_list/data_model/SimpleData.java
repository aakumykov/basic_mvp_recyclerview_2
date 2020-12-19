package com.github.aakumykov.basic_mvp_recyclerview_2.b_simple_list.data_model;

import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.interfaces.iSortableData;

public class SimpleData implements iSortableData {

    private int mNumber;
    private String mTitle;

    public SimpleData(int number, String title) {
        mNumber = number;
        this.mTitle = title;
    }

    public int getNumber() {
        return mNumber;
    }

    public String getTitle() {
        return mTitle;
    }

    @Override
    public Long getDate() {
        return 0L;
    }
}
