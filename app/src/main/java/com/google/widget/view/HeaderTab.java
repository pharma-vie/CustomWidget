package com.google.widget.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.widget.R;

/**
 * ============================================================
 * Copyright：Google有限公司版权所有 (c) 2017
 * Author：   陈冠杰
 * Email：    815712739@qq.com
 * GitHub：   https://github.com/JackChen1999
 * 博客：     http://blog.csdn.net/axi295309066
 * 微博：     AndroidDeveloper
 * <p>
 * Project_Name：Widgets
 * Package_Name：com.google.widget
 * Version：1.0
 * time：2016/2/15 14:09
 * des ：
 * gitVersion：$Rev$
 * updateAuthor：$Author$
 * updateDate：$Date$
 * updateDes：${TODO}
 * ============================================================
 **/
public class HeaderTab extends LinearLayout implements View.OnClickListener {

    private static final int LEFT_TAB_INDEX = 0;
    private static final int RIGHT_TAB_INDEX = 1;
    private int mSelectedTabIndex = -1;
    private TextView mTvLeft;
    private TextView mTvRight;
    private int mTextSelectedColor;
    private int mTextNormalColor;
    private ITabActionCallback mCallback;

    public HeaderTab(Context context) {
        this(context, null);
    }

    public HeaderTab(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HeaderTab(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        View tab = LayoutInflater.from(getContext()).inflate(R.layout.layout_tab, this, true);
        mTvLeft = (TextView) tab.findViewById(R.id.tv_left);
        mTvRight = (TextView) tab.findViewById(R.id.tv_right);

        mTextSelectedColor = Color.parseColor("#11B7F3");
        mTextNormalColor = Color.WHITE;
    }

    public void bindTab(ITabActionCallback callback, String leftText, String rightText){
        mCallback = callback;
        mTvLeft.setText(leftText);
        mTvLeft.setOnClickListener(this);

        mTvRight.setText(rightText);
        mTvRight.setOnClickListener(this);
    }

    public void cleanPreviousStyle() {
        switch (mSelectedTabIndex) {
            case LEFT_TAB_INDEX:
                mTvLeft.setBackgroundResource(R.drawable.skin_header_tab_left_normal);
                mTvLeft.setTextColor(mTextNormalColor);
                break;
            case RIGHT_TAB_INDEX:
                mTvRight.setBackgroundResource(R.drawable.skin_header_tab_right_normal);
                mTvRight.setTextColor(mTextNormalColor);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_left:
                if (mSelectedTabIndex == LEFT_TAB_INDEX) {
                    return;
                }
                leftClick();
                break;
            case R.id.tv_right:
                if (mSelectedTabIndex == RIGHT_TAB_INDEX) {
                    return;
                }
                rightClick();
                break;
        }
    }

    public void rightClick() {
        cleanPreviousStyle();
        mTvRight.setBackgroundResource(R.drawable.skin_header_tab_right_select);
        mTvRight.setTextColor(mTextSelectedColor);
        mCallback.onRightTabClick();
        mSelectedTabIndex = RIGHT_TAB_INDEX;
    }

    public void leftClick() {
        cleanPreviousStyle();
        mTvLeft.setBackgroundResource(R.drawable.skin_header_tab_left_select);
        mTvLeft.setTextColor(mTextSelectedColor);
        mCallback.onLeftTabClick();
        mSelectedTabIndex = LEFT_TAB_INDEX;
    }

    public interface ITabActionCallback {
        void onLeftTabClick();

        void onRightTabClick();
    }
}
