package com.example.haams.pallete_ninepatch;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;

/**
 * Created by haams on 2017-08-03.
 */

public class ButtonType extends Button {

    private Paint paint;
    private Canvas canvas;
    private static final String TAG = "ButtonType";

    public ButtonType(Context context) {
        super(context);
        initViews();
    }


    public ButtonType(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews();
    }

    private void initViews() {
        paint = new Paint();
        paint.setColor(Color.DKGRAY);
        paint.setAntiAlias(true);
        paint.setTextSize(40);
        paint.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_UP:
                // 손 땔 때 끝
                break;
            case MotionEvent.ACTION_DOWN:
                Log.i(TAG,"누르기");
                break;
        }
        invalidate();
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int curWidth = getWidth();
        int curHeight = getHeight();

        Rect rect = new Rect();
        float textWidth = ((float)(curWidth - rect.width())/2.0F);
        float textHeight = ((float)((curHeight-4) + rect.height())/2.0F-1.0F);

        canvas.drawText("커스텀뷰 버튼입니다.",textWidth,textHeight,paint);
    }
}
