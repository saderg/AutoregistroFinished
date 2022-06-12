package com.example.autoregistros;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class Resources extends AppCompatActivity {

    TextView info, t, t1, t2, t3, t4, t5, t6, t7;
    public ImageButton graphicButton, dayButton, listButton;

    int id_user;
    String user_name, password, email_address, date_of_birth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resources);

        getIntent();
        id_user = getIntent().getExtras().getInt("id_user");
        user_name = getIntent().getStringExtra("user_name");
        password = getIntent().getStringExtra("password");
        email_address = getIntent().getStringExtra("email_address");
        date_of_birth = getIntent().getStringExtra("date_of_birth");

        graphicButton = findViewById(R.id.buttonGraphic);
        dayButton = findViewById(R.id.buttonDia);
        listButton = findViewById(R.id.buttonEmotions);

        //MENU BOTONES
        graphicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Resources.this, Graphic.class);
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
                Intent intent = new Intent(Resources.this, Dia.class);
                intent.putExtra("id_user", id_user);
                intent.putExtra("user_name" , user_name);
                intent.putExtra("password", password);
                intent.putExtra("email_address" , email_address);
                intent.putExtra("date_of_birth" , date_of_birth);
                startActivity(intent);
            }
        });

        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Resources.this, ListEmotions.class);
                intent.putExtra("id_user", id_user);
                intent.putExtra("user_name" , user_name);
                intent.putExtra("password", password);
                intent.putExtra("email_address" , email_address);
                intent.putExtra("date_of_birth" , date_of_birth);
                startActivity(intent);
            }
        });


        info = findViewById(R.id.infoTxt);
        t = findViewById(R.id.link);
        t.setMovementMethod(LinkMovementMethod.getInstance());
        t1 = findViewById(R.id.link1);
        t1.setMovementMethod(LinkMovementMethod.getInstance());

        t2 = findViewById(R.id.link2);
        t2.setMovementMethod(LinkMovementMethod.getInstance());

        t3 = findViewById(R.id.link3);
        t3.setMovementMethod(LinkMovementMethod.getInstance());

        t4 = findViewById(R.id.link4);
        t4.setMovementMethod(LinkMovementMethod.getInstance());

        t5 = findViewById(R.id.link5);
        t5.setMovementMethod(LinkMovementMethod.getInstance());

        t6 = findViewById(R.id.link6);
        t6.setMovementMethod(LinkMovementMethod.getInstance());

        t7 = findViewById(R.id.link7);
        t7.setMovementMethod(LinkMovementMethod.getInstance());

    }
}