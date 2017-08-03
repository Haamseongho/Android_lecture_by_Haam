# NinePatch & Palette 
<hr />

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


<img src="./images/custom1" />