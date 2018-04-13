package com.dev.arif.collapseimagetransitions;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PreviewPageActivity extends AppCompatActivity {

    @BindView(R.id.media_controller)
    ImageView media_controller;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview_page);
        ButterKnife.bind(this);
        Intent intent=getIntent();
        String url=intent.getStringExtra("Image");
        Picasso.get()
                .load(url)
                .into(media_controller);
    }
    @Override
    public void onBackPressed() {
        supportFinishAfterTransition();

    }
    @OnClick(R.id.close)
    public void close()
    {
        supportFinishAfterTransition();
    }
}
