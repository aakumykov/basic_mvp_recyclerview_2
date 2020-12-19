package com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.list_utils;


import android.util.Log;

import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.enums.eBasicSortingMode;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.enums.eSortingOrder;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.interfaces.iItemsComparator;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.interfaces.iListBottomItem;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.interfaces.iListTopItem;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.interfaces.iSortingMode;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.list_items.BasicMVPList_DataItem;
import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.list_items.BasicMVPList_ListItem;

public abstract class BasicMVPList_ItemsComparator implements iItemsComparator {

    private static final String TAG = BasicMVPList_ItemsComparator.class.getSimpleName();
    protected iSortingMode mSortingMode;
    protected eSortingOrder mSortingOrder;


    public BasicMVPList_ItemsComparator(iSortingMode sortingMode, eSortingOrder sortingOrder) {
        mSortingMode = sortingMode;
        mSortingOrder = sortingOrder;
    }


    @Override
    public int compare(BasicMVPList_ListItem o1, BasicMVPList_ListItem o2) {
        return sortSelf(o1, o2, true);
    }


    protected int sortSelf(BasicMVPList_ListItem o1, BasicMVPList_ListItem o2, boolean calledFromBasicComparator) {
        if (hasPinnedItems(o1, o2))
            return sortPinnedItems(o1, o2);
        else {
            if (calledFromBasicComparator)
                return sortMiddleItemsBasic(o1, o2);
            else
                return sortSortableItems(o1, o2);
        }
    }

    protected boolean hasPinnedItems(BasicMVPList_ListItem o1, BasicMVPList_ListItem o2) {
        return hasTopItem(o1, o2) || hasBottomItem(o1, o2);
    }

    protected int sortPinnedItems(BasicMVPList_ListItem o1, BasicMVPList_ListItem o2) {
        if (hasTopItem(o1, o2))
            return sortTopItems(o1 ,o2);

        else if (hasBottomItem(o1, o2))
            return sortBottomItems(o1 ,o2);

        else {
            Log.e(TAG, "Requested to sort pinned item, but has no one");
            return 0;
        }
    }

    protected int sortTopItems(BasicMVPList_ListItem o1, BasicMVPList_ListItem o2) {
        return 0;
    }

    protected int sortBottomItems(BasicMVPList_ListItem o1, BasicMVPList_ListItem o2) {
        return 0;
    }

    protected abstract int sortSortableItems(BasicMVPList_ListItem o1, BasicMVPList_ListItem o2);

    protected boolean hasTopItem(BasicMVPList_ListItem item1, BasicMVPList_ListItem item2) {
        return isTopItem(item1) || isTopItem(item2);
    }

    protected boolean hasBottomItem(BasicMVPList_ListItem item1, BasicMVPList_ListItem item2) {
        return isBottomItem(item1) || isBottomItem(item2);
    }

    protected boolean isTopItem(BasicMVPList_ListItem listItem) {
        return listItem instanceof iListTopItem;
    }

    protected boolean isBottomItem(BasicMVPList_ListItem listItem) {
        return listItem instanceof iListBottomItem;
    }

    protected int unknownSortingMode(String tag, iSortingMode sortingMode) {
        Log.e(tag, "Неизвестный режим сортировки: "+sortingMode);
        return 0;
    }


    // Внутренние
    private int sortMiddleItemsBasic(BasicMVPList_ListItem o1, BasicMVPList_ListItem o2) {
        if (mSortingMode instanceof eBasicSortingMode) {

            switch ((eBasicSortingMode) mSortingMode) {
                case BY_NAME:
                    return sortByName(o1, o2);

                case BY_DATE:
                    return sortByDate(o1, o2);

                default:
                    return unknownSortingMode(TAG, mSortingMode);
            }
        }
        else {
            Log.e(TAG, "Sorting mode is not eBasicSortingMode instance: " + mSortingMode);
            return 0;
        }
    }

    private int sortByName(BasicMVPList_ListItem o1, BasicMVPList_ListItem o2) {
        BasicMVPList_DataItem dataItem1 = (BasicMVPList_DataItem) o1;
        BasicMVPList_DataItem dataItem2 = (BasicMVPList_DataItem) o2;

        String title1 = dataItem1.getBasicData().getTitle();
        String title2 = dataItem2.getBasicData().getTitle();

        if (mSortingOrder.isDirect())
            return title1.compareTo(title2);
        else
            return title2.compareTo(title1);
    }

    private int sortByDate(BasicMVPList_ListItem o1, BasicMVPList_ListItem o2) {
        BasicMVPList_DataItem dataItem1 = (BasicMVPList_DataItem) o1;
        BasicMVPList_DataItem dataItem2 = (BasicMVPList_DataItem) o2;

        Long date1 = dataItem1.getBasicData().getDate();
        Long date2 = dataItem2.getBasicData().getDate();

        if (mSortingOrder.isDirect())
            return date1.compareTo(date2);
        else
            return date2.compareTo(date1);
    }

}
