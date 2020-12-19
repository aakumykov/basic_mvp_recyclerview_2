package com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.exceptions;

import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.view_modes.BasicViewMode;

public class UnknownViewModeException extends RuntimeException {

    public UnknownViewModeException(BasicViewMode viewMode) {
        super("Неизвестный viewMode: "+viewMode);
    }
}
