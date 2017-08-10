# DataBinding
---
## MVVM & MVP 전에 알고 가야할 기초 개념

### 데이터 바인딩 
1. XML단에서 layout태그로 루트xml을 묶어준다.
2. <data>태그와 <variable> 태그를 이용하여 어떤 클래스를 바인딩 할 것이며, 현 xml단에서 어떻게 객체로 쓸 것인지 이름을 정해준다.
3. 바인딩 되는 클래스에서는 Observable클래스를 이용하여 데이터를 받는다.
4. get / set 함수를 이용하여 작업 처리를 한다.
5. MainActivity에서는 DataBindingUtil 클래스를 이용하여 데이터 바인딩을 해준다.


## 바인딩 하는 방식

바인딩 하는 방식은 다양하다
바인딩한다고 선언한 클래스에서 Observable 클래스를 활용하여 하는 방법이 있고 
MainActivity에서 DataBindingUtil 클래스를 활용하여 binding 객체를 만들고 이를 가지고 xml에서 각 뷰에 대한 id 값을 접근하여 바인딩 할 수도 있다.

<br />

> Observable 바인딩 

<build.graddle>

~~~

     android {

   		 dataBinding{
   		     enabled = true
   		 }

    
   			 compileSdkVersion 25
   			 buildToolsVersion "25.0.2"
   			 defaultConfig {

          ....... (중략)

~~~
<br />

<activity_main.xml>

```

   	<?xml version="1.0" encoding="utf-8"?>
	<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/activity_main"
    	tools:context="com.example.haams.mvvm_test.MainActivity">

    <data>
        <variable
            name="user"
            type="com.example.haams.mvvm_test.User"></variable>
    </data>

```

- layout으로 루트xml묶기
- data태그 내의 variable태그를 두어 바인딩할 클래스 이름 설정 
(여기서는 User클래스가 바인딩 클래스)
- name="user"로 둠으로써 현재 액티비티에선 user라는 이름으로 데이터를 접근할 것이라고 암시


```

      <TextView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="@{user.name}"
            android:textAlignment="center"
            android:textSize="15sp"
            android:textStyle="bold" />

        <ImageView
            android:id="@+id/mInsta"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:scaleType="centerCrop"
            android:layout_margin="8dp" />


```

- TextView에선 text값을 "@{user.name}" 이라고 설정함
- 여기서의 user는 User클래스를 바인딩한 값이고 name은 해당 클래스의 매개변수로 들어간 값임(ObservableField<String> ~~ )


<User.class>

```
   
	   public class User {
    	public ObservableField<String> name = new ObservableField<>();
    	public ObservableInt likes = new ObservableInt();

   		public User(String name3){
        name.set(name3);
        likes.set(0);
   	    }

    	public void onClickLike(View view) {
        likes.set(likes.get() + 1);
   	   }
     }
 

```

위에 xml 에서 "@{user.name}" 에서의 name이 위 ObservableField로 정의한 name입니다. 
여기 들어갈 내용을 가지고 오도록 할 것입니다.

: (1) 클래스 바인딩 활용


```

    public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        binding.setUser(new User("일상 사진"));

        Glide.with(this)
                .load(R.drawable.m_image)
                .into(binding.mInsta);

    }
}


```

1. ActivityMainBinding 클래스 - DataBindingUtil로 정의
2. binding 객체는 setUser로 User클래스를 생성해 가지고 온다.

[조건]

1. xml에서 바인딩 클래스를 User로 줘야하고 name도 일치해야한다.
2. User라는 클래스가 생성되어야 한다. 

: 위 조건이 만족되어야 setUser라는 메서드가 생성될 수 있다.

Glide 활용해서 이미지를 넣을때 into 부분에 binding.mInsta라고 선언된 부분이 있다.
이 부분이 Observable과 xml에서 "@{~~.~~}" 이 방식으로 바인딩을 안하고 DataBindingUtil를 활용하여 바인딩한 방식이다.

xml내의 id를 찾아올 필요 없이 binding.___(xml id값) 으로 하게 되면 바인딩 되어 데이터를 받아올 수 있다.


-----------


MVVM 과 MVP를 다루기 이 전에 데이터 바인딩 하는 방식에 대해 소개하였습니다.
더 심화해서 이 후 내용 소개해 드리겠습니다^_^