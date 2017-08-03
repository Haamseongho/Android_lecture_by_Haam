># NinePatch & Palette 
<hr />
<br />
<br />
## NinePatch 
---
### 1. 개념설명

> NinePatch 는 왜 쓸까요?
<hr />

- 이미지 왜곡을 막기 위해서
- 픽셀수는 단말에 따라 달라지기 때문에
- 실제 이미지가 크기의 변화에 따라 달라질 수 있기 때문에


### 2. NinePatch 사용 방법
<hr />

```

    (~~.xml)

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_weight="1">

        <com.example.haams.pallete_ninepatch.ButtonType
            android:id="@+id/btnType"
            android:layout_width="match_parent"
            android:layout_height="50dp"
            android:layout_marginTop="20dp"
            android:padding="10dp"></com.example.haams.pallete_ninepatch.ButtonType>
    </LinearLayout>

```

:  커스텀뷰를 활용하여 위젯을 Java파일에서 정리

- xml에서는 위와 같이 자바파일과 연결시킨다.


<br />
<img src="./images/custom1.JPG" />

```

      public class ButtonType extends Button {
            private Paint paint;
            private Canvas canvas;
            private static final String TAG = "ButtonType";


```

- Button을 상속하는 클래스를 하나 만든다. (위젯을 직접 그리기 위함) - [Bitmap / canvas]

<br />

<img src="./images/custom2.JPG" />

- 그릴 속성들을 정의해준다 (paint)

<br />

<img src="./images/custom3.JPG" />

- 그릴 때에 필요한 것은 onTouchEvent 그리고 onDraw 메소드입니다.
- MotionEvent.ACTION_DOWN 이 부분은 버튼 클릭으로 사용됩니다.
- invalidate()를 사용해서 onDraw()에 접목되도록 정의합니다.


<br />

<img src="./images/custom4.JPG" />

> canvas.drawText( "텍스트","너비","높이","paint" ) 
> > - 버튼내의 내용을 그리게 됩니다.


<br />


## Palette
---

 > <a href="https://developer.android.com/reference/android/support/v7/graphics/Palette.html" > Palette 설명  (개발자 사이트 참조)</a> 





<br />


## ButterKnife 
----

## 1. 사용법 
> Build.graddle (Module:app) 에 추가 

```

    compile 'com.jakewharton:butterknife:8.7.0'
    annotationProcessor 'com.jakewharton:butterknife-compiler:8.7.0'

``` 

```
    

    @BindView(R.id.mImage)
    ImageView mImage;

    @BindView(R.id.color1)
    RelativeLayout color1;
    @BindView(R.id.color2)
    RelativeLayout color2;
    @BindView(R.id.color3)
    RelativeLayout color3;
    @BindView(R.id.color4)
    RelativeLayout color4;

    @BindView(R.id.colorText1)
    TextView colorText1;
    @BindView(R.id.colorText2)
    TextView colorText2;
    @BindView(R.id.colorText3)
    TextView colorText3;
    @BindView(R.id.colorText4)
    TextView colorText4;

``` 

- @BindView로 View를 바인딩해준다.
- 안에서 사용할 객체를 설정해 준다. 
   - 예:) TextView colorText1; (colorText1로 사용)


```

    @OnClick({R.id.color1,R.id.color2,R.id.color3,R.id.color4})
       public void moveIntent(View view){
          startActivity(new Intent(MainActivity.this,SubActivity.class));
    }

```

- @OnClick으로 아이디 값을 넘겨 준다. (해당 위젯들에 대한 클릭리스너) 
