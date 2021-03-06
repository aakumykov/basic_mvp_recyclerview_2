package com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.interfaces;


import android.widget.Filterable;

public interface iBasicFilterableLsit extends Filterable {

    void prepareFilter();
    void removeFilter();

    String getFilterText();

    void filterItems(String pattern);
    boolean isFiltered();
}
