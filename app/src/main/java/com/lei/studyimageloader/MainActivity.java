package com.lei.studyimageloader;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

public class MainActivity extends AppCompatActivity implements ImageLoadingListener {

    private ImageView iv, img2, img3;
    private ImageLoader imageLoader;
    private ProgressDialog pdDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv = findViewById(R.id.iv);
        img2 = findViewById(R.id.img2);
        img3 = findViewById(R.id.img3);
        imageLoader = ImageLoader.getInstance();
        getPermission();
        pdDialog = new ProgressDialog(this);
        pdDialog.setTitle("tip");
        pdDialog.setMessage("加载中");

        //url 1.file://本地文件 2.drawable:// 3.context://.../xx.jpg  4.assets
        imageLoader.displayImage("drawable://" + R.drawable.bbbb, img2, this);
        imageLoader.displayImage("file:///storage/emulated/0/AAA/as.jpg", iv, this);


        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.eeee)//设置图片下载期间显示的图片
                .showImageForEmptyUri(R.drawable.eeee)//设置图片uri为空或是错误时显示的图片
                .showImageOnFail(R.drawable.eeee)
                .cacheInMemory(true)
                .cacheOnDisc(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
        imageLoader.displayImage("http://pic32.photophoto.cn/20140730/0022005289036413_b.jpg", img3, options,  this);
    }

    public void getPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);

        }

        /*if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);

        }*/
    }

    @Override
    public void onLoadingStarted(String imageUri, View view) {
        pdDialog.show();
    }

    @Override
    public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
        pdDialog.dismiss();
    }

    @Override
    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
        pdDialog.dismiss();
    }

    @Override
    public void onLoadingCancelled(String imageUri, View view) {

    }
}
