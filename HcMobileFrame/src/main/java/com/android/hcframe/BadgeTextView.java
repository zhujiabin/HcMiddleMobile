package com.android.hcframe;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

import com.android.hcframe.badge.BadgeObserver;

/**
 * @Company 浙 江 鸿 程 计 算 机 系 统 有 限 公 司
 * @URL http://www.zjhcsoft.com
 * @Address 杭州滨江区伟业路1号
 * @Email jinjr@zjhcsoft.com
 * Created by jrjin on 16-7-7 16:05.
 *
 * 带有文字的角标显示,角标显示在文字的右上角,文字居中显示.
 */
public class BadgeTextView extends TextView implements BadgeObserver {

    private static final String TAG = "BadgeTextView";

    private Paint mPaint;

    /** 不显示数字的点的半径 */
    private static final int POINT_RADIUS = 5;
    /** 显示数字的点的半径 */
    private static final int POINT_RADIUS_TEXT = 8;
    /** 数据的条数 不显示数字的时候可以随便任意的数字只要>0*/
    private int mCount = 0;

    public static final int FLAG_TEXT = 1;

    public static final int FLAG_NONE = 2;
    /** 显示数字:1;不显示数字：2*/
    private int mFlag = FLAG_NONE;
    /** 点的半径 */
    private float mRadius;
    /** 数字内容，超过99，显示99+ */
    private String mTextCount;
    /** TextView设置的文字 */
    private Paint mTextPaint;

    private Rect mTextBounds = new Rect();

    public BadgeTextView(Context context) {
        this(context, null);
        // TODO Auto-generated constructor stub
    }

    public BadgeTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        // TODO Auto-generated constructor stub
    }

    public BadgeTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mPaint.setTextAlign(Paint.Align.CENTER);
        mPaint.setTextSize(10 * HcUtil.getScreenDensity());
        mRadius = (mFlag == FLAG_NONE ? POINT_RADIUS : POINT_RADIUS_TEXT) * HcUtil.getScreenDensity();

        mTextPaint = new Paint();
        mTextPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);
        if (mCount <= 0) return; // 不需要显示
        String text = getText().toString();
        if (!TextUtils.isEmpty(text)) {
            mTextPaint.setTextSize(getTextSize());
            mTextPaint.getTextBounds(text, 0, text.length(), mTextBounds);
        }
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        int textWidth = mTextBounds.width();
        int textHeight = mTextBounds.height();
        if (width <= 0 || height <= 0 || textWidth <= 0 || textHeight <= 0) return;
        mPaint.setColor(Color.RED);
        int w = (width - textWidth) / 2;
        int h = (height - textHeight) / 2;
        float cx = 0.0f;
        float cy = 0.0f;
        if (w <= mRadius && h <= mRadius) {
            cx = width - mRadius;
            cy = mRadius;
        } else if (w < mRadius && h > mRadius) {
            cx = width - mRadius;
            cy = h;
        } else if (w > mRadius && h < mRadius) {
            cx = width - w;
            cy = mRadius;
        } else {
            cx = width - w;
            cy = h;
        }

        canvas.drawCircle(cx, cy, mRadius, mPaint);
        if (mFlag == FLAG_TEXT) {
            mPaint.setColor(Color.WHITE);
            Rect bounds = new Rect();
            mPaint.getTextBounds(mTextCount, 0, mTextCount.length(), bounds);
            HcLog.D(TAG + " #onDraw bound w = "+bounds.width() + " bound h = "+bounds.height());
            canvas.drawText(mTextCount, cx, cy + (int) (bounds.height() / 24d * 11), mPaint);
        }

    }

    /**
     * 设置推送时未读的数据条数
     * @author jrjin
     * @time 2015-10-26 下午3:36:23
     * @param count 推送未读的数据量 <=0时，不显示点
     */
    @Override
    public void setCount(int count) {
        mCount = count;

        if (mCount <= 0) {
            mTextCount = null;
        } else if (mCount >= 100) {
            mTextCount = "99+";
        } else {
            mTextCount = mCount + "";
        }

        postInvalidateDelayed(200);
    }

    /**
     * 设置显示的点的类型
     * @author jrjin
     * @time 2015-10-26 下午3:43:26
     * @param flag 显示数字:0;不显示数字：1
     */
    @Override
    public void setFlag(int flag) {
        mFlag = flag;
        mRadius = (mFlag == FLAG_NONE ? POINT_RADIUS : POINT_RADIUS_TEXT) * HcUtil.getScreenDensity();
        postInvalidateDelayed(200);
    }

    @Override
    public void setTextSize(float size) {
//        String text = getText().toString();
//        if (!TextUtils.isEmpty(text)) {
//            mPaint.setTextSize(size);
//            mPaint.getTextBounds(text, 0, text.length(), mTextBounds);
//        }

        super.setTextSize(size);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
//        if (text != null) {
//            mPaint.setTextSize(getTextSize());
//            mPaint.getTextBounds(text.toString(), 0, text.toString().length(), mTextBounds);
//        }
        super.setText(text, type);
    }
}
