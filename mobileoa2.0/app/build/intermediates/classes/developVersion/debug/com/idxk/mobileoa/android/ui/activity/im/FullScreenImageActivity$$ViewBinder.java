// Generated code from Butter Knife. Do not modify!
package com.idxk.mobileoa.android.ui.activity.im;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class FullScreenImageActivity$$ViewBinder<T extends com.idxk.mobileoa.android.ui.activity.im.FullScreenImageActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558463, "field 'image'");
    target.image = finder.castView(view, 2131558463, "field 'image'");
  }

  @Override public void unbind(T target) {
    target.image = null;
  }
}
