package com.example.haams.surfaceviews;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by haams on 2017-08-02.
 */

public class MyView extends View {

    private Paint paint;
    private ArrayList<Ball> ballList = new ArrayList<>();
    private Bitmap bitmap;


    public MyView(Context context) {
        super(context);

    }


    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
    }
    public void setBitmap(Bitmap bitmap){
        this.bitmap = bitmap;
    }

    public void updateAnimation(){
        Ball ball;
        for(int i=0;i<ballList.size();i++){
            ball = ballList.get(i);
            ball.move(getWidth(),getHeight());
        }
        invalidate();
        // 그림이 그려지는 곳은 결국 메인 스레드이기 때문에 메인액티비티 내의 xml에서 View를 받아 구현하는 MyView
        // 여기서 invalidate()를 호출해서 그린다.
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Ball ball;
        for(int i=0;i<ballList.size();i++){
            ball = ballList.get(i);
            ball.draw(canvas);
        }
        updateAnimation();
    }
    // 그림 그리는 것은 뷰를 상속받는 (xml에서 커스텀뷰로 연결한 그 클래스!!)
    // 여기서 onDraw에서 호출해야 한다.
    // 서피스 뷰는 onDraw나 invalidate() 가 안되므로 Ball 객체의 draw를 불러온 것!
    public boolean onTouchEvent(MotionEvent event){
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            int x = (int)event.getX();
            int y = (int)event.getY();
            ballList.add(new Ball(x,y,bitmap));

            invalidate();
            return true;
        }
        return false;
    }

}
