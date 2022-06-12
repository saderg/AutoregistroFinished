package com.example.autoregistros.actualizar;

import static com.example.autoregistros.conectaAPI.Urls.URL_DELETE_USER;
import static com.example.autoregistros.conectaAPI.Urls.URL_PUT_USER;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.autoregistros.Dia;
import com.example.autoregistros.R;
import com.example.autoregistros.entidades.User;
import com.example.autoregistros.MainActivity;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class Update extends AppCompatActivity {

    Button cancelar;
    Button update;
    EditText updateUserName;
    EditText updatePassword;
    EditText updateEmail;
    Button deleteUser;

    int id_user;
    String user_name;
    String password;
    String email_address;
    String date_of_birth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        cancelar = findViewById(R.id.btnCancelar);
        update = findViewById(R.id.btnEjecutar);
        updateUserName = findViewById(R.id.updateUserName);
        updatePassword = findViewById(R.id.updatePassword);
        updateEmail = findViewById(R.id.updateEmail);
        deleteUser = findViewById(R.id.deleteUser);

        id_user = getIntent().getExtras().getInt("id");
        user_name = getIntent().getStringExtra("userName");
        password = getIntent().getStringExtra("password");
        email_address = getIntent().getStringExtra("email");
        date_of_birth = getIntent().getStringExtra("date_of_birth");



        System.out.println("IDDDDDDDDDDDD--------------- " + id_user);
        System.out.println(user_name);
        System.out.println(password);
        System.out.println(email_address);
        System.out.println(date_of_birth);

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Update.this, Dia.class);
                intent.putExtra("id_user" , id_user);
                intent.putExtra("user_name", user_name);
                intent.putExtra("password", password);
                intent.putExtra("email_address", email_address);
                intent.putExtra("date_of_birth", date_of_birth);
                startActivity(intent);
            }
        });

        /*updateUserName.setText(username);
        updatePassword.setText(password);
        updateEmail.setText(email);
        */

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                user_name = updateUserName.getText().toString();
                password = updatePassword.getText().toString();
                email_address = updateEmail.getText().toString();

                User user = new User(id_user, user_name, password, email_address, date_of_birth);
                updateUser(user);

                Intent intent = new Intent(Update.this, Dia.class);
                intent.putExtra("id_user" , id_user);
                intent.putExtra("user_name", user_name);
                intent.putExtra("password", password);
                intent.putExtra("email_address", email_address);
                intent.putExtra("date_of_birth", date_of_birth);
                startActivity(intent);
            }
        });

        deleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aviso();
            }
        });

    }

    public void updateUser(User user) {
        final ProgressDialog loading = new ProgressDialog(Update.this);
        loading.setMessage("Please Wait...");
        loading.setCanceledOnTouchOutside(false);
        loading.show();

        String URL = URL_PUT_USER;
        JSONObject jsonUser = new JSONObject();
        try {
            //metemos los valores en el json
            jsonUser.put("id_usuario", user.getId_usuario());
            jsonUser.put("user_name",user.getUser_name());
            jsonUser.put("password",user.getPassword());
            jsonUser.put("email_address", user.getEmail_adress());
            jsonUser.put("date_of_birth", user.getDate_of_birth());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // en la request metemos un json en vez de null
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, URL , jsonUser,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(Update.this,"Usuario actualizado",Toast.LENGTH_LONG).show();

                        try {
                            Log.d("JSON", String.valueOf(response));
                            loading.dismiss();

                            JSONObject body = response.getJSONObject("body");
                        } catch (JSONException e) {
                            e.printStackTrace();
                            loading.dismiss();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loading.dismiss();
                VolleyLog.d("Error", "Error: " + error.getMessage());
                Toast.makeText(Update.this, "" + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        Volley.newRequestQueue(this).add(jsonObjectRequest);

    }

    public void deleteUser(int id) {

        final ProgressDialog loading = new ProgressDialog(Update.this);
        loading.setMessage("Please Wait...");
        loading.setCanceledOnTouchOutside(false);
        loading.show();

        String url = URL_DELETE_USER +  "?id_usuario=" + id;
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

    public void aviso(){
        new AlertDialog.Builder(this)
                .setTitle("Eliminar cuenta")
                .setMessage("¿Estás seguro de eliminar la cuenta?")
                .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteUser(id_user);
                        Toast.makeText(Update.this, "Cuenta eliminada", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Update.this, MainActivity.class);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Update.this, Update.class);
                        startActivity(intent);
                    }
                })
                .show();
    }

}