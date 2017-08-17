# 멀티메모
---
## 카메라 연동 

- 사용방법 
    1. Intent를 활용 [캡처 후 이미지 가져오기]
    
	```
		
			Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);			
			
           // 임시로 파일 경로 설정
            
			-> String url = "tmp_" + String.valueOf(System.currentTimeMills()) + ".jpg";
            Uri ImageUri = Uri.fromFile(new File(Environment.getExternalStorage(),url);

    		// 외부 저장소 경로 + 현 시간에 대한 .jpg 파일을 만들어서 경로를 설정해준다.
            intent.putExtra(MediaStore.EXTRA_OUTPUT,ImageUri);
            // 결과물로 사용한다고 설정하기 
            startActivityForResult(intent,1001);

           > 인텐트를 보내고 다시 받아야함. 다시 받는 곳에서 캡처 관련 작업을 진행

    ```


<br />
<hr />
  
  
  
   2 . onActivityResult(int requestCode , int resultCode , Intent data) 부분!

   ```

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


   ``` 



--------

여기서 중요한 것은 API 23버전 이상 부터는 퍼미션을 주어야 하는데 Manifest와 자바 코드에서 각각 불러주어야 합니다.

## [Manifest.xml]

```

         <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
   		 <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />

  		  <uses-feature
  		      android:name="android.hardware.camera"
  		      android:required="false" />
  		  <uses-permission android:name="android.permission.CAMERA" />


```


## [MainActivity.java]

```

	   int permission = ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    MainActivity.this,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }


```

위와 같이 각각 작업해 준다면 파일 관련된 내용 저장소를 사용한다는 내용의 권한 문제는 해결할 수 있습니다.


---

## [캡처한 파일 가지고 오기]

```

		 BitmapFactory.Options options = new 	BitmapFactory.Options();
     		   options.inSampleSize = 8;
      	  Bitmap bitmap = BitmapFactory.decodeFile(imageUri.getPath(), options);

     	   Glide.with(this).asBitmap()
       	         .load(resizeBitmap(bitmap))
     	           .into((ImageView) findViewById(R.id.mImage));



```

캡처한 이미지는 외부 저장소에 tmp_ 경로 내의 jpg 파일로 생성된 상태입니다. 

이를 BitmapFactory를 이용해서 파일 디코딩 합니다.

물론 해당 경로를 가지고와서 디코딩 해야될 것입니다.
디코딩한 것을 비트맵으로 만들고 이를 Glide 라이브러리를 이용해서
비트맵을 불러옵니다.

resizeBitmap은 제가 따로 정의한 메소드이기 때문에 저 자리에 그냥 bitmap 넣으셔야 합니다! 
(사이즈 조정 관련된 내용은 본인의 기호에 맞게 정의해주시기 바랍니다.)



--------

- Gallery에서 이미지 가져오기
	1. Intent 선언 
	
```

		Intent gIntent = new Intent(Intent.ACTION_PICK);
        gIntent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        gIntent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(gIntent, GALLERY_CODE);


```

ACTION_PICK으로 암시적 인텐트를 만들어 준 다음 데이터 설정과 데이터 타입을 외부 컨텐트 URI 사용 그리고 미디어 컨텐트 타입을 사용한다고 설정해 준다.
앞서 캡처와 마찬가지로 startActivityForResult로 인텐트를 전송하고 onActivityResult()에서 내용을 정의해준다.




## 퍼미션 제한 

```

		if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    MainActivity.this,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }


```

위 인텐트를 보내기 전에 퍼미션을 먼저 준 다음 진행한다.


## ContentProvider

: 카메라 캡쳐와 다른 점은 카메라 캡쳐 시엔 파일을 새로 생성한 다음 외부 저장소와 임시 저장소 그리고 파일 명 까지 직접 정의하며 만들었다면 , 
사진첩에서 가지고 오는 이미지 파일은 이미 존재한 파일이기 때문에 
저장소에서 파일에 대한 내용을 가지고 온 다음 이를 decodeFile 해야 합니다.





가지고 올 때는 ContentProvider를 이용해서 가지고 올 텐데 이를 활용하기 위해선 getContentResolver() 메소드를 이용해서 쿼리문을 주어 구현할 것입니다.

```

		private String getRealPathFromURI(Uri imgUri) {
        	String[] proj = {MediaStore.Images.Media.DATA};
        	Cursor cursor = getContentResolver().query(imgUri, proj, null, null, null);
       	 	int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        	cursor.moveToFirst();
        	return cursor.getString(column_index);
    }


```

projection으로 이미지 미디어의 데이터를 먼저 찾은 뒤 , 이를 기점으로 쿼리문을 주어 ContentProvider를 활용합니다.

getContentResolver()를 통해서 MediaStore.Images.Media.DATA를 현 어플리케이션으로 가지고 온다.

query()메소드는 클라이언트에서 요청한 데이터를 관리하게 되는데
이는 Cursor객체가 관리한다.
데이터를 가지고 오기 위한 Uri가 첫 번째 인자로 들어가있고,
데이터 중 가져올 칼럼의 이름을 정의하는 projection을 정의해준다.

그 외적인 내용들은 조건절에 속한 내용들이고 오름/내림 차순 정의 인데 크게 필요한 부분이 아니기 때문에 null로 설정하였습니다.

따라서 저장된 이미지의 위치를 가지고 Media.DATA에서 찾기 때문에 저장된 공간의 인덱스 값이 나올 것이고 이 인덱스 값으로 데이터 값을 가지고 온다면 저장된 경로가 나올 것입니다.


```

     	imageUri = data.getData();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 8;
        Bitmap bitmap = BitmapFactory.decodeFile(getRealPathFromURI(imageUri), options);

        imgPath = getRealPathFromURI(imageUri); // DB저장 (사진첩 내의 이미지 경로)


        ((ImageView) findViewById(R.id.mImage)).setImageBitmap(resizeBitmap(bitmap));



```

이 후엔 BitmapFactory를 활용해서 경로를 비트맵으로 디코딩 한 뒤 
이미지를 꼽아주면 됩니다.

