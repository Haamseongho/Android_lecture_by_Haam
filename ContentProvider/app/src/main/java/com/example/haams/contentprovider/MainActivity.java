package com.example.haams.contentprovider;

import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.database.Cursor;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView contents;
    private ImageView mImage;

    @TargetApi(Build.VERSION_CODES.N)
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getImageData();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void getImageData() {
        try {
            Cursor cursor = getImage();
            if (cursor.moveToFirst()) {
                int idColNum = cursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns._ID);
                int titleColNum = cursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns.TITLE);
                int dateTakenColNum = cursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns.DATE_TAKEN);
                // 각 칼럼별 열 인덱스 가져오기 .
                long id = cursor.getLong(idColNum);
                String title = cursor.getString(titleColNum);
                long dateTaken = cursor.getLong(dateTakenColNum);
                Uri imageUri = ContentUris.withAppendedId(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,id);
                // 기존에 Cursor의 쿼리문 줄 때 Uri와 동일하게 정의해준다.
                // id값을 통해서 데이터를 가지고 온다.
                // 칼럼별 열 인덱스를 가지고 데이터를 불러왔음.
                contents = (TextView) findViewById(R.id.contents);
                mImage = (ImageView) findViewById(R.id.mImage);

                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(dateTaken);
                SimpleDateFormat dates = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초");
                dates.format(calendar);

                contents.setText(title +" / " + dates.toString());
                mImage.setImageURI(imageUri);
            }
            cursor.close();
        } catch (SecurityException e) {
            Toast.makeText(this, "스토리지에 접근 권한을 허가로 해주세요.（종료합니다)", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    private Cursor getImage() {
        ContentResolver contentResolver = getContentResolver();
        // ContentResolver 는 getContentResolver() 메소드를 활용하여 객체를 가지고 온다.
        String[] projection = {
                MediaStore.Images.ImageColumns._ID,
                MediaStore.Images.ImageColumns.TITLE,
                MediaStore.Images.ImageColumns.DATE_TAKEN
        };
        // 가져올 칼럼명 : ID , TITLE , DATE
        String sortOrder = MediaStore.Images.ImageColumns.DATE_TAKEN + " DESC";
        // 날짜순 내림차순으로 가지고 오기.
        Uri queryUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        // URI : CONTENT_URI & EXTERNAL_CONTENT_URI 이렇게 두 개가 있음!
        queryUri = queryUri.buildUpon().appendQueryParameter("limit", "1").build();
        // 가지고 올 때 쿼리문의 파라미터로 하나만 가져오도록 제한한다.
        return contentResolver.query(queryUri, projection, null, null, sortOrder);
        // 필터링할 칼럼명 조절 안함 (where절) & 해당 칼럼명의 조건 설정도 하지 않음.
    }

}
