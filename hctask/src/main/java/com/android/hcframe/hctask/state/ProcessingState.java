package com.android.hcframe.hctask.state;

/**
 * @Company 浙 江 鸿 程 计 算 机 系 统 有 限 公 司
 * @URL http://www.zjhcsoft.com
 * @Address 杭州滨江区伟业路1号
 * @Email jinjr@zjhcsoft.com
 * Created by jrjin on 16-7-27 09:02.
 */

import android.content.Context;
import android.os.Parcel;
import android.view.View;
import android.widget.TextView;

import com.android.hcframe.hctask.R;
import com.android.hcframe.hctask.TaskOperator;
import com.android.hcframe.sql.SettingHelper;

/**
 * 进行中的任务
 */
public class ProcessingState extends TaskState {

    public ProcessingState() {
        mStateIconResId = R.drawable.task_proceiving_icon;
        mStatus = STATUS_PROCESSING;
        mDividerColor = "#78cae8";
        mSort = 3;
    }

    public ProcessingState(Parcel p) {
        super(p);
    }

    public ProcessingState(TaskState task) {
        super(task);
        mStateIconResId = R.drawable.task_proceiving_icon;
        mStatus = STATUS_PROCESSING;
        mDividerColor = "#78cae8";
        mSort = 3;
    }

    @Override
    public void setTextView(Context context, final TaskOperator operator, TextView... views) {
        if (views.length != 2) {
            throw new IndexOutOfBoundsException("CompletedState #setTextView TextView must be 2! views size = "+views.length);
        }
        if (SettingHelper.getUserId(context).equals(getPublisherId())) {
            // 说明是发布者,只需要一个按钮（变更任务）
            if (views[0].getVisibility() != View.GONE)
                views[0].setVisibility(View.GONE);
            if (views[1].getVisibility() != View.VISIBLE)
                views[1].setVisibility(View.VISIBLE);
            views[1].setText(context.getResources().getString(R.string.task_change_btn));

            views[1].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    operator.changeTask(ProcessingState.this);
                }
            });
        } else {
            // 说明是发布者,只需要一个按钮（提交完成）
            if (views[0].getVisibility() != View.GONE)
                views[0].setVisibility(View.GONE);
            if (views[1].getVisibility() != View.VISIBLE)
                views[1].setVisibility(View.VISIBLE);
            views[1].setText(context.getResources().getString(R.string.task_commit_btn));
            views[1].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    operator.commitTask(ProcessingState.this);
                }
            });
        }
    }

    public static final Creator<ProcessingState> CREATOR = new Creator<ProcessingState>() {
        @Override
        public ProcessingState createFromParcel(Parcel source) {
            return new ProcessingState(source);
        }

        @Override
        public ProcessingState[] newArray(int size) {
            return new ProcessingState[0];
        }
    };
}
