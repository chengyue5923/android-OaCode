// Generated code from Butter Knife. Do not modify!
package com.idxk.mobileoa.android.ui.activity.im;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ChatActivity$$ViewBinder<T extends com.idxk.mobileoa.android.ui.activity.im.ChatActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558522, "field 'chatToolBar'");
    target.chatToolBar = finder.castView(view, 2131558522, "field 'chatToolBar'");
    view = finder.findRequiredView(source, 2131558524, "field 'messageList'");
    target.messageList = finder.castView(view, 2131558524, "field 'messageList'");
    view = finder.findRequiredView(source, 2131558523, "field 'swipeRefresh'");
    target.swipeRefresh = finder.castView(view, 2131558523, "field 'swipeRefresh'");
    view = finder.findRequiredView(source, 2131558525, "field 'messageSendView'");
    target.messageSendView = finder.castView(view, 2131558525, "field 'messageSendView'");
    view = finder.findRequiredView(source, 2131558521, "field 'rootLayout'");
    target.rootLayout = finder.castView(view, 2131558521, "field 'rootLayout'");
  }

  @Override public void unbind(T target) {
    target.chatToolBar = null;
    target.messageList = null;
    target.swipeRefresh = null;
    target.messageSendView = null;
    target.rootLayout = null;
  }
}
