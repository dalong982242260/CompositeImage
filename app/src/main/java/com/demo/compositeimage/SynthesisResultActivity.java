package com.demo.compositeimage;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


public class SynthesisResultActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_synthesis_result);
        ScaleImageView scaleImageView= (ScaleImageView) findViewById(R.id.mScaleImageView);
        Intent intent=getIntent();
        if(intent!=null) {
            Bundle b=intent.getExtras();
            Bitmap mbitmap=(Bitmap) b.getParcelable("bitmap");
            scaleImageView.setImageDrawable(new BitmapDrawable(mbitmap));
        }
    }
}
