package com.demo.compositeimage;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import java.io.File;


public class MainActivity extends AppCompatActivity {

    private LinearLayout cutLayout;
    private ImageSynthesisLayout mImageSynthesisLayout;

    private int index = 0;
    public String[] jsonData = new String[]{
            "theme/chemo/coordinate/chemo1.json",
            "theme/chemo/coordinate/chemo2.json",
            "theme/chemo/coordinate/chemo3.json",
            "theme/chemo/coordinate/chemo4.json",
            "theme/chemo/coordinate/chemo5.json",

            "theme/tianmi/coordinate/tianmi1.json",
            "theme/tianmi/coordinate/tianmi2.json",
            "theme/tianmi/coordinate/tianmi3.json",
            "theme/tianmi/coordinate/tianmi4.json",
            "theme/tianmi/coordinate/tianmi5.json",

            "theme/tuijian/coordinate/tuijian1.json",
            "theme/tuijian/coordinate/tuijian2.json",
            "theme/tuijian/coordinate/tuijian3.json",
            "theme/tuijian/coordinate/tuijian4.json",
            "theme/tuijian/coordinate/tuijian5.json",

    };
    public String[] imgData = new String[]{
            "theme/chemo/template/chemo1.png",
            "theme/chemo/template/chemo2.png",
            "theme/chemo/template/chemo3.png",
            "theme/chemo/template/chemo4.png",
            "theme/chemo/template/chemo5.png",

            "theme/tianmi/template/tianmi1.png",
            "theme/tianmi/template/tianmi2.png",
            "theme/tianmi/template/tianmi3.png",
            "theme/tianmi/template/tianmi4.png",
            "theme/tianmi/template/tianmi5.png",

            "theme/tuijian/template/tuijian1.png",
            "theme/tuijian/template/tuijian2.png",
            "theme/tuijian/template/tuijian3.png",
            "theme/tuijian/template/tuijian4.png",
            "theme/tuijian/template/tuijian5.png",

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
    }

    private void initListener() {
        findViewById(R.id.cut_picture).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cutPic();
            }
        });
        findViewById(R.id.up).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                up();
            }
        });
        findViewById(R.id.down).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                down();
            }
        });
    }

    private void down() {
        index--;
        if (index < 0) {
            index = jsonData.length - 1;
        }
        setData();
    }

    private void up() {
        index++;
        if (index >= jsonData.length) {
            index = 0;
        }
        setData();
    }

    /**
     * 截图
     */
    private void cutPic() {
        Bitmap bimap = SynthesisInfoUtils.getViewDraw(cutLayout);
        String path = PathManager.getPhotoDir().getAbsolutePath() + "/" + System.currentTimeMillis() + ".jpg";
        Log.e("cutPic", path);
        SynthesisInfoUtils.saveBitmap(path, bimap, 50);
        // Mediascanner need to scan for the image saved
        Intent mediaScannerIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri fileContentUri = Uri.fromFile(new File(path));
        mediaScannerIntent.setData(fileContentUri);
        sendBroadcast(mediaScannerIntent);
    }

    private void initView() {
        cutLayout = (LinearLayout) findViewById(R.id.viewGrop);
        mImageSynthesisLayout = (ImageSynthesisLayout) findViewById(R.id.mImageSynthesisLayout);
        setData();
    }

    public void setData() {
        mImageSynthesisLayout.setSynthesisData(
                SynthesisInfoUtils.getPuzzleInfo(this, jsonData[index]),
                SynthesisInfoUtils.getImageFromAssetsFile(this, imgData[index]),
                R.mipmap.carimg, R.mipmap.ic_launcher_round);
    }

}
