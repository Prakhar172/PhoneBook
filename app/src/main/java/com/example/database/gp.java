package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class gp extends AppCompatActivity {
TextView t1;
LinearLayout ll;
String str;
    SharedPreferences sp;
    void fill(String s) {
        try {
            SQLiteDatabase db = openOrCreateDatabase("Group", MODE_PRIVATE, null);
            Cursor cur = db.rawQuery("select * from contact where gp='"+s+"' ", null);
            if (cur.moveToFirst()) {
                do {
                    TextView tv1 = new TextView(gp.this);
                    tv1.setText(cur.getString(0));
                    tv1.setTextSize(20);
                    ll.addView(tv1);
                } while (cur.moveToNext());
            } else {
                Toast.makeText(gp.this, "Sorry no record found", Toast.LENGTH_SHORT).show();
                db.close();
            }
        } catch (Exception e) {
            Toast.makeText(gp.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gp);
    t1=findViewById(R.id.t1);
    ll=findViewById(R.id.ll);
        sp = getSharedPreferences("abcd", MODE_PRIVATE);
str=sp.getString("name1", "Sorry");
fill(str);



    }
}
