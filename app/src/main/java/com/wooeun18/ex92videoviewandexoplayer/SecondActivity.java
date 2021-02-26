package com.wooeun18.ex92videoviewandexoplayer;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerControlView;
import com.google.android.exoplayer2.ui.PlayerView;

public class SecondActivity extends AppCompatActivity {

    //ExoPlayer Library 추가 [프로젝트 스트럭쳐 안됨됨]

    PlayerView pv; //비디오를 보여주는 뷰 .
    SimpleExoPlayer player; // 실제 비디오를 플레이 하는 객체 참조변수 .

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        pv= findViewById(R.id.pv);
    }

    //액티비티가 화면에 보여질때 자동으로 호출되는 메소드
    @Override
    protected void onStart() {
        super.onStart();

        //실제 비디오를 실행하는 객체 생성
        player= new SimpleExoPlayer.Builder(this).build();

        //플레이어 뷰에 플레이어 객체 설정 .
        pv.setPlayer(player);

        //비디오를 1개를 로딩하는 작업 수행 .
//        Uri uri= Uri.parse("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4");
//
//        //Media Item 으로 만들고 실행 .
//        MediaItem mediaItem= MediaItem.fromUri(uri);
//        player.setMediaItem(mediaItem);
//        player.prepare(); //준비
//        player.play(); //시작

        //비디오 여러개를 차레로 재생 .
        Uri firstUri= Uri.parse("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4");
        Uri SecondUri= Uri.parse("http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ForBiggerEscapes.mp4");

        MediaItem item1= MediaItem.fromUri(firstUri);
        MediaItem item2= MediaItem.fromUri(SecondUri);
        player.addMediaItem(item1);
        player.addMediaItem(item2);

        player.prepare();
        player.play();

        //플레이어 컨트롤뷰에 플레이어를 연동하기 .
//        PlayerControlView pcv= findViewById(R.id.pcv);
//        pcv.setPlayer(player);
   }

   //2021 02 26
    public void clickBtn(View view) {
        //ExoPlayer는 전체화면모드라는 특별한 기능은 없음
        //액티비티화면 사이즈만한 ExoPlayer 를 가진 액티비티로 이동
        String videoUrl= "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4";
        long currentPos= player.getCurrentPosition();

        Intent intent= new Intent(this, FullscreenActivity.class);
        intent.putExtra("videoUrl", videoUrl);
        intent.putExtra("currentPos", currentPos);
        startActivity(intent);

        //일시정지
        player.pause();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==100 && resultCode==RESULT_OK){
            long currentPos= data.getLongExtra("currentPos", 0);
            player.seekTo(currentPos);
        }
    }
}













