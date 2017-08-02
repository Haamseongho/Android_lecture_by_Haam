#SurfaceView
### 1. 개념설명

>SurfaceView는 왜 사용하는가?
<hr />
- 메인 스레드에서 뷰를 구현하는 것이 아니라 외부 스레드에서 구현 한 뒤 메인 스레드에 붙이기 때문에 속도가 빠르다.
- 그림을 그릴 때에 onDraw()나 invalidate() 메소드를 사용하지 
않기 때문에 따로 한 번 더 호출할 메소드 수가 없을 뿐더러 
사용시에 외부 스레드가 LockCanvas(null)를 걸고 하기 때문에 
작업 프로세스 전체를 사용한다. 
(∴ 속도가 빠르다.)

<hr />

<br />
> SurfaceView 사용 시 알야아 할 핵심 기능
<hr />
1. ##SurfaceHolder 
   * SurfaceView를 관리하는 홀더 / 제어 기능 
   * getHolder()로 불러 온 뒤 작업 한다.


2. ##Callback
   * SurfaceView는 기존 안드로이드 View와는 다르게 구현되기 때문에 이를 총괄하는데 쓰이는 기능이다.
   * 홀더가 서피스뷰를 관리하기 때문에 홀더에 콜백을 붙이는 것이고 상태를 제어하기 때문에 서피스뷰에 관한 상태 메소드를 구현 해야 한다.
   
3. ##LockCanvas(null) & unlockCanvasAndPost(canvas); 
   * 서피스 뷰가 기존 안드로이드 뷰가 아닌 다른 뷰에서 구현한다는 특징을 제대로 살린 것 중 하나이다.
   * 뷰에 대한 프로세스를 독점하여 작업할 수 있기 때문에 
기아 상태나 DeadLock이 발생하지 않는다.


<hr />
<br />
<br />

### 2. 핵심 코드


```

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


```

: SurfaceHolder를 getHolder()로 불러오고 여기에 callback을 달아준다. 

: PaperAnimation은 스레드로 나중에 Lock걸기 위한 클래스입니다.


```


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



```

1. surfaceView 생성시 --> 스레드 시작 (독점하기 위함)
2. surfaceChanged 
3. surfaceView가 제거될 경우 --> 스레드 인터럽트 걸기


```

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


```


: 스레드 구현 

- 서피스뷰를 총괄하는 holder에 lockCanvas를 건다 (독점)
- Lock을 걸고 나서 drawStart(Canvas canvas)메소드를 호출 한다.(그림 그리기)
- 다 쓴 뒤에는 unLockCanvasAndPost(해당 캔버스) 로 Lock을 푼다.


: drawStart(canvas) 

- SurfaceView는 직접적인 그림을 그릴 수 없는 스레드이기 때문에 
(onDraw() , invalidate() 시현 불가능)
Ball이라는 클래스 내의 draw를 호출하여 그림을 그린다. <이게 핵심!>


> Ball 클래스

```

     public void draw(Canvas canvas) {
        // SurfaceView에선 직접적으로 draw와 invalidate를 구현할 수 없기 때문에
        // 외부 다른 공간에서 draw를 호출해주는 형식으로 진행되어야 합니다.
           if (bitmap == null) {
              Paint paint = new Paint();
                  for (int r = RAD, alpha = 1; r > 4; r--, alpha += 5) { // 바깥쪽은 흐릿하게 안쪽은 진하게 그려지는 원
                       paint.setColor(Color.argb(alpha, Color.red(color), Color.green(color), Color.blue(color)));
                        canvas.drawCircle(mX + RAD, mY + RAD, r, paint);
                   
               /*
                 canvas를 그리기 전 paint로 색상 및 그림 속성을 정리  해준다. 어떻게 그릴 건지 어떤 색을 쓸 것인지..
                 이 후에 canvas에 그림을 그리는 것이다.
                 --> 여기서 그림 그려주고 이걸 호출하는 형식으로 진행되 어야 한다.
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

``` 


: Ball 클래스에서는 미리 그림 그릴 것을 구현해둬야 합니다. 

~~~

    @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);
         setContentView(new MySurfaceView(this)); --> SurfaceView
         myView = (MyView) findViewById(R.id.myView);
    }

~~~

> setContentView(new MySurfaceView(this));
: 이 부분이 xml과 SurfaceView 연결 짓는 부분

<br />

###3. CustomView를 활용한다면? 

------

커스텀 뷰를 사용한다면 xml단에서 View를 상속받는 클래스와 연결을 지어야 합니다. 

좋은 점은 따로 draw를 구현할 필요 없이 onDraw() 메소드랑 invalidate() 메소드를 해당 View 상속 클래스에서 구현해주면 됩니다. 

요즘 나온 휴대폰 기종은 성능이 좋아서 SurfaceView와 커스텀 뷰의 차이가 
크게 나지 않는다고 합니다.

onDraw()와 invalidate() 메소드 사용이 익숙해질 무렵 커스텀뷰를 사용하여
작업 처리할 경우 보다 더 나은 처리방법이 될 수 있습니다. 

해당 소스파일은 커스텀뷰도 같이 구현해 두었고 주석 처리도 해두었습니다.

감사합니다.

