package com.example.q.project1;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.MaskFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

/**
 * Created by q on 2016-12-27.
 */

public class DrawActivity extends Activity implements View.OnClickListener {
    private DrawingView drawView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);
        drawView = (DrawingView)findViewById(R.id.drawing_view);

        Button redBrush = (Button) findViewById(R.id.palette_red);
        redBrush.setBackgroundColor(ContextCompat.getColor(this, R.color.red));
        redBrush.setOnClickListener(this);
        Button orangeBrush = (Button) findViewById(R.id.palette_orange);
        orangeBrush.setBackgroundColor(ContextCompat.getColor(this, R.color.orange));
        orangeBrush.setOnClickListener(this);
        Button yellowBrush = (Button) findViewById(R.id.palette_yellow);
        yellowBrush.setBackgroundColor(ContextCompat.getColor(this, R.color.yellow));
        yellowBrush.setOnClickListener(this);
        Button greenBrush = (Button) findViewById(R.id.palette_green);
        greenBrush.setBackgroundColor(ContextCompat.getColor(this, R.color.green));
        greenBrush.setOnClickListener(this);
        Button blueBrush = (Button) findViewById(R.id.palette_blue);
        blueBrush.setBackgroundColor(ContextCompat.getColor(this, R.color.blue));
        blueBrush.setOnClickListener(this);
        Button blackBrush = (Button) findViewById(R.id.palette_black);
        blackBrush.setBackgroundColor(ContextCompat.getColor(this, R.color.black));
        blackBrush.setOnClickListener(this);
        Button eraser = (Button) findViewById(R.id.palette_eraser);
        eraser.setOnClickListener(this);
    }

    public void onClick(View v) {
        if (v.getId() == R.id.palette_eraser) {
            drawView.setErase(true);
        }
        else {
            drawView.setErase(false);
            drawView.setColor(v.getTag().toString());
        }
    }
}
