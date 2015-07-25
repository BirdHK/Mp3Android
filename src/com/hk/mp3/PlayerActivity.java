package com.hk.mp3;

import java.io.File;

import com.hk.entity.Mp3Info;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class PlayerActivity extends Activity implements OnClickListener{
	Button startBtn = null;
	Button pauseBtn = null;
	Button stopBtn = null;
	Mp3Info mp3Info = null;
	MediaPlayer mediaPlayer = null;
	
	private boolean isPlaying =false;
	private boolean isPause =false;
	private boolean isReleased =false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO 自动生成的方法存根
		super.onCreate(savedInstanceState);
		setContentView(R.layout.player);
		
		Intent intent = getIntent();
		mp3Info = (Mp3Info) intent.getSerializableExtra("mp3Info");
		System.out.println("player------>"+mp3Info);
		
		startBtn  = (Button) findViewById(R.id.start);
		startBtn.setOnClickListener(this);
		pauseBtn = (Button) findViewById(R.id.pause);
		pauseBtn.setOnClickListener(this);
		stopBtn = (Button) findViewById(R.id.stop);
		stopBtn.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch(id){
		case R.id.start:
			startPlay();
			break;
		case R.id.pause:
			pausePlay();
			break;
		case R.id.stop:
			stopPlay();
			break;
		}
	}
	
	private void startPlay(){
		if(!isPlaying){
			String path = getMp3Path(mp3Info);
			mediaPlayer = MediaPlayer.create(PlayerActivity.this, Uri.parse("file://"+path));
			mediaPlayer.setLooping(false);
			mediaPlayer.start();
			isPlaying = true;
			isReleased  = false;
		}
	}
	private void pausePlay(){
		if(mediaPlayer != null){
			if(!isReleased){
				if(!isPause){
					mediaPlayer.pause();
					isPause = true;
					isPlaying = true;
				}else{
					mediaPlayer.start();
					isPause = false;		
				}
			}
		}
	}
	private void stopPlay(){
		if(mediaPlayer != null){
			if(isPlaying){
				if(!isReleased){
					mediaPlayer.stop();
					mediaPlayer.release();
					isReleased = true;
				}
				isPlaying = false;
			}
		}
	}
	private String getMp3Path(Mp3Info mp3Info){
		String SDCARD = Environment.getExternalStorageDirectory().getAbsolutePath();
		String path = SDCARD+File.separator+"Mp3Player"+File.separator+mp3Info.getMp3Name();
		return path;
	}
}
