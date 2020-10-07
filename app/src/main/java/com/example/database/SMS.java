package com.example.database;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class SMS extends AppCompatActivity {
Button b1,b2,b3;
EditText e1, e2;
SharedPreferences sp;
 @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
        ActivityCompat.requestPermissions(SMS.this,new String[]{Manifest.permission.SEND_SMS},1);
        b1=findViewById(R.id.bsend);
        b2=findViewById(R.id.bchoose);
        b3=findViewById(R.id.bSpeech);
        e1=findViewById(R.id.etphno_);
        e2=findViewById(R.id.etsms);

            sp = getSharedPreferences("abcde", MODE_PRIVATE);

            e1.setText(sp.getString("name2", "Sorry"));

        b1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               SmsManager sm=SmsManager.getDefault();
               sm.sendTextMessage(e1.getText().toString(),null,e2.getText().toString(),null,null);
               Toast.makeText(SMS.this, "sms is send", Toast.LENGTH_SHORT).show();
           }
       });

b2.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent in=new Intent(SMS.this,chooser2.class);
        startActivity(in);
    }
});

b3.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent in=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        in.putExtra(RecognizerIntent.EXTRA_PROMPT,"1");
        startActivityForResult(in,1  );
    }
});
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ArrayList<String> list=data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
 /*  for(String s:list)
   {
       e2.setText(e2.getText().toString()+s);
   }*/
 e2.setText(list.get(0));
    }
}
