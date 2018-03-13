package project.hanium.hansung.service_test;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Random;

/**
 * Created by haams on 2018-03-12.
 */

public class ServiceTest extends Service {

    // onPause : 백업할 때 !
    private int extraValue;
    private BackGroundSubThread subThread;


    // tkwls

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "서비스 콜백 메소드 시작", Toast.LENGTH_LONG).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);

        if (flags == START_FLAG_RETRY) {
            // 정상 종료가 아닌 경우 반드시 실행되어야 하는 인텐트를 여기서 다시 진행
        }
        if (intent == null) {
            // START_STICKY모드로 비정상 종료시 intent null로 만들고 처리해야 하는 업무
        }
        extraValue = intent.getIntExtra("service", 0);
        subThread = new BackGroundSubThread("Thread1_test");
        subThread.start();

        // 서비스가 비정상적으로 종료할 경우 null 값을 인텐트에 넣어서 재시작함.
        return START_STICKY; // START_STICKY 모드로 정상 처리
    }

    // start 할 때 BroadCastRecei
    //

    /*

     */
    // ver에 인텐트 해서 registerReceiver에 해주기 .
    // BroadcastReceiver 상속 받는 클래스에서
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (subThread != null && subThread.isAlive()) {
            // 백그라운드 스레드가 존재할 경우
            subThread.interrupt();
        }
        this.stopSelf(); // 서비스 종료
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {

        // message 보낼 때
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String newMessage = (String) msg.obj;
            // 메인 액티비티에서 서비스로 보내면 여기 클래스로 들어와서
            // onStartCommand() 로 들어온다.
            // 서비스가 시작하면서 Thread를 시작한다.
            // Thread가 돌아가면서 handler에 메세지를 보낸다.
            // onStartCommand()에서 putExtra로 보낸 값을 가지고 있는다.
            // 스레드를 시작시키면서 핸들러에서 가지고 있는 메시지를 스레드에서 받고
            switch (msg.what){
                case 0:
                    Toast.makeText(getApplicationContext(),"서비스 테스트1",Toast.LENGTH_LONG).show();
                    break;
                case 1:
                    Toast.makeText(getApplicationContext(),"서비스 테스트2",Toast.LENGTH_LONG).show();
                    break;
                case 2:
                    Toast.makeText(getApplicationContext(),"서비스 테스트3",Toast.LENGTH_LONG).show();
                    break;
                case 3:
                    Toast.makeText(getApplicationContext(),"서비스 테스트4",Toast.LENGTH_LONG).show();
                    break;
            }
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public class BackGroundSubThread extends Thread {

        private HashMap<String, String> newHashMap;
        private Random random;

        public BackGroundSubThread(String name) {
            super(name);
            newHashMap = new HashMap<>();
            random = new Random(System.currentTimeMillis());
            newHashMap.put("테스트1", "테스트 진행1");
            newHashMap.put("테스트2", "테스트 진행2");
            newHashMap.put("테스트3", "테스트 진행3");
            newHashMap.put("테스트4", "테스트 진행4");
            newHashMap.put("테스트5", "테스트 진행5");
        }

        @Override
        public void run() {
            while (!isInterrupted()) {
                Message message = handler.obtainMessage();
                message.what = extraValue;

                if (extraValue == 0) {
                    message.obj = newHashMap.get("테스트1");
                } else if (extraValue == 1) {
                    message.obj = newHashMap.get("테스트2");
                } else if (extraValue == 2) {
                    message.obj = newHashMap.get("테스트3");

                } else if (extraValue == 3) {
                    message.obj = newHashMap.get("테스트4");
                } else {
                    message.obj = newHashMap.get("테스트4");
                }
                extraValue = random.nextInt(4);
                try{
                    sleep(2000);
                }catch(InterruptedException e){
                    message.obj = " sleep()";
                    currentThread().interrupt();
                }
                handler.sendMessage(message);
            }
            super.run();
        }
    }
}
