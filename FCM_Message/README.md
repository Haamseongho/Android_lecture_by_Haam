# FCM 푸시 알림

---

## FCM 이란?

- Firebase에서 제공하는 클라우딩메세지 서비스
- 보내는 방식은 단일 기기 / 토픽 / 패키지에 설정된 앱 이렇게 3가지로 보낼 수 있음

<hr />

### 진행 방향 

안드로이드에서 토큰 값을 얻은 뒤에 해당 토큰을 가지고
서버에 등록한 뒤 Fcm-node 패키지를 통해 해당 토큰으로 푸시알림을 보낼 것입니다.


---

### 진행 과정 [안드로이드]


1. Firebase 사이트에 들어가서 프로젝트를 만든다.
<br />
<a link="https://console.firebase.google.com/?hl=ko">[ Firebase Site ]</a>
2. 프로젝트 생성 이 후에는 안드로이드 앱을 등록하도록 하는데 SHA 인증서를 받는 작업과 안드로이드 패키지를 추가하는 작업을 해줘야 합니다.

<br />

<img src="./images/fcm1.jpg" />

---

3.안드로이드 프로젝트를 만든 다음에 Manifest.xml에서 패키지를 가지고 와서 패키지를 등록해서 앱을 생성한다.

4.안드로이드 등록을 하면서 패키지를 등록하면 그 다음에는 닉네임 설정과 암호화된 키를 등록하는 차례인데, SHA-1 or SHA-256 둘 중 하나를 등록해주면 됩니다.

<방법> 
[Window]

- cmd창을 연다
- C:\\Users\\users\\.android 경로 내에 debug.keystore가 있는지 체크 한 뒤 
keytool -list -v -keystore debug.keystore 입력한다.
- 키 저장소 비밀번호는 그냥 엔터를 치고 들어가던 그 전에 등록한 비밀번호가 있으면 입력 후 들어간다. (저는 android 였습니다.)

<br />

<img src="./images/Fcm2.jpg" /> 


위와 같은 그림이 나오게 되면 여기서 SHA-1 또는 SHA-256 인증서 지문을 가지고와서 앱에 등록시킨다.

Package.json을 다운 받을 수 있게 되면 다운 받은 뒤 아래 그림처럼 복사해서 안드로이드 프로젝트에 넣어준다.

<br />

<img src="./images/Fcm3.jpg" />

Package.json에는 파이어베이스 내에 정리한 안드로이드 정보가 정리되어 있다. (클라이언트 ID , api-key , Package name ... )

5.아래 그림 처럼 build.graddle(project..) 에 등록해 준다.

<br />

<img src="./images/Fcm4.jpg" />

( google에 관련된 내용 적어주기 )

6.build.graddle(Module..)의 dependencies에 컴파일 해주기 

<br />

<img src="./images/Fcm5.jpg"/>


7.Manifest에 정리를 해줘야 하는데 이 부분이 가장 중요하다. 
에러가 나거나 뭔가 진행이 잘 안될 때 주로 Manifest.xml이 잘못된 경우가 많았다.


```


		 <service android:name=".firebase.MyFirebaseMessagingService">
            <intent-filter>
                <action android:name="com.google.firebase.MESSAGING_EVENT" />
            </intent-filter>
        </service>
        <service android:name=".firebase.MyFirebaseInstanceIDService">
            <intent-filter>
                <action android:name="com.google.firebase.INSTANCE_ID_EVENT" />
            </intent-filter>
        </service>

        <meta-data
            android:name="com.google.firebase.messaging.default_notification_icon"
            android:resource="@drawable/ic_face_black_24dp" />
        <meta-data
            android:name="com.google.firebase.messaging.default_notification_color"
            android:resource="@color/colorAccent" />





```

Manifest.xml 내의 application 태그 내에 위와 같이 파이어베이스 관련된 내용을 정리해준다. 

물론 퍼미션에도 인터넷 사용을 체크해줘야 합니다.

> Permission
> 
>      <uses-permission android:name="android.permission.INTERNET" />
>      <uses-permission android:name="android.permission.VIBRATE" />
>      <uses-permission android:name="com.google.android.c2dm.permission.RECEIVE" />


- 인터넷 퍼미션
- NotificationCompat 사용 시 진동 허용 퍼미션
- 구글 메세지를 받겠다는 허가 퍼미션


```


		
	public class MyFirebaseMessagingService extends FirebaseMessagingService {
    	@Override
   		 public void onMessageReceived(RemoteMessage remoteMessage) {
   		     super.onMessageReceived(remoteMessage);
        	 showNotification(remoteMessage);
    }

    private void showNotification(RemoteMessage remoteMessage) {
        Intent intent = new Intent(this, FcmTestActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 1001, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        final NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_face_black_24dp)
                .setContentTitle(remoteMessage.getFrom())
                .setContentIntent(pendingIntent);
        builder.setPriority(NotificationCompat.PRIORITY_MAX);
        builder.setVibrate(new long[]{0, 1000, 250, 1000});
        NotificationManagerCompat.from(this).notify(1001, builder.build());
    }
}



```

 위 코드는 메세지를 받았을 때에 어떻게 표현될 지 정리한 것인데 위와 같이 인텐트를 설정하게 되면 푸시 메세지가 왔을 경우 클릭 시에 해당 액티비티로 이동하게 됩니다.


```

	public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {
    	private static final String TAG = "FirebaseToken";

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String refreshToken = FirebaseInstanceId.getInstance().getToken();
       // Toast.makeText(MyFirebaseInstanceIDService.this,refreshToken,Toast.LENGTH_LONG).show();
        Log.d(TAG,  " refreshToken = " + refreshToken);
        sendRegistrationToServer(refreshToken);
    }


```

푸시알림이 올 경우 토큰을 받는 부분을 정의한 곳으로 위 부분을 실행 액티비티에서 호출해주면 됩니다. 


[MainActivity.java]

```

		private void getFcmToken() {
        	myFirebaseInstanceIDService = new MyFirebaseInstanceIDService();
        	myFirebaseInstanceIDService.onTokenRefresh();

       		FirebaseMessaging.getInstance().subscribeToTopic("news");
        	FirebaseInstanceId.getInstance().getToken();
        	Log.i(TAG, FirebaseInstanceId.getInstance().getToken());
    }


```

파이어베이스와 관련되어 있지 않은 그냥 사용 액티비티에서 FirebaseIDService관련된 클래스를 생성해서 토큰 재생성 메소드를 호출하거나 FirebaseInstanceId를 import한 뒤 인스턴스를 싱글턴 패턴으로 가지고 온 뒤 토큰을 가지고 와서 로그를 찍으면 됩니다.

같은 개념이긴 하지만 중요한 것은 Manifest를 위와 같이 잘 정의한 다음에 토큰을 로그에 찍어야 합니다.

위 토큰은 서버에 등록되어서 서버에서 이벤트 처리를 할 때에 해당 토큰으로 푸시를 보내도록 구현할 것이기 때문입니다.


---

8.서버 파트 

( 패키지 설치 )
> npm install fcm-node --save 



[View] - index.ejs

```

		  <form action="/push/message" method="post">
   			 <input type="submit" name="submit" value="푸시">
 		 </form>


```

간단한 버튼으로 푸시를 보내겠습니다.

[Router] - message.js


```

		var express = require("express");
		var router = express.Router();
		var Fcm = require("fcm-node");
		var serverKey = "----------------------------";

		var fcm = new Fcm(serverKey);



```

서버 키는 파이어베이스 사이트에 다시 들어가게 되면 

<br />

<img src="./images/Fcm7.jpg"/>

위와 같이 설정 부분에 클라우드 메시징에 들어가면 서버 키가 정의되어 있습니다.

이를 위에 serverKey에 정의해 넣고 Fcm의 인자로 준다.

<br />

<img src="./images/Fcm8.jpg" />

위와 같이 메세지 변수를 정의하고 
to 부분에는 아까 받은 토큰 값을 넣어 주고 notification과 data를 정의해준다. 
이 후에 라우팅 작업을 해주는 데 fcm.send()를 통해서 
정의한 변수 메세지를 보내주도록 한다.

---

위와 같이 작업을 해준 뒤에 해당 Url로 요청을 보낼 경우 해당 토큰의 클라이언트 앱에 푸시 메세지가 전송될 것 입니다.

위 라우터 내용은 정말 간단하게 작업한 내용이기 때문에 본인의 프로젝트에 맞게 정의해서 구현해주시면 될 것 같습니다.

