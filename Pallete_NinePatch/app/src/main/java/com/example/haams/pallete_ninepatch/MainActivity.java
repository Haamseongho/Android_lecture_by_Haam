package com.example.haams.pallete_ninepatch;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.transition.Transition;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        bindViews();

    }

    private void bindViews() {
        setViews();
    }

    private void setViews() {

        Glide.with(this).asBitmap()
                .load(R.drawable.kakao)
                .into(new BitmapImageViewTarget(mImage){
                    @Override
                    public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                        super.onResourceReady(resource, transition);

                        Palette palette = Palette.from(resource).generate();
                        setPalette(palette);
                    }
                });
    }

    private void setPalette(Palette palette) {
        if(palette == null)
            return;

        Palette.Swatch vibrantSwatch = palette.getVibrantSwatch();

        if(vibrantSwatch!=null){
            color1.setBackgroundColor(vibrantSwatch.getRgb());
            colorText1.setTextColor(vibrantSwatch.getTitleTextColor());
        }

        Palette.Swatch darkVibrantSwatch = palette.getDarkVibrantSwatch();
        if(darkVibrantSwatch != null){
            color2.setBackgroundColor(darkVibrantSwatch.getRgb());
            colorText2.setTextColor(darkVibrantSwatch.getBodyTextColor());
        }


        Palette.Swatch lightVibrantSwatch = palette.getLightVibrantSwatch();
        if(darkVibrantSwatch != null){
            color3.setBackgroundColor(lightVibrantSwatch.getRgb());
            colorText3.setTextColor(lightVibrantSwatch.getBodyTextColor());
        }

        Palette.Swatch mutedSwatch = palette.getMutedSwatch();
        if(mutedSwatch!=null){
            color4.setBackgroundColor(mutedSwatch.getRgb());
            colorText4.setTextColor(mutedSwatch.getTitleTextColor());
        }

    }
    @OnClick({R.id.color1,R.id.color2,R.id.color3,R.id.color4})
    public void moveIntent(View view){
        startActivity(new Intent(MainActivity.this,SubActivity.class));
    }
}
