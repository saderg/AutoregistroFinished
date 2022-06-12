package com.example.autoregistros;

import static com.example.autoregistros.conectaAPI.Urls.URL_GET_LOGIN;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.autoregistros.registro.CreateUser;
import com.example.autoregistros.entidades.Emotion;
import com.example.autoregistros.entidades.User;
import com.example.autoregistros.registro.CreateUser;

import org.json.JSONArray;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<User> listUsuarios;
    ArrayList<Emotion> listEmotions;

    public EditText cajaUsuario;
    public EditText cajaPassword;
    public Button botonLogin;
    public Button botonRegistro;

    Context context = MainActivity.this;

    private RequestQueue queue; //es una cola donde se van gestionando los request

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.SplashTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listUsuarios = new ArrayList<>();
        queue = Volley.newRequestQueue(this); //se inicializa la cola en un contexto
        cajaUsuario = this.findViewById(R.id.cajaUsuario);
        cajaPassword = this.findViewById(R.id.cajaContraseña);
        botonLogin = this.findViewById(R.id.botonLogin);
        botonRegistro = this.findViewById(R.id.registroLogin);

        botonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user_name = cajaUsuario.getText().toString();
                String password = cajaPassword.getText().toString();
                getLogin(user_name, password);
            }
        });
        botonRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CreateUser.class);
                startActivity(intent);
            }
        });

        getIntent();
    }

    public void getLogin(String user_name, String password) {
        ArrayList<User> arrayListaUsuarios = new ArrayList<>();
        String url = URL_GET_LOGIN + "?user_name=" + user_name + "&password=" + password;

        System.out.println(user_name + "  " + password);
        System.out.println(url);

        User user = new User();

        StringRequest peticionApi = new StringRequest(Request.Method.GET , url,
                (response -> {
                    Log.i("TAG", "TODO BIEN");
                    try {
                        JSONArray arrayUsers = new JSONArray(response);
                        for (int i = 0; i < arrayUsers.length(); i++) {

                            user.setId_usuario(arrayUsers.getJSONObject(i).getInt("id_usuario"));
                            user.setUser_name(arrayUsers.getJSONObject(i).getString("user_name"));
                            user.setPassword(arrayUsers.getJSONObject(i).getString("password"));
                            user.setEmail_adress(arrayUsers.getJSONObject(i).getString("email_address"));
                            user.setDate_of_birth(arrayUsers.getJSONObject(i).getString("date_of_birth"));

                            System.out.println(user.toString());

                            arrayListaUsuarios.add(user);
                        }
                        if (arrayListaUsuarios != null) {
                            Intent intent = new Intent(MainActivity.this, Dia.class);
                            intent.putExtra("id_user", user.getId_usuario());
                            intent.putExtra("user_name" , user.getUser_name());
                            intent.putExtra("password", user.getPassword());
                            intent.putExtra("email_address" , user.getEmail_adress());
                            intent.putExtra("date_of_birth" , user.getDate_of_birth());
                            Log.i("LOGIN USER INTENT MAIN", user.toString());
                            startActivity(intent);
                        }
                    }catch (Exception ex){
                        ex.printStackTrace();
                    }
                }),
                (error ->{
                    Log.e("error", "Error al pedir los datos." + error.getMessage());
                    aviso();
                }));
        Volley.newRequestQueue(context).add(peticionApi);

    }

    public void aviso(){
        new AlertDialog.Builder(this)
                .setTitle("Usuario no encontrado")
                .setMessage("¿Tienes una cuenta creada?")
                .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MainActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Registrarme", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MainActivity.this, CreateUser.class);
                        startActivity(intent);
                    }
                })
                .show();
    }

}