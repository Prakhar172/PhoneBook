package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
Button badd,bseach,bAddGroup,bSMS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        badd=findViewById(R.id.b_AddContact);
        bseach=findViewById(R.id.b_SearchContact);
        bAddGroup=findViewById(R.id.b_AddGroup);
    bSMS=findViewById(R.id.b_SMS);
        badd.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent in=new Intent(MainActivity.this,AddContact.class);
            startActivity(in);
        }
    });
        bseach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(MainActivity.this,SearchContact.class);
                startActivity(in);
            }
        });
        bAddGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(MainActivity.this,AddGroup.class);
                startActivity(in);

            }
        });
        bSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(MainActivity.this,SMS.class);
                startActivity(in);
            }
        });

    }
}
