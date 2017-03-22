package fr.esilv.s8.youtubedataapi.activities;

import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import fr.esilv.s8.youtubedataapi.R;
import fr.esilv.s8.youtubedataapi.adapters.VideoAdapter;
import fr.esilv.s8.youtubedataapi.models.Video;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private EditText editText;
    String text;
    private List<Video.ItemsBean> listVideo;
    private String key = "AIzaSyABziWyGb1ygmHDh6b46QFGREk7XM_Axo8";
    private String url = "https://www.googleapis.com/youtube/v3/search?part=snippet";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Bind the XML ListView to the Java ListView
        editText = (EditText) findViewById(R.id.editText);
        text = editText.getText().toString();
        listView = (ListView) findViewById(R.id.idListView);
        getVideo(text);

    }


    private void getVideo(String Request){

            StringRequest videosRequest = new StringRequest("https://www.googleapis.com/youtube/v3/search?part=snippet&maxResults=25&q="+text+"&key=AIzaSyABziWyGb1ygmHDh6b46QFGREk7XM_Axo8", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    //parse data from webservice to get Contracts as Java object
                    Video videos = new Gson().fromJson(response, Video.class);
                    listVideo = videos.getItems();
                    Log.d("test", listView.toString());
                    setAdapter(videos);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("Videos", "Error");
                }
            });

            Volley.newRequestQueue(this).add(videosRequest);

    }

    private void setAdapter(Video videos) {
        VideoAdapter adapter = new VideoAdapter(this, listVideo);
        listView.setAdapter(adapter);
    }
}