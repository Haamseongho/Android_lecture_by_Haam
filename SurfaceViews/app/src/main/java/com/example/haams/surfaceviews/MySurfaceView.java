package com.example.haams.surfaceviews;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

/**
 * Created by haams on 2017-08-02.
 */

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    private PaperAnimation thread;
    SurfaceHolder holder;
    private ArrayList<Ball> ballList = new ArrayList<>();
    private Bitmap bitmap;

    public MySurfaceView(Context context) {
        super(context);
        holder = getHolder();
        holder.addCallback(this);

        thread = new PaperAnimation();
    }

    public MySurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread.start();
    }


    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        try {
            thread.interrupt();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class PaperAnimation extends Thread {
        @Override
        public void run() {
            while (!thread.currentThread().isInterrupted()) {
                Canvas canvas = holder.lockCanvas(null);
                canvas.drawColor(Color.WHITE); // 흰 배경에 그리기
                synchronized (holder) {
                    drawStart(canvas);
                    try {
                        thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        if (canvas != null) {
                            holder.unlockCanvasAndPost(canvas);
                        }
                    }
                }
            }
        }
    }

    private void drawStart(Canvas canvas) {
        for (int i = 0; i < ballList.size(); i++) {
            Ball ball = ballList.get(i);
            ball.move(getWidth(), getHeight());
            ball.draw(canvas); // 그림 그릴 것 호출!! 여기가 중요.
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int keyAction = event.getAction();

        if (keyAction == MotionEvent.ACTION_DOWN) {
            int x = (int) event.getX();
            int y = (int) event.getY();

            ballList.add(new Ball(x, y, bitmap));
            return true;
        }

        return false;
    }
}

