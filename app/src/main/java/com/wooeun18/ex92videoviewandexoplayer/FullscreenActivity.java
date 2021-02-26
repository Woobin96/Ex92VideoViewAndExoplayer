package com.wooeun18.ex92videoviewandexoplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;

public class FullscreenActivity extends AppCompatActivity {

    PlayerView pv;
    SimpleExoPlayer player; //애가 플레이 시켜주는거임

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);

        pv= findViewById(R.id.pv);
        player= new SimpleExoPlayer.Builder(this).build();
        pv.setPlayer(player);

        Intent intent= getIntent();
        String videoUrl= intent.getStringExtra("videoUrl");
        long currenPos= intent.getLongExtra("currenPos", 0);

        MediaItem mediaItem= MediaItem.fromUri(videoUrl);
        player.setMediaItem(mediaItem);
        player.prepare();

        player.seekTo(currenPos);// 그 위치 찾아서 가는거 .
        player.play();
        player.setRepeatMode(ExoPlayer.REPEAT_MODE_ALL); //영상 계속해서 도는거 .
    }

    //화면에서 안보이기 시작할때 일시정지
    @Override
    protected void onPause() {
        super.onPause();
        player.pause();
    }

    //이 액티비티가 종료될때 비디오도 종료되도록
    @Override
    protected void onDestroy() {
        super.onDestroy();

        //플레이어를 메모리에서 완전 삭제하기 .
        pv.setPlayer(null); //끊어내는거 .
        player.release();
        player= null;
    }
}

