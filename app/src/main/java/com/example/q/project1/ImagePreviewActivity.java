package com.example.q.project1;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by q on 2016-12-26.
 */

public class ImagePreviewActivity extends Activity {
    private ArrayList<String> imagePaths = new ArrayList<String>();
    private PagerAdapter mPagerAdapter;
    private ViewPager mPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_preview);

        Intent i = getIntent();
        int position = i.getExtras().getInt("position");

        mPagerAdapter = new ImagePreviewAdapter(this, position);
        mPager = (ViewPager) findViewById(R.id.img_pager);
        mPager.setAdapter(mPagerAdapter);
        mPager.setCurrentItem(position);

    }
}
