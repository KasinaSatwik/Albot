package com.example.albot;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText username, password, repassword,age1,bloodgroup1,contacts1,houseno1,housename1,locality1,city1;
    Button signup, signin;
    DBHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        repassword = (EditText) findViewById(R.id.repassword);
        age1 = (EditText) findViewById(R.id.age);
        bloodgroup1 = (EditText) findViewById(R.id.bloodgroup);
        contacts1 = (EditText) findViewById(R.id.contacts);
        houseno1 = (EditText) findViewById(R.id.houseno);
        housename1 = (EditText) findViewById(R.id.housename);
        locality1 = (EditText) findViewById(R.id.locality);
        city1 = (EditText) findViewById(R.id.city);
        signup = (Button) findViewById(R.id.btnsignup);
        signin = (Button) findViewById(R.id.btnsignin);
        DB = new DBHelper(this);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();
                String age = age1.getText().toString();
                String bloodgroup = bloodgroup1.getText().toString();
                String contacts = contacts1.getText().toString();
                String houseno = houseno1.getText().toString();
                String housename = housename1.getText().toString();
                String locality = locality1.getText().toString();
                String city = city1.getText().toString();

                if(user.equals("")||pass.equals("")||repass.equals("")||age.equals("")||bloodgroup.equals("")||contacts.equals("")||houseno.equals("")||housename.equals("")||locality.equals("")||city.equals(""))
                    Toast.makeText(MainActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    if(pass.equals(repass)){
                        Boolean checkuser = DB.checkusername(user);
                        if(checkuser==false){
                            Boolean insert = DB.insertData(user, pass, age, bloodgroup, contacts, houseno, housename, locality, city);
                            if(insert==true){
                                Toast.makeText(MainActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(MainActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(MainActivity.this, "User already exists! please sign in", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(MainActivity.this, "Passwords not matching", Toast.LENGTH_SHORT).show();
                    }
                } }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}