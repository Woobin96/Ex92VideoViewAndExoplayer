package com.wooeun18.ex92videoviewandexoplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;

public class DetailActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        textView= findViewById(R.id.tv);

        //전달된 json 문자열 받기
        String jsonStr= getIntent().getStringExtra("item");

        //json 문자열을 --> VideoItem 객체로 변환
        VideoItem videoItem= new Gson().fromJson(jsonStr, VideoItem.class);

        textView.setText(videoItem.title + "\n" + videoItem.subtitle + "\n"+ videoItem.sources);

    }
}