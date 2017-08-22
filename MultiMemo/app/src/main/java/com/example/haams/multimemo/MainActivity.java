package com.example.haams.multimemo;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.icu.text.SimpleDateFormat;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.CharacterPickerDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.ImageViewTarget;
import com.example.haams.multimemo.items.Memo;
import com.example.haams.multimemo.server.Network;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final int CAMERA_CODE = 1001;
    private static final int GALLERY_CODE = 1002;
    private static final String TAG = "MainActivity";
    private EditText edtTitle, edtMemo;
    File storageDir;
    DBHelper dbHelper;
    String imgPath;
    int index = 0;
    Uri imageUri;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    private static final int REQUEST_EXTERNAL_STORAGE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        initViews();
    }

    private void initViews() {
        edtTitle = (EditText) findViewById(R.id.edtTitle);
        edtMemo = (EditText) findViewById(R.id.edtMemo);

    }


    @OnClick({R.id.imgUpload, R.id.btnUpload})
    public void BtnClick(View view) {
        switch (view.getId()) {
            case R.id.imgUpload:
                clkPhotos();
                break;
            case R.id.btnUpload:
                dbHelper = new DBHelper(this, "memoTable", null, 1);
                sendDataToSqlite();
               // sendDataToServer();
                break;

        }
    }

    private void clkPhotos() {
        final AlertDialog.Builder dlg = new AlertDialog.Builder(this);
        final View pView = LayoutInflater.from(this).inflate(R.layout.activity_img_select_alertitems, null);
        Button btnCamera = (Button) pView.findViewById(R.id.btn_camera);
        Button btnGallery = (Button) pView.findViewById(R.id.btn_gallery);

        dlg.setView(pView);

        dlg.setTitle("사진 가져오기");
        dlg.setMessage("사진 촬영 후 가지고 올까요? or 사진첩에서 가지고 올까요? ");

        dlg.show();


        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connectCamera(dlg); // 카메라 연결
            }
        });
        btnGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connectGallery(dlg); // 사진첩 연결
            }
        });

    }


    private void sendDataToSqlite() {
        if (index == 0) {
            dbHelper.insertOnlyMemo(edtTitle.getText().toString()+"=", edtMemo.getText().toString()+"=");
        } else {
            dbHelper.insertMemoWithImage(edtTitle.getText().toString()+"=", edtMemo.getText().toString()+"=", imgPath+"=");
            Log.i(TAG, "db saved well");
            Log.i(TAG, dbHelper.getMemoAllThings(edtTitle.getText().toString()));
        }
        startActivity(new Intent(MainActivity.this,MemoActivity.class));
    }

    private void sendDataToServer() {
        Network network = Network.getNetworkInstance();
        network.getFileProxy().uploadFileToServer(edtTitle.getText().toString(),
                edtMemo.getText().toString(), imgPath, new Callback<Memo>() {
                    @Override
                    public void onResponse(Call<Memo> call, Response<Memo> response) {
                        if(response.isSuccessful()){
                            Log.i(TAG,"파일 서버 전송 완료");
                            startActivity(new Intent(MainActivity.this,MemoActivity.class));
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<Memo> call, Throwable t) {
                        Log.e(TAG,t.toString());
                        // 서버 전송 실패
                    }
                });
    }


    public void connectCamera(AlertDialog.Builder dlg) {
        Intent camIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        int permissionCamera = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA);
        if (permissionCamera == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA}, CAMERA_CODE);
        }

        int permission = ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    MainActivity.this,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
        /*
        Permission 정리 [ 파일 생성 및 외부 저장소 활용 ] API 23버전 이상 부턴 Manifest와 Java쪽에서도 코드를 정리해줘야 한다.
         */
        String url = "tmp_" + String.valueOf(System.currentTimeMillis()) + ".jpg";
        imageUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(), url));
        camIntent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, imageUri);


        imgPath = imageUri.getPath(); // db저장하기 위함


        dlg.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                dialog.dismiss();
            }
        });
        startActivityForResult(camIntent, CAMERA_CODE);

    }


    public void connectGallery(AlertDialog.Builder dlg) {

        int permission = ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    MainActivity.this,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
        Intent gIntent = new Intent(Intent.ACTION_PICK);
        gIntent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        gIntent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        dlg.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                dialog.dismiss();
            }
        });
        startActivityForResult(gIntent, GALLERY_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case CAMERA_CODE:
                    getPictureFromCamera(data);
                    break;
                case GALLERY_CODE:
                    getPictureFromGallery(data);
                    break;

            }
        }
    }

    private void getPictureFromCamera(Intent data) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 8;
        Bitmap bitmap = BitmapFactory.decodeFile(imageUri.getPath(), options);
        ((ImageView) findViewById(R.id.mImage)).setImageBitmap(resizeBitmap(bitmap));
/*

        Glide.with(this).asBitmap()
                .load(resizeBitmap(bitmap))
                .into((ImageView) findViewById(R.id.mImage));
*/

        index = 1;

    }

    private void getPictureFromGallery(Intent data) {
        imageUri = data.getData();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 8;
        Bitmap bitmap = BitmapFactory.decodeFile(getRealPathFromURI(imageUri), options);

        imgPath = getRealPathFromURI(imageUri); // DB저장 (사진첩 내의 이미지 경로)


        ((ImageView) findViewById(R.id.mImage)).setImageBitmap(resizeBitmap(bitmap));

     /*
        Log.e(TAG, getRealPathFromURI(data.getData()));
        Glide.with(this).asBitmap()
                .load(resizeBitmap(bitmap))
                .into((ImageView) findViewById(R.id.mImage));
*/
        index = 1; // index 값은 이미지가 있을 때 DB 저장하는 것을 알리기 위함

    }

    private Bitmap resizeBitmap(Bitmap bitmap) {
        if (bitmap == null) {
            return null;
        }
        bitmap = imgRotate(bitmap);
        int resizeWidth = 400;
        double aspectRatio = (double) bitmap.getHeight() / (double) bitmap.getWidth();
        int targetHeight = (int) (resizeWidth * aspectRatio);
        Bitmap result = Bitmap.createScaledBitmap(bitmap, resizeWidth, targetHeight, false);
        if (result != bitmap) {
            bitmap.recycle();
        }
        return result;
    }

    private Bitmap imgRotate(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.postRotate(90);
        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, false);
        return resizedBitmap;
    }

    private String getRealPathFromURI(Uri imgUri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(imgUri, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }
}
