package com.example.albot;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Editdetails extends AppCompatActivity {
    EditText username,password,age,bloodgroup,contacts,houseno,housename,locality,city ;
    Button update, delete, view;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_details);
        username = findViewById(R.id.username1);
        password = findViewById(R.id.password1);
        age = findViewById(R.id.age1);
        bloodgroup = findViewById(R.id.bloodgroup1);
        contacts = findViewById(R.id.contacts1);
        houseno = findViewById(R.id.houseno1);
        housename = findViewById(R.id.housename1);
        locality = findViewById(R.id.locality1);
        city = findViewById(R.id.city1);
        update = findViewById(R.id.btnUpdate);
        delete = findViewById(R.id.btnDelete);
        view = findViewById(R.id.btnView);
        DB = new DBHelper(this);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usertxt = username.getText().toString();
                String passtxt = password.getText().toString();
                String agetxt = age.getText().toString();
                String bloodgrouptxt = bloodgroup.getText().toString();
                String contactstxt = contacts.getText().toString();
                String housenotxt = houseno.getText().toString();
                String housenametxt = housename.getText().toString();
                String localitytxt = locality.getText().toString();
                String citytxt = city.getText().toString();

                Boolean checkupdatedata = DB.updateuserdata(usertxt, passtxt, agetxt,bloodgrouptxt,contactstxt,housenotxt,housenametxt,localitytxt,citytxt);
                if(checkupdatedata==true)
                    Toast.makeText(Editdetails.this, "Entry Updated", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(Editdetails.this, "New Entry Not Updated", Toast.LENGTH_SHORT).show();
            }        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usernameTXT = username.getText().toString();
                Boolean checkudeletedata = DB.deletedata(usernameTXT);
                if(checkudeletedata==true)
                    Toast.makeText(Editdetails.this, "Entry Deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(Editdetails.this, "Entry Not Deleted", Toast.LENGTH_SHORT).show();
            }        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getdata();
                if(res.getCount()==0){
                    Toast.makeText(Editdetails.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Name :"+res.getString(0)+"\n");
                    buffer.append("Age :"+res.getString(2)+"\n");
                    buffer.append("Blood Group :"+res.getString(3)+"\n");
                    buffer.append("Contact :"+res.getString(4)+"\n");
                    buffer.append("House no :"+res.getString(5)+"\n");
                    buffer.append("House name :"+res.getString(6)+"\n");
                    buffer.append("Locality :"+res.getString(7)+"\n");
                    buffer.append("City :"+res.getString(8)+"\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(Editdetails.this);
                builder.setCancelable(true);
                builder.setTitle("User Entry");
                builder.setMessage(buffer.toString());
                builder.show();
            }        });
    }}
