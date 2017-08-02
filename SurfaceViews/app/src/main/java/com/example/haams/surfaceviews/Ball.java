package com.example.haams.surfaceviews;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by haams on 2017-08-02.
 */

public class Ball {
    final int RAD = 24; // 공의 반지름
    int mX, mY; // 공의 중심 좌표
    int mWidth, mHeight; // 볼의 넓이와 높이
    int dx, dy;
    // 볼의 이동 x방향,y방향 속도 값
    int color;
    Bitmap bitmap;

    public Ball(int mX, int mY) {
        this.mX = mX;
        this.mY = mY;
    }

    public Ball(int mX, int mY, Bitmap bitmap) {
        this.mX = mX;
        this.mY = mY;
        this.bitmap = bitmap;

        if (bitmap != null) {
            this.bitmap = bitmap;
            mWidth = bitmap.getWidth();
            // 비트맵의 가로 폭으로부터 공의 폭 계산
            mHeight = bitmap.getHeight();
            // 비트맵의 세로 길이로부터 공의 폭 계산.
        } else {
            mWidth = mHeight = RAD * 2; // 비트맵 설정 안되어 있을 경우
            // 원의 반지름의 2배가 공의 폭 계산
        }
    }

    public void draw(Canvas canvas) {
        // SurfaceView에선 직접적으로 draw와 invalidate를 구현할 수 없기 때문에
        // 외부 다른 공간에서 draw를 호출해주는 형식으로 진행되어야 합니다.
        if (bitmap == null) {
            Paint paint = new Paint();
            for (int r = RAD, alpha = 1; r > 4; r--, alpha += 5) { // 바깥쪽은 흐릿하게 안쪽은 진하게 그려지는 원
                paint.setColor(Color.argb(alpha, Color.red(color), Color.green(color), Color.blue(color)));
                canvas.drawCircle(mX + RAD, mY + RAD, r, paint);
                /*
                canvas를 그리기 전 paint로 색상 및 그림 속성을 정리해준다. 어떻게 그릴 건지 어떤 색을 쓸 것인지..
                이 후에 canvas에 그림을 그리는 것이다.
                --> 여기서 그림 그려주고 이걸 호출하는 형식으로 진행되어야 한다.
                 */
            }
        } else
            canvas.drawBitmap(bitmap, mX, mY, null);  // 비트맵 그리기
    }

    void move(int width, int height) {
        mX += dx;       // x 좌표값을 dx 만큼 증가
        mY += dy;       // y 좌표값을 dy 만큼 증가

        if (mX < 0 || mX > width - mWidth) {   // 화면 좌우 경계에 닿은 경우
            dx *= -1;                       // 좌우 방향 반전
        }
        if (mY < 0 || mY > height - mHeight) {    // 화면 상하 경계에 닿은 경우
            dy *= -1;                           // 상하 방향 반전

        }
    }
}


