package com.android.hcframe.hctask;

import android.app.Activity;
import android.view.ViewGroup;

import com.android.hcframe.AbstractPage;
import com.android.hcframe.menu.MenuPage;

/**
 * @Company 浙 江 鸿 程 计 算 机 系 统 有 限 公 司
 * @URL http://www.zjhcsoft.com
 * @Address 杭州滨江区伟业路1号
 * @Email jinjr@zjhcsoft.com
 * Created by jrjin on 16-8-3 14:11.
 */
public class TaskMenuPage extends MenuPage {

    @Override
    public AbstractPage createPage(String appId, Activity context, ViewGroup parent) {
        return new TaskHomeView(context, parent, appId);
    }
}
