package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;

public class AddGroup extends AppCompatActivity {
Button b1,b;
EditText et1;
String temp="";
int i;
    Button tv1;
    LinearLayout ll;

    SharedPreferences sp;
    /*void fill(int abc,String gn1) {
        for (int i = 0; i < abc; i++) {
            Toast.makeText(this, "heey", Toast.LENGTH_SHORT).show();

            ab[i] = gn1;
        }
        for (int j = 0; j < i; j++) {
            Button b = new Button(getApplicationContext());
            i = j;
            b.setText("" + ab[i]);
            Toast.makeText(this, "heey", Toast.LENGTH_SHORT).show();
            ll.addView(b);
        }
    }
*/
    void fill() {
        try {
            SQLiteDatabase db = openOrCreateDatabase("Group", MODE_PRIVATE, null);
            Cursor cur = db.rawQuery("select * from contact ", null);
            if (cur.moveToFirst()) {
                do {
                    if(temp.equals(""))
                    {
                  tv1 = new Button(AddGroup.this);
                    tv1.setText(cur.getString(1));
                    temp=cur.getString(1);
                    tv1.setTextSize(12);
                        tv1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String tv=tv1.getText().toString();
                                sp=getSharedPreferences("abcd",MODE_PRIVATE);
                                SharedPreferences.Editor ed=sp.edit();
                                ed.putString("name1",tv);
                                Toast.makeText(AddGroup.this, "Clicked on "+tv, Toast.LENGTH_SHORT).show();
                                ed.commit();
                                Intent in=new Intent(AddGroup.this,gp.class);
                                startActivity(in);

                            }
                        });


                                ll.addView(tv1);
                    }
                    else if(temp.equals(""+cur.getString(1)))
                        {
                   continue;
                        }
                    else
                        {
                       tv1 = new Button(AddGroup.this);
                        tv1.setText(cur.getString(1));
                        temp=cur.getString(1);
                        tv1.setTextSize(12);
                            tv1.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    String tv=tv1.getText().toString();
                                    sp=getSharedPreferences("abcd",MODE_PRIVATE);
                                    SharedPreferences.Editor ed=sp.edit();

                                    ed.putString("name1",tv);

                                    Toast.makeText(AddGroup.this, "Clicked on "+tv, Toast.LENGTH_SHORT).show();
                                    ed.commit();
                                    Intent in=new Intent(AddGroup.this,gp.class);
                                    startActivity(in);

                                }
                            });
                            ll.addView(tv1);
                    }
                } while (cur.moveToNext());
            } else {
                Toast.makeText(AddGroup.this, "Sorry no record found", Toast.LENGTH_SHORT).show();
                db.close();
            }
        } catch (Exception e) {
            Toast.makeText(AddGroup.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_group);
        b1 = findViewById(R.id.bADD);
        ll = findViewById(R.id.ll);
        et1 = findViewById(R.id.et1);
        ll.setOrientation(LinearLayout.VERTICAL);
        fill();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp = getSharedPreferences("abc", MODE_PRIVATE);
                SharedPreferences.Editor ed = sp.edit();

                ed.putString("name", et1.getText().toString());
                ed.commit();
                Intent in = new Intent(AddGroup.this, chooser.class);
                startActivity(in);

            }
        });
//
//                 tv1=(Button)view;

//        SharedPreferences sp = getSharedPreferences("name", MODE_PRIVATE);
//        SQLiteDatabase db1 = SQLiteDatabase.openDatabase("" + sp.getString("abc", "Sorry"), null, 0);
//        String str=sp.getString("abc","sorry");
//        TextView tv=new TextView(AddGroup.this);
//          tv.setText(str);
//           ll.addView(tv);
//
}
}
