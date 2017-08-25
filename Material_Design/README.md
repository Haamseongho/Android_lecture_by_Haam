# Material Design
---

## 1 . 스큐어모픽 디자인

- 실물에 가까운 질감과 형태로써 사실적 표현을 하는 디자인
     
<br />
<img src="./images/skeuomorphic1.JPG"/>  

: 빈 공간이 생기면 들어가야할 것 같고 뭔가 입체감 느껴지고 이러한 느낌

단점 : Metaphor방식이기에 UI를 어디까지나 모티브에 의해 만들기엔 어려움이 있다.
또한 세세한 부분까지 신경쓰기에는 디바이스 리소스 측면에서 많은 비용을 감수해야 한다.

---

## 2 . 플랫디자인 

- 은유적인 부분에서 보다 현실적인 UI로 넘어온 디자인
- 어떻게 콘텐츠를 보기 쉽게 구현해야 하는가에 치중한 디자인


<br />


<img src="./images/flat.JPG" />

: 모던 UI / 간결한 타이포그래피 / 스탠다드형 UI 정의 

장점 

1. 플랫한 면에 보여줄 타이포그래피를 올린 디자인으로 비용이 적고 다양한 화면 크기를 지원할 수 있습니다.
2. 지역성이 약해 전 세계적으로 받아드리기 쉽다
3. 아이콘의 모티브가 추상화되어 가고 있고 아이콘과 플랫간의 경계가 없기 때문에 받아드리기 편합니다.


---

<br />


> Material Design ?

- 단순한 그릇 / 컨텐츠는 들어가 있지 않고 그 컨텐트를 보기 좋게 담기 위한 그릇입니다.

> Emptiness ?

- 빈 공간을 뜻하는 단어로 어떻게 공백을 두어 여지를 남길 지 표현해야 한다
- 사용자가 자유롭게 활용할 수 있도록 진행되어야 한다
- 인간 중심의 설계 / 목표주도형 설계입니다.


---

### 대표 컴포넌트 (Material Design) - 그릇

1. 리스트

   - 리스트 항목 좌우에는 이미지나 타임스탬프가 들어간다
   - 구별하기 쉬운 콘텐츠를 배치하여 다른 리스트 항목과 비교하도록 한다.
   - 뒤로 가기 버튼과 세트로 계층 구조를 나타내는 용도로 이용한다.
   - 오른쪽 긑에 아이콘 등으로 보조 액션을 배치하도록 한다.
   - 스와이프 액션을 두는 경우가 있는데 무작정 넣지말고 단축키나 추가 기능으로만 정의해주는 것이 좋다
 
 <br />

<img src="./images/list.JPG"/> 

<br />

2 . 그리드리스트
    - 이미지를 주로 사용하는 콘텐츠나 갱신되는 내용에 적합
    - 가로세로 균형 잡힌 콘텐츠를 셀 형태로 배치하기 때문에 긴 텍스트는 게시하기 힘듬
    - 액션은 셀 전체에 할당되며, 보조 액션을 배치하는 경우 네 모퉁이에 배치하는 것을 권장
    - 개별 셀마다 제스쳐 액션을 설정하는 것은 권장하지 않는다.


<br />

<img src="./images/gridlist.JPG" />


3 . 카드
 	
   - 세 줄 이상의 텍스트를 이용하거나 하나의 항목에 대해 즐겨찾기에 등록할 때 카드를 사용한다.
   - 가장 Material Design에 가까운 UI로서 종이를 모티브르 하고 있습니다.
   - 카드상의 버튼은 최대한 장식을 없애고, 아이콘이나 문자와 공백에 따라 액션 부분을 명가합니다.

   <br />



   
<img src="./images/card.JPG"/>

---

### 버튼
---

1. 일반적인 버튼 : Floating Action Button , Raised Button , Flat Button
	- 다이얼로그 상자 안에서는 Flat Button을 활용 
		- 플랫한 면에 디자인 되어야 하기 때문에 애니메이션은 설정하지 않고 글자의 대소문자로 주위와 차이 나도록 디자인합니다.
	- 머터리얼 디자인(그릇) 내부의 컨텐츠로 들어간 버튼에서는 Raised Button 활용
		- 콘텐츠 내에서도 두드러지게 표현되어야 하기 때문 
	- Floating Action Button은 고정 스크롤로 있을 경우 사용
		- 앱의 에코 사이클을 최대화하는 액션에 이용
		- 예 : ) 메일 앱의 경우 전송처럼 메일 자체의 송수신량을 늘리고 이용 기회를 최대화 하는 액션에 어울림
		- 단점은 눈에 들어오게 배치된 버튼이더라도 스마트폰이 익숙하지 않은 사용자들은 버튼의 위치를 해메일 수 있습니다.


2. 토글 버튼 
	- 상태 전환 시에 사용한다.
	- 여러 토글 버튼을 그룹화해 표시할 수도 있다.
	- 아이콘을 이용하여 단일 전화을 가능하게 할 수도 있다.
	- 주의할 점은 일반 모티브로 이용할 경우 텍스트를 함께 적는 편이 낫다. 어떤 기능을 하는지 사용자가 모른다면 사용하는 의미가 없기 때문이다.




3. 드롭다운 버튼 
	- 여러 선택지를 선택할 수 있는 버튼
	- 선택 시에 버튼의 상태가 갱신된다
	- 드롭다운 버튼 내의 주위에는 여백을 두고 이용하는 것을 권장한다

---

### Elevation vs Shadow

Elevation : 높낮이 두는 속성

Shadow : 그림자 두는 속성 


- Shadow를 설정할 경우 배경을 지정할 떄 배경색에 투명도를 설정하면 안된다.


# CoordinatorLayout

---

: View 스크롤 시에 연동해서 자식이 될 view의 크기와 위치를 동적으로 관리하는 클래스 


# AppBarLayout

---
: LinearLayout을 상속한 Material Design의 AppBar 컨셉을 실현한 레이아웃 요소

> 구현 요소
> > app:layout_scrolFlags="scroll|exitUntilCollapsed" 
> > >  콘텐츠 영역을 아래로 스크롤 할 경우 AppBarLayout이 CollapsingToolbarLayout의 최소 높이 까지 작아지고 위로 스크롤 할 경우 최대 높이까지 확대 됩니다.

> > app:layout_scrolFlags="scroll|enterAlways" 
> > >  minHeight와 관계없이 콘텐츠 영역을 아래로 스크롤함에 따라 AppBarLayout이 작아지고 최종적으로는 표시가 되지 않습니다.

> > app:layout_scrolFlags="scroll|enterAlwaysCollapsed" 
> > >  minHeight와 관계없이 작아지지만 콘텐츠 영역의 맨 위로 스크롤한 뒤 다시 당기면 AppBarLayout이 표시됩니다. 


# CollapsingToolbarLayout
---
: AppBarLayout 바로 아래의 자식 요소로 AppBar의 확대 및 축소 표시를 위한 Toolbar의 wrapper


# DrawerLayout & NavigationView 

---

## NavigationView
- Navigation Drawer를 구현할 수 있는 뷰
- 액션바 안의 아이콘 (액션바토글) 또는 스와이프 동작으로 드로어 표시 가능
- 메뉴와 서브 콘텐츠를 딱 맞게 넣을 수 있음

NavigationView는 부분별 레이아웃 분리와 아이템 배치로 더 쉽게 구현할 수 있게 되었습니다.


### [ 전체 코드로 확인 ] 

```

	<?xml version="1.0" encoding="utf-8"?>
	<android.support.v4.widget.DrawerLayout xmlns:android="http://schemas.android.com/apk/res/android"
    	xmlns:app="http://schemas.android.com/apk/res-auto"
    	xmlns:tools="http://schemas.android.com/tools"
    	android:id="@+id/drawer"
    	android:layout_width="match_parent"
    	android:layout_height="match_parent"
    	android:fitsSystemWindows="true"
    	tools:context="com.example.haams.material_design.MainActivity">



```

전체를 DrawerLayout으로 묶어준다. 
기본적으로 서랍장이 닫히기 전 안에 내용물들을 담는 부분입니다. 
(스와이프 또는 토글버튼 선택으로 서랍장이 열리기 전 전체 스탠다드 페이지를 구성하는 부분)

```

	  <android.support.design.widget.CoordinatorLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content">


        <android.support.design.widget.AppBarLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:fitsSystemWindows="true"
            android:minHeight="50dp"
            android:theme="@style/ThemeOverlay.AppCompat.Dark.ActionBar">



```

CoordinatorLayout으로 그 다음 부터 전체 틀을 묶어 줍니다.

이 안에 AppBarLayout을 두는데, AppBarLayout 내의 내용물들은 위 AppBarLayout 설명과 같이 액션을 줄 수 있습니다.

```

	 	<android.support.design.widget.CollapsingToolbarLayout
                android:id="@+id/col_toolbar"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:fitsSystemWindows="true"
                android:minHeight="50dp"
                app:contentScrim="?attr/colorPrimary"
                app:layout_collapseMode="parallax"
                app:layout_scrollFlags="scroll|enterAlwaysCollapsed">


                <android.support.v7.widget.Toolbar
                    android:id="@+id/main_toolbar"
                    android:layout_width="match_parent"
                    android:layout_height="130dp"
                    app:layout_collapseMode="parallax"
                    app:layout_scrollFlags="scroll|enterAlways|enterAlwaysCollapsed"
                    app:popupTheme="@style/Theme.Design.Light">


                </android.support.v7.widget.Toolbar>
            </android.support.design.widget.CollapsingToolbarLayout>



```

위는 AppBarLayout 내의 내용물입니다.
CollapsingToolbarLayout으로  AppBar의 확대 및 축소를 다룰 수 있고 그 내부의 Toolbar를 자유자재로 가지고 놀 수 있습니다.

그 속성으로는 
> app:layout_collapseMode="parallax" 
> > 시간차이로 붕괴되는 현상 

> app:layout_collapseMode="pin" 
> > 맨 위에 고정시키는 현상 (스크롤 시에)

> layout_scrollFlags : 스크롤 시에 주는 Animation

AppBar를 스크롤 시에 CollapsingToolbarLayout은 시간차로 붕괴되며 아래로 스크롤 시 다시 AppBar가 나오게 된다.
내부 Toolbar또한 시간차로 붕괴되기 시작하며 아래로 스크롤 시 AppBar가 사라지며 최종적으론 보이지 않는다. 

```

	
            <android.support.design.widget.TabLayout
                android:id="@+id/main_tablayout"
                android:layout_width="match_parent"
                android:layout_height="?attr/actionBarSize"
                android:layout_gravity="bottom"></android.support.design.widget.TabLayout>

        </android.support.design.widget.AppBarLayout>


```

CollapsingToolBarLayout 바깥쪽에 TabLayout을 두면서 
툴바 내에 붕괴되는 것과는 별개로 돌아가도록 진행한다.
단 AppBarLayout에 같이 두고 위와 같은 속성을 주지 않음으로써 최종적으론 AppBar내에 탭레이아웃만 남도록 정리한다.

CoordinatorLayout이 끝난 다음에 NavigationView를 설정합니다.

```

	
    <android.support.design.widget.NavigationView
        android:id="@+id/main_nav"
        android:layout_width="wrap_content"
        android:layout_height="match_parent"
        android:layout_gravity="start"
        app:headerLayout="@layout/nav_header"
        app:menu="@menu/nav_menu"></android.support.design.widget.NavigationView>


```

Drawer가 닫힌 상태에서는 CoordinatorLayout 부분 까지 표현되는 것이고 Drawer가 열릴 경우 NavigationView가 보여지게 되는 것입니다.

## NavigationView 활용

### ★ NavigationView를 사용하기 위해선 주의해야할 점 3가지만 알고 가시면 됩니다.


1. 슬라이딩 메뉴로 표시되는 페이지로 header와 contents부분으로 나뉩니다. 
header는 별개의 layout이 필요하고 contents는 menu가 필요합니다.

2. NavigationView를 자바에서 사용할 때에 해당 아이템을 가지고 표현하는 것은 contents부분에 설정한 메뉴아이템을 가지고 오는 것이라 생각하시면 됩니다.

3. NavigationView의 아이템이 메뉴에 관련된 아이템들이기 때문에 원래 가지고 있는 menu의 menuItem들과 구분을 지어야 합니다. 


---

## *1* . 정리
NavigationView 태그로 선언된 xml 부분에서 app:headerLayout 이 부분에 주목합니다.

이 부분은 별개의 xml파일을 만들어 header부분에 어떠한 이미지가 들어갈 지 정리한 부분입니다. 

app:menu 부분에서는 슬라이딩 메뉴에 어떠한 메뉴 아이템들이 들어갈 지 정리한 부분입니다.

---
## *2* 정리

```

	<?xml version="1.0" encoding="utf-8"?>
	<menu xmlns:android="http://schemas.android.com/apk/res/android">
    	<item android:title="위치 알림 정하기"
        	android:id="@+id/spot_item"
       	 	android:icon="@android:drawable/ic_lock_idle_alarm"></item>
	</menu>


```

위와 같이 간단한 메뉴를 만들어서 호출하면 됩니다.

---

### *3* 정리


```


	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (menuIndex) {
            case 0:
                switch (item.getItemId()) {
                    case R.id.alarm_item:
                        Toast.makeText(MainActivity.this, "일정 알람 선택", Toast.LENGTH_LONG).show();
                        subMenuIndex = 1;
                        break;
                }
                break;
            case 1:
                switch (item.getItemId()) {
                    case R.id.spot_item:
                        Toast.makeText(MainActivity.this, "장소 알람 선택", Toast.LENGTH_LONG).show();
                        subMenuIndex = 2;
                        break;

                }
                break;

            case 2:
                switch (item.getItemId()) {
                    case R.id.chat_item:
                        Toast.makeText(MainActivity.this, "채팅 알람 선택", Toast.LENGTH_LONG).show();
                        subMenuIndex = 3;
                        break;
                }
                break;
        }
        if (mainDrawerToggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }


```

옵션 아이템 선택 메소드 부분에서 mainDrawerToggle (네비게이션뷰 호출 아이콘) 선택 시 true 값을 반환한다고 표시하면 됩니다.

전체 코드는 MainActivity.java에 있기 때문에 확인하시길 바랍니다. 
