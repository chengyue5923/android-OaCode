package com.idxk.mobileoa.logic.manager;

import android.app.Activity;

import java.util.Stack;

public class ActivityManager {

    private Stack<Activity> activityStack;

    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);
    }

    public Activity currentActivity() {
        Activity activity = activityStack.lastElement();
        return activity;
    }

    public void finishActivity() {
        Activity activity = activityStack.lastElement();
        finishActivity(activity);
    }

    public void removeActivity(Activity activity) {
        if (activityStack.contains(activity)) {
            activityStack.remove(activity);
        }
    }

    public void finishActivity(Activity activity) {
        if (activity != null) {
            activity.finish();
            activity = null;
        }
    }

    public void finishActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }


    public int getActivitySize() {
        if (activityStack == null) {
            return 0;
        }

        return activityStack.size();
    }

    public void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    public Activity getActivity(Class<?> cls) {
        if (activityStack == null) {
            return null;
        }

        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                return activity;
            }
        }

        return null;
    }


    public Activity finishLeftOnly(Class<?> cls) {
        Activity mainActivity = null;
        for (Activity activity : activityStack) {
            if (!activity.getClass().equals(cls)) {
                finishActivity(activity);
            } else {
                mainActivity = activity;
            }
        }

        return mainActivity;
    }
}