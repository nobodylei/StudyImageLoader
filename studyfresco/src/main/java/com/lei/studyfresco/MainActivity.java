package com.lei.studyfresco;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

public class MainActivity extends AppCompatActivity {

    private SimpleDraweeView simpleDraweeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //写在setContentView之前
        Fresco.initialize(this);
        setContentView(R.layout.activity_main);

        simpleDraweeView = findViewById(R.id.sdv);

        Uri imageUri = Uri.parse("http://img.pconline.com.cn/images/upload/upc/tx/wallpaper/1302/04/c0/17981969_1359970249174.jpg");
        //开始下载图片
        simpleDraweeView.setImageURI(imageUri);

        //控制器
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(imageUri)//重试之后要加载的图片地址
                .setTapToRetryEnabled(true)//设置点击之后重试
                .setOldController(simpleDraweeView.getController())
                .build();
        simpleDraweeView.setController(controller);

    }
}
