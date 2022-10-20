package com.moutamid.simplelogin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = findViewById(R.id.imageView);

        Glide.with(this)
                .load("https://source.unsplash.com/random")
                .apply(new RequestOptions()
                        .placeholder(R.color.grey)
                        .error(R.color.red)
                )
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(imageView);
    }
}