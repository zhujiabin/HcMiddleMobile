package com.android.hcframe.approve;

import android.app.Activity;
import android.view.ViewGroup;

import com.android.hcframe.AbstractPage;
import com.android.hcframe.menu.MenuPage;

/**
 * Created by pc on 2016/6/22.
 */
public class ApprovePage extends MenuPage {

    @Override
    public AbstractPage createPage(String appId, Activity context,
                                   ViewGroup parent) {
        // TODO Auto-generated method stub
        return new ApproveHomePage(context, parent, appId);
    }
}
