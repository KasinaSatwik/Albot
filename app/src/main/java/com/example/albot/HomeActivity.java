package com.example.albot;

import android.content.ActivityNotFoundException;
import android.content.Intent;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.alicebot.ab.ParseState;
import org.w3c.dom.Node;

public class HomeActivity extends AppCompatActivity {


    private Button Chatbot;
    private Button Edit;
    private Button Maps;
    private DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        DB = new DBHelper(this);
        Chatbot = (Button) findViewById(R.id.chatbotbutton);
        Chatbot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenChatbot();
            }
        });
        Edit = (Button) findViewById(R.id.editdetails);
        Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenEditdetails();
            }
        });
        Maps = (Button) findViewById(R.id.maps);
        Maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Openmaps();
            }
        });


    }

    private void Openmaps() {

        displaytrack();
    }

    private void displaytrack() {
        Cursor res = DB.getdata();
        if (res.getCount() == 0) {
            Toast.makeText(HomeActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
            return;
        }
        StringBuffer address = new StringBuffer();
        while (res.moveToNext()) {
            address.append(res.getString(6) + "," + res.getString(7) + "," + res.getString(8));

        }
        try {
            Uri uri = Uri.parse("https://www.google.com/maps/dir/?api=1&destination=" + address);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.setPackage("com.google.android.apps.maps");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps");
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        }
    }

    private void OpenEditdetails() {
        Intent intent = new Intent(this, Editdetails.class);
        startActivity(intent);
    }


    private void OpenChatbot() {
        Intent intent = new Intent(this, Chatbot.class);
        startActivity(intent);
    }


}