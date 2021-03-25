package com.example.albot;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {


    private Button Chatbot;
    private Button Edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
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


    }

    private void OpenEditdetails() {
        Intent intent = new Intent(this,Editdetails.class);
        startActivity(intent);
    }


    private void OpenChatbot() {
        Intent intent = new Intent(this, Chatbot.class);
        startActivity(intent);
    }


}