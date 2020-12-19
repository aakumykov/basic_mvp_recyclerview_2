package com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.view_states;


import com.github.aakumykov.basic_mvp_recyclerview_2.a_basic_mvp_list_components.interfaces.iBasicViewState;

public class ErrorViewState implements iBasicViewState {

    private final int mMessageId;
    private final String mDebugMessage;

    public ErrorViewState(int messageId, String debugMessage) {
        mMessageId = messageId;
        mDebugMessage = debugMessage;
    }

    public int getMessageId() {
        return mMessageId;
    }

    public String getDebugMessage() {
        return mDebugMessage;
    }
}
