package com.heim.quickindexview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by zxp on 2016/8/9 0009.
 */
public class QuickIndexView extends View {

    private static final String[] LETTERS = new String[]{
            "A", "B", "C", "D", "E", "F",
            "G", "H", "I", "J", "K", "L",
            "M", "N", "O", "P", "Q", "R",
            "S", "T", "U", "V", "W", "X",
            "Y", "Z"
    };
    private float mCellHeight;
    private float mCellWidth;
    private Paint mPaint;
    private OnLetterChangedListener mListener;

    public interface OnLetterChangedListener{
        void onLetterChange(String letter);
    }

    public void setOnLetterChangedListener(OnLetterChangedListener listener) {
        this.mListener = listener;
    }

    public QuickIndexView(Context context) {
        this(context, null);
    }

    public QuickIndexView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public QuickIndexView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < LETTERS.length; i++) {
            String letter = LETTERS[i];
            float x = mCellWidth * 0.5f;

            Rect bounds = new Rect();
            mPaint.getTextBounds(letter, 0, letter.length(), bounds);
            float y = mCellHeight * 0.5f + bounds.height() + i * mCellHeight;

            mPaint.setColor(i == currentIndex ? Color.YELLOW : Color.WHITE);
            canvas.drawText(LETTERS[i], x, y, mPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                upDateSelectLetter(event);
                break;
            case MotionEvent.ACTION_MOVE:
                upDateSelectLetter(event);
                break;
            case MotionEvent.ACTION_UP:
                currentIndex = -1;
                break;
        }
        //重绘
        invalidate();
        return true;
    }

    int currentIndex = -1;

    private void upDateSelectLetter(MotionEvent event) {
        int index = (int) (event.getY() / mCellHeight);
        //放置角标超出屏幕
        if (index > 0 && index < LETTERS.length) {
            //跟上次的索引不同
            if (index != currentIndex) {
                String letter = LETTERS[index];

                currentIndex = index;
                if (mListener != null) {
                    mListener.onLetterChange(letter);
                }
            }
        }

    }

    //初始化画笔
    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.WHITE);
        mPaint.setTypeface(Typeface.DEFAULT_BOLD);
        mPaint.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        int mHeight = getMeasuredHeight();
        int mWidth = getMeasuredWidth();

        //单元格高度
        mCellHeight = mHeight * 1.0f / LETTERS.length;
        //单元格宽度
        mCellWidth = mWidth;
    }
}
