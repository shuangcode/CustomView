package com.daniel.custom.ui;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.util.Vector;

public class HTTPAudioPlaylistPlayer extends Activity implements
        View.OnClickListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnPreparedListener
{
    Vector playlistItems;
    Button parseBtn, playBtn, stopBtn;
    EditText editTextUrl;
    String baseURL = "";
    MediaPlayer mediaPlayer;

    int currentPlaylistItemNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.main2);
//        parseBtn = (Button) findViewById(R.id.ParseButton);
//        playBtn = (Button) findViewById(R.id.PlayButton);
//        stopBtn = (Button) findViewById(R.id.StopButton);
//        editTextUrl=(EditText) findViewById(R.id.EditTextURL);

        playBtn.setOnClickListener(this);
        parseBtn.setOnClickListener(this);
        stopBtn.setOnClickListener(this);

        playBtn.setEnabled(false);
        stopBtn.setEnabled(false);

        mediaPlayer = new MediaPlayer();
        mediaPlayer.setOnCompletionListener(this);
        mediaPlayer.setOnPreparedListener(this);
    }

    @Override
    public void onPrepared(MediaPlayer mp)
    {
        // TODO Auto-generated method stub
        stopBtn.setEnabled(true);
        Log.v("HTTPAUDIOPLAYLIST", "Playing");
        mediaPlayer.start();
    }

    @Override
    public void onCompletion(MediaPlayer mp)
    {
        // TODO Auto-generated method stub
        Log.v("ONCOMPLETION", "called");
        mediaPlayer.stop();
        mediaPlayer.reset();

        if (playlistItems.size() > currentPlaylistItemNumber + 1)
        {
            currentPlaylistItemNumber++;
            String path = ((PlaylistFile) playlistItems
                    .get(currentPlaylistItemNumber)).getFilePath();

            try
            {
                mediaPlayer.setDataSource(path);
                mediaPlayer.prepareAsync();
            } catch (IllegalArgumentException e)
            {
                e.printStackTrace();
            } catch (IllegalStateException e)
            {
                e.printStackTrace();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onClick(View v)
    {
        // TODO Auto-generated method stub
        if (v == parseBtn)
        {
            // 下载由editTextUrl对象中的URL指定的M3U文件，并对它进行分析。
            // 分析的操作是选出任何表示待播放文件的行，创建一个PlaylistItem对象，
            // 然后把它添加到playlistItems容器里
//            parsePlaylistFile();
        } else if (v == playBtn)
        {
//            playPlaylistItems();
        } else if (v == stopBtn)
        {
            stop();
        }
    }

//    private void parsePlaylistFile()
//    {
//        // TODO Auto-generated method stub
//        playlistItems = new Vector();
//        // 为了从Web获取M3U文件，可以使用Apache软件基金会的HttpClient库，
//        // 它已被android所包括。
//        // 首先创建一个HttpClient对象，其代表类似Web浏览器的事物；
//        HttpClient httpClient = new DefaultHttpClient();
//        // 然后创建一个HttpGet对象，其表示指向一个文件的具体请求。
//        HttpGet getRequest = new HttpGet(editTextUrl.getText().toString());
//        Log.v("URI", getRequest.getURI().toString());
//        // HttpClient将执行HttpGet，并返回一个HttpResponse
//        try
//        {
//            HttpResponse httpResponse = httpClient.execute(getRequest);
//            if (httpResponse.getStatusLine().getStatusCode() != HttpStatus.SC_OK)
//            {
//                Log.v("HTTP ERROR", httpResponse.getStatusLine()
//                        .getReasonPhrase());
//            } else
//            {
//                // 在发出请求之后，可以从HttpRequest中获取一个InputStream，
//                // 其包含了所请求文件的内容
//                InputStream inputStream = httpResponse.getEntity().getContent();
//                // 借助一个BufferedReader可以逐行得遍历该文件
//                BufferedReader bufferedReader = new BufferedReader(
//                        new InputStreamReader(inputStream));
//                String line;
//                while ((line = bufferedReader.readLine()) != null)
//                {
//                    Log.v("PLAYLISTLINE", "ORIG:" + line);
//                    if (line.startsWith("#"))
//                    {
//                        // 元数据，可以做更多的处理，但现在忽略它
//                    } else if (line.length() > 0)
//                    {
//                        // 如果它的长度大于0，那么就假设它是一个播放列表条目
//                        String filePath = "";
//                        if (line.startsWith("http://"))
//                        {
//                            // 如果行以“http://”开头那么就把它作为流的完整URL
//                            filePath = line;
//                        } else
//                        {
//                            // 否则把它作为一个相对的URL，
//                            // 同时把针对该M3U文件的原始请求的URL附加上去
//                            filePath = getRequest.getURI().resolve(line)
//                                    .toString();
//                        }
//                        // 将其添加到播放列表条目的容器中去
//                        PlaylistFile playlistFile = new PlaylistFile(filePath);
//                        playlistItems.add(playlistFile);
//                    }
//                }
//            }
//        } catch (ClientProtocolException e)
//        {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (IOException e)
//        {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        playBtn.setEnabled(true);
//    }
//
//    private void playPlaylistItems()
//    {
//        playBtn.setEnabled(false);
//        currentPlaylistItemNumber = 0;
//        if (playlistItems.size() > 0)
//        {
//            String path = ((PlaylistFile) playlistItems
//                    .get(currentPlaylistItemNumber)).getFilePath();
//            // 在提取出流的或者文件的路径之后，就可以在MediaPlayer上的setDataSource方法使用它了
//            try
//            {
//                mediaPlayer.setDataSource(path);
//                mediaPlayer.prepareAsync();
//            } catch (IllegalArgumentException e)
//            {
//                e.printStackTrace();
//            } catch (IllegalStateException e)
//            {
//                e.printStackTrace();
//            } catch (IOException e)
//            {
//                e.printStackTrace();
//            }
//        }
//    }

    private void stop()
    {
        mediaPlayer.pause();
        playBtn.setEnabled(true);
        stopBtn.setEnabled(false);
    }

    class PlaylistFile
    {
        String filePath;

        public PlaylistFile(String _filePath)
        {
            filePath = _filePath;
        }

        public void setFilePath(String _filePath)
        {
            filePath = _filePath;
        }

        public String getFilePath()
        {
            return filePath;
        }
    }
}