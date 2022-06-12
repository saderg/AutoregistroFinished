
package com.example.autoregistros.registro;

import static com.example.autoregistros.conectaAPI.Urls.URL_POST_USER;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.autoregistros.MainActivity;
import com.example.autoregistros.R;
import com.example.autoregistros.entidades.User;

import org.json.JSONException;
import org.json.JSONObject;

public class CreateUser extends AppCompatActivity {

    FragmentTransaction transaccion;
    Fragment usernameF, passwordF, emailF, dateOfBirthF;
    String user_name, password, email_address, date_of_birth;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        usernameF = new com.example.autoregistros.registro.UsernameFragment();
        passwordF = new com.example.autoregistros.registro.PasswordFragment();
        emailF = new com.example.autoregistros.registro.EmailFragment();
        dateOfBirthF = new com.example.autoregistros.registro.Date_of_birthFragment();

        getSupportFragmentManager().beginTransaction().add(R.id.containerFragment, usernameF).commit();

    }

    public void onClick(View view){

        transaccion = getSupportFragmentManager().beginTransaction();

        switch (view.getId()){
            case R.id.botonUserName:
                EditText user_name_text = findViewById(R.id.editUserName);
                user_name = user_name_text.getText().toString();
                transaccion.replace(R.id.containerFragment, passwordF).commit();
                break;
            case R.id.botonPassword:
                EditText password_text = findViewById(R.id.editPassword);
                password = password_text.getText().toString();
                transaccion.replace(R.id.containerFragment, emailF).commit();
                break;
            case R.id.botonEmail:
                EditText email_text = findViewById(R.id.editEmail);
                email_address = email_text.getText().toString();
                transaccion.replace(R.id.containerFragment, dateOfBirthF).commit();
                break;
            case R.id.botonDateOfBirth:
                EditText date_of_birth_text = findViewById(R.id.editBirth);

                date_of_birth = date_of_birth_text.getText().toString();

                Intent intent = new Intent(CreateUser.this, MainActivity.class);
                startActivity(intent);

                User user = new User(user_name, password, email_address, date_of_birth);
                postUser(user, context);
                break;
        }
    }

    public void postUser(User user, Context context) {

        System.out.println();
        final ProgressDialog loading = new ProgressDialog(context);
        loading.setMessage("Please Wait...");
        loading.setCanceledOnTouchOutside(false);
        loading.show();

        Log.i("user", user.toString());
        String URL = URL_POST_USER;
        JSONObject jsonUser = new JSONObject();
        try {
            //metemos los valores en el json
            jsonUser.put("user_name",user.getUser_name());
            jsonUser.put("password",user.getPassword());
            jsonUser.put("email_address", user.getEmail_adress());
            jsonUser.put("date_of_birth", user.getDate_of_birth());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        // en la request metemos un json en vez de null
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL , jsonUser,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        aviso();
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
                Toast.makeText(context,"Ya existe ese usuario",Toast.LENGTH_LONG).show();
            }
        });
        Volley.newRequestQueue(context).add(jsonObjectRequest);
    }


    public void aviso(){
        new AlertDialog.Builder(this)
                .setTitle("¡Genial!")
                .setMessage("Ya eres usuario de la aplicación")
                .show();
    }


}