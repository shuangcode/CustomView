package com.daniel.custom.ui;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

/**
 * 这个类是播放在线音频的方式
 */
public class AudioHTTPPlayer extends Activity implements View.OnClickListener,
		MediaPlayer.OnErrorListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnBufferingUpdateListener,
		MediaPlayer.OnPreparedListener
{
	/** Called when the activity is first created. */
	MediaPlayer mediaPlayer;
	Button stopButton, startButton;
	TextView statusTextView, bufferValueTextView;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.main);
//
//		stopButton = (Button) findViewById(R.id.EndButton);
//		startButton = (Button) findViewById(R.id.StartButton);
//		startButton.setOnClickListener(this);
//		stopButton.setOnClickListener(this);
//		startButton.setEnabled(false);
//		stopButton.setEnabled(false);
//
//		bufferValueTextView = (TextView) findViewById(R.id.BufferValueTextView);
//		statusTextView = (TextView) findViewById(R.id.StatusDisplayTextView);
		statusTextView.setText("onCreate");

		mediaPlayer = new MediaPlayer();
		mediaPlayer.setOnCompletionListener(this);
		mediaPlayer.setOnErrorListener(this);
		mediaPlayer.setOnBufferingUpdateListener(this);
		mediaPlayer.setOnPreparedListener(this);
		statusTextView.setText("MediaPlayer created");
		try
		{
			mediaPlayer
					.setDataSource("http://www.mobvcasting.com/android/audio/goodmorningandroid.mp3");

			// mediaPlayer.prepare();
			// mediaPlayer.start();
			statusTextView.setText("setDataSource done");
			statusTextView.setText("calling prepareAsync");
			mediaPlayer.prepareAsync();// 开始在后台缓冲音频文件并返回
		} catch (IOException e)
		{
			Log.v("AUDIOHTTPPLAYER", e.getMessage());
		}
	}

	@Override
	public void onPrepared(MediaPlayer mp)
	{
		// TODO Auto-generated method stub
//	当完成prepareAsync方法时，将调用活动的onPrepared方法
		statusTextView.setText("onPrepared called");
		startButton.setEnabled(true);
	}

	@Override
	public void onBufferingUpdate(MediaPlayer mp, int percent)
	{
		// TODO Auto-generated method stub
//		当MediaPlayer正在缓冲时，将调用活动的onBufferingUpdate方法
		bufferValueTextView.setText(""+percent+"%");
	}

	@Override
	public void onCompletion(MediaPlayer mp)
	{
		// TODO Auto-generated method stub
		statusTextView.setText("onCompletion called");
		stopButton.setEnabled(false);
		startButton.setEnabled(true);
	}

	@Override
	public boolean onError(MediaPlayer mp, int what, int extra)
	{
		// TODO Auto-generated method stub
		switch (what)
		{
			case MediaPlayer.MEDIA_ERROR_NOT_VALID_FOR_PROGRESSIVE_PLAYBACK:
				statusTextView
						.setText("MEDIA_ERROR_NOT_VALID_FOR_PROGRESSIVE_PLAYBACK"
								+ extra);
				break;
			case MediaPlayer.MEDIA_ERROR_SERVER_DIED:
				statusTextView.setText("MEDIA_ERROR_SERVER_DIED" + extra);
				break;
			case MediaPlayer.MEDIA_ERROR_UNKNOWN:
				statusTextView.setText("MEDIA_ERROR_UNKNOWN" + extra);
				break;
		}
		return false;
	}

	@Override
	public void onClick(View v)
	{
		// TODO Auto-generated method stub
		if (v == stopButton)
		{
			mediaPlayer.pause();
			statusTextView.setText("pause called");
			startButton.setEnabled(true);
		} else if (v == startButton)
		{
			mediaPlayer.start();
			statusTextView.setText("start called");
			startButton.setEnabled(false);
			stopButton.setEnabled(true);
		}
	}
}