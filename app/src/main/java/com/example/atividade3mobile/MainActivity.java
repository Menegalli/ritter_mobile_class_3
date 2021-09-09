package com.example.atividade3mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;


public class MainActivity extends AppCompatActivity {

    //variavel global pra queue
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         queue = Volley.newRequestQueue(this);

        // inicio dos listeners dos botoes
        Button getTodo = findViewById(R.id.buttonGetTodo);
        getTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://jsonplaceholder.typicode.com/todos"; // url do jsonplaceholder, diferente nos 3 botões
                String textId = "todo"; // string condicional id para os ifs do método
                Integer jsonID = 24;  // index do json
                jsonRequest(url, textId, jsonID);
            }
        });

        Button getPost = findViewById(R.id.buttonGetPost);
        getPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://jsonplaceholder.typicode.com/posts";
                String textId = "post";
                Integer jsonID = 12;
                jsonRequest(url, textId, jsonID);
            }
        });

        Button getAlbum = findViewById(R.id.buttonGetAlbum);
        getAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://jsonplaceholder.typicode.com/albums";
                String textId = "album";
                Integer jsonID = 33;
                jsonRequest(url, textId, jsonID);
            }
        });
    }

    // metodo que fiz para realizar os multiplos requests GET do jsonplaceholder, me inspirando no código da última aula
    private void jsonRequest(String url, String textID, Integer jsonID){
        JsonArrayRequest req = new JsonArrayRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        TextView tv = null;
                        if(textID.equals("todo")){
                            tv = findViewById(R.id.textResults1);
                        } else if(textID.equals("post")){
                            tv = findViewById(R.id.textResults2);
                        } else if(textID.equals("album")){
                            tv = findViewById(R.id.textResults3);
                        }

                        try {
                            tv.setText(response.length()+"\n"+response.getJSONObject(jsonID).toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                TextView tv = null;
                if(textID.equals("todo")){
                    tv = findViewById(R.id.textResults1);
                } else if(textID.equals("post")){
                    tv = findViewById(R.id.textResults2);
                } else if(textID.equals("album")){
                    tv = findViewById(R.id.textResults3);
                }

                tv.setText(error.getMessage());
            }
        });
        queue.add(req);
    }


}