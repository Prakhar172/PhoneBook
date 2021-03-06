package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.zip.CheckedInputStream;
import java.util.zip.Inflater;

public class chooser2 extends AppCompatActivity {
    EditText e1;
    LinearLayout ll, l2;
    Button b1;
    SharedPreferences sp;

    void fill() {
        try {
            SQLiteDatabase db = openOrCreateDatabase("PhoneBook", MODE_PRIVATE, null);
            Cursor cur = db.rawQuery("select * from contacts", null);
            if (cur.moveToFirst()) {

                do {
                    CheckBox cb1 = new CheckBox(chooser2.this);
                    cb1.setText(cur.getString(0));
                    ll.addView(cb1);


                    CheckBox cb = new CheckBox(chooser2.this);
                    cb.setText(cur.getString(1));
                    ll.addView(cb);


                } while (cur.moveToNext());
            } else {
                Toast.makeText(this, "Sorry no record found", Toast.LENGTH_SHORT).show();
                db.close();
            }
        } catch (Exception e) {
            Toast.makeText(this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    void fill(String s) {
        try {
            SQLiteDatabase db = openOrCreateDatabase("PhoneBook", MODE_PRIVATE, null);
            Cursor cur = db.rawQuery("select * from contacts where name like '" + s + "%'", null);
            if (cur.moveToFirst()) {
                do {

                    CheckBox cb1 = new CheckBox(chooser2.this);
                    cb1.setText(cur.getString(0));
                    ll.addView(cb1);

                    CheckBox cb = new CheckBox(chooser2.this);
                    cb.setText(cur.getString(1));
                    ll.addView(cb);
                    l2.addView(ll);


                } while (cur.moveToNext());
            } else {
                Toast.makeText(this, "Sorry no record found", Toast.LENGTH_SHORT).show();
                db.close();
            }
        } catch (Exception e) {
            Toast.makeText(this, "e.getMessage()", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chooser);
        e1 = findViewById(R.id.e1);
        ll = findViewById(R.id.ll);
        l2 = findViewById(R.id.l2);
        b1=findViewById(R.id.b1);

        fill();

        e1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                ll.removeAllViews();
                fill(e1.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i=0;i<ll.getChildCount();i++)
                {
                    if(ll.getChildAt(i) instanceof CheckBox)
                    {
                        CheckBox cb=(CheckBox)ll.getChildAt(i);
                        for(int j=0;j<1;j++) {
                            if (cb.isChecked() == true) {
                                sp = getSharedPreferences("abcde", MODE_PRIVATE);
                                SharedPreferences.Editor ed = sp.edit();

                                ed.putString("name2", cb.getText().toString());
                                ed.commit();
                            } else {
                                cb.setChecked(false);

                            }
                        }
                    }
                }
                Intent in=new Intent(chooser2.this,SMS.class);
                startActivity(in);


            }
        });
    }
}
