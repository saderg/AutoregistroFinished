package com.example.autoregistros;

import static com.example.autoregistros.conectaAPI.Urls.URL_DELETE_EMOTION;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.autoregistros.adapter.EmotionAdapter;
import com.example.autoregistros.entidades.Emotion;

import org.json.JSONArray;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;


public class ListEmotions extends AppCompatActivity{

    public ImageButton graphicButton, dayButton, listButton, resourcesButton;
    private ArrayAdapter<String> emotionAdapter;

    private int id_user, id_emotion;
    private String user_name, password, email_address, date_of_birth;
    private String emotion_type, emotion_reason;
    private List<String> emotions = new ArrayList<>();

    EmotionAdapter adapter;

    RecyclerView recyclerView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_emotions);

        getIntent();
        id_user = getIntent().getExtras().getInt("id_user");
        id_emotion = getIntent().getExtras().getInt("id_emotion");
        emotion_type = getIntent().getStringExtra("emotion_type");
        emotion_reason = getIntent().getStringExtra("emotion_reason");

        System.out.println("SYSOOOOOO" + id_user);

        graphicButton = findViewById(R.id.buttonGraphic);
        dayButton = findViewById(R.id.buttonDia);
        graphicButton = findViewById(R.id.buttonGraphic);
        resourcesButton = findViewById(R.id.buttonResources);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        //MENU BOTONES
        graphicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListEmotions.this, Graphic.class);
                intent.putExtra("id_user", id_user);
                intent.putExtra("user_name" , user_name);
                intent.putExtra("password", password);
                intent.putExtra("email_address" , email_address);
                intent.putExtra("date_of_birth" , date_of_birth);
                startActivity(intent);
            }
        });

        dayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListEmotions.this, Dia.class);
                intent.putExtra("id_user", id_user);
                intent.putExtra("user_name" , user_name);
                intent.putExtra("password", password);
                intent.putExtra("email_address" , email_address);
                intent.putExtra("date_of_birth" , date_of_birth);
                startActivity(intent);
            }
        });

        resourcesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListEmotions.this, Resources.class);
                intent.putExtra("id_user", id_user);
                intent.putExtra("user_name" , user_name);
                intent.putExtra("password", password);
                intent.putExtra("email_address" , email_address);
                intent.putExtra("date_of_birth" , date_of_birth);
                startActivity(intent);
            }
        });

        getByRange(id_user);

    }

    public void getByRange(int id_usuario){

        ArrayList<Emotion> arrayListEmociones = new ArrayList<>();

        final ProgressDialog loading = new ProgressDialog(ListEmotions.this);
        loading.setMessage("Please Wait...");
        loading.setCanceledOnTouchOutside(false);
        loading.show();

        String url = "http://192.168.1.31:8086/app/emotions/emotion/range?user_id=" + id_usuario + "&start_date=1000-01-01T00:00:00&end_date=2030-06-12T23:59:59";

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

        StringRequest peticionApi = new StringRequest(Request.Method.GET , url,
                (response -> {
                    Log.i("TAG", "TODO BIEN");
                    try {
                        JSONArray arrayEmociones = new JSONArray(response);

                        for (int i = 0; i < arrayEmociones.length(); i++) {
                            loading.dismiss();

                            Emotion emotion = new Emotion();

                            emotion.setId_emocion(arrayEmociones.getJSONObject(i).getInt("id_emocion"));
                            emotion.setId_usuario(arrayEmociones.getJSONObject(i).getInt("id_usuario"));
                            emotion.setEmotion_type(arrayEmociones.getJSONObject(i).getString("emotion_type"));
                            emotion.setEmotion_reason(arrayEmociones.getJSONObject(i).getString("emotion_reason"));
                            emotion.setEmotion_date(format.parse(arrayEmociones.getJSONObject(i).getString("emotion_date")));
                            System.out.println(emotion.toString());

                            arrayListEmociones.add(emotion);

                            adapter = new EmotionAdapter(arrayListEmociones);

                            recyclerView.setAdapter(adapter);

                        }

                        adapter.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                aviso();
                            }
                        });
                    }catch (Exception ex){
                        ex.printStackTrace();
                    }
                }),
                (error ->{
                    Log.e("error", "Error al pedir los datos." + error.getMessage());
                    loading.dismiss();
                }));
        Volley.newRequestQueue(this).add(peticionApi);

    }
    public void aviso(){
        new AlertDialog.Builder(this)
                .setTitle("Eliminar emoción")
                .setMessage("¿Estás seguro de eliminar la emoción?")
                .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteEmotion(id_emotion);
                        Toast.makeText(ListEmotions.this, "Emoción eliminada", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ListEmotions.this, ListEmotions.class);
                        intent.putExtra("id_user" , id_user);
                        intent.putExtra("id_emotion" , id_emotion);
                        intent.putExtra("emotion_type" , emotion_type);
                        intent.putExtra("emotion_reason" , emotion_reason);

                        startActivity(intent);
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(ListEmotions.this, ListEmotions.class);
                        intent.putExtra("id_user" , id_user);
                        intent.putExtra("id_emotion" , id_emotion);
                        intent.putExtra("emotion_type" , emotion_type);
                        intent.putExtra("emotion_reason" , emotion_reason);
                        startActivity(intent);
                    }
                })
                .show();
    }

    private void deleteEmotion(int id_emocion) {

        final ProgressDialog loading = new ProgressDialog(ListEmotions.this);
        loading.setMessage("Please Wait...");
        loading.setCanceledOnTouchOutside(false);
        loading.show();

        String url = URL_DELETE_EMOTION + "?id_emocion=" + id_emocion;
        StringRequest stringRequest = new StringRequest(Request.Method.DELETE, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();
                Log.d("response_del", response + "  " + url);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loading.dismiss();
                Log.d("error del", ""+error);
            }
        }){
            protected HashMap<String, String> getParams() throws AuthFailureError {
                loading.dismiss();
                HashMap<String, String> map = new HashMap<>();
                map.put("id_usuario", "4");
                return map;
            }
        };
        Volley.newRequestQueue(this).add(stringRequest);

    }




}