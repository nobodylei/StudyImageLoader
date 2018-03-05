package com.lei.studypicasso;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.ViewsById;

import java.io.File;

@EActivity
public class MainActivity extends AppCompatActivity {
    @ViewById
    ImageView img1;
    @ViewById
    ImageView img2;
    @ViewById
    ImageView img3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getPermission();
        //加载本地图片
        Picasso.with(this).load(R.drawable.moya).into(img2);
        Picasso.with(this).load(new File("/storage/emulated/0/y.jpg")).into(img1);
        String url = "http://pic1.win4000.com/wallpaper/9/573a8a5acfd17.jpg";
        Picasso.with(this).load(url).into(img3);
    }

    private int dp2px(Context context, float v) {
        float sale = context.getResources().getDisplayMetrics().density;
        return (int)(v * sale + 0.5f);
    }

    public void getPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);

        }

        /*if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);

        }*/
    }
}
