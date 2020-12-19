package com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.view_states;

public class CancelableProgressViewState extends ProgressViewState {

    public CancelableProgressViewState(int messageId) {
        super(messageId);
    }

    public CancelableProgressViewState(String messageString) {
        super(messageString);
    }
}
