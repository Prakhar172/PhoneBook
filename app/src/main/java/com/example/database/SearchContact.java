package com.example.database;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class SearchContact extends AppCompatActivity {
    EditText e1;
    LinearLayout ll;
    TextView tv1,tv;

    void fill()
    {
        try
        {
            SQLiteDatabase db=openOrCreateDatabase("PhoneBook",MODE_PRIVATE,null);
            Cursor cur=db.rawQuery("select * from contacts",null);
            if(cur.moveToFirst())
            {
                do
                    {
                        TextView tv=new TextView(SearchContact.this);
                        tv.setText(cur.getString(0));
                        tv.setTextSize(24);
                        TextView tv1=new TextView(SearchContact.this);
                        tv1.setText(cur.getString(1));
                        tv1.setTextSize(12);
                        ll.addView(tv);
                        ll.addView(tv1);
                        registerForContextMenu(tv);
                        registerForContextMenu(tv1);

                    }while(cur.moveToNext());
            }
            else
            {
                Toast.makeText(this, "Sorry no record found", Toast.LENGTH_SHORT).show();
                db.close();
            }
        }
        catch(Exception e)
        {
            Toast.makeText(SearchContact.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    void fill(String s)
    {
        try
        {
            SQLiteDatabase db=openOrCreateDatabase("PhoneBook",MODE_PRIVATE,null);
            Cursor cur=db.rawQuery("select * from contacts where name like '"+s+"%'",null);
            if(cur.moveToFirst())
            {
                do
                {
                    TextView tv=new TextView(SearchContact.this);
                    tv.setText(cur.getString(0));
                    tv.setTextSize(24);
                    TextView tv1=new TextView(SearchContact.this);
                    tv1.setText(cur.getString(1));
                    tv1.setTextSize(12);
                    ll.addView(tv);
                    ll.addView(tv1);

                }while(cur.moveToNext());
            }
            else
            {
                Toast.makeText(this, "Sorry no record found", Toast.LENGTH_SHORT).show();
                db.close();
            }
        }
        catch(Exception e)
        {
            Toast.makeText(SearchContact.this, "e.getMessage()", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_contact);
        e1 = findViewById(R.id.et_seach);
        ll = findViewById(R.id.ll);
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

    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        tv1=(TextView)v;
        tv=(TextView)v;

        MenuInflater mi=getMenuInflater();
        mi.inflate(R.menu.mymenu,menu);

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.m1)
        {
            SQLiteDatabase db=openOrCreateDatabase("PhoneBook",MODE_PRIVATE,null);
            //SQLiteDatabase db=SQLiteDatabase.openDatabase("PhoneBook",null,0);
            db.execSQL("delete from contacts where name = '"+tv1.getText().toString()+"'");
            db.close();
            ll.removeView(tv);
            ll.removeView(tv1);

            //sta
            // rtActivity(new Intent(SearchContact.this,MainActivity.class));
        }
        if(item.getItemId()==R.id.m2)
        {
            Toast.makeText(this, "Clicked on Details", Toast.LENGTH_SHORT).show();
        }
        return super.onContextItemSelected(item);
    }
}
