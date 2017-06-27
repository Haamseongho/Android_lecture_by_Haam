package com.example.haams.customviewexample;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

/**
 * Created by haams on 2017-06-27.
 */

public class StarIndicatorView extends LinearLayout {
    private ImageView mStar1;
    private ImageView mStar2;
    private ImageView mStar3;
    private ImageView mStar4;
    private ImageView mStar5;
    private ImageView mStars[];
    private int mSelected = 0;

    public StarIndicatorView(Context context) {
        super(context);
    }

    public StarIndicatorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.five_starts_indicator, this);
        if (attrs != null) {
            TypedArray typeArr = context.obtainStyledAttributes(attrs, R.styleable.StarIndicatorView);
            mSelected = typeArr.getInteger(0, 0);
            typeArr.recycle();
        }
    }

    /*
    Inflate 완료 후 콜백된다.
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mStar1 = (ImageView) findViewById(R.id.star);
        mStar2 = (ImageView) findViewById(R.id.star2);
        mStar3 = (ImageView) findViewById(R.id.star3);
        mStar4 = (ImageView) findViewById(R.id.star4);
        mStar5 = (ImageView) findViewById(R.id.star5);

        mStars = new ImageView[]{mStar1, mStar2, mStar3, mStar4, mStar5};

        setSelected(mSelected, true);

    }

    public void setSelected(int select) {
        setSelected(select, false);
    }

    private void setSelected(int mSelected, boolean b) {
        switch (mSelected) {
            case 0:
                setOffStar();
                break;
            case 1:
                setOnStar(mSelected);
                break;
            case 2:
                setOnStar(mSelected);
                break;
            case 3:
                setOnStar(mSelected);
                break;
            case 4:
                setOnStar(mSelected);
                break;
            case 5:
                setOnStar(mSelected);
                break;

        }
    }

    private void setOnStar(int mSelected) {
        for (int i = 0; i < mSelected; i++) {
            Glide.with(getContext())
                    .load(R.drawable.star)
                    .fitCenter().into(mStars[i]);
        }
    }

    private void setOffStar() {
        for (int i = 0; i < 5; i++) {
            Glide.with(getContext())
                    .load(R.drawable.star_empty)
                    .fitCenter().into(mStars[i]);
        }
    }

    public int getSelected() {
        return mSelected;
    }

    public StarIndicatorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
