package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.strictmode.SqliteObjectLeakedViolation;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddContact extends AppCompatActivity {
    EditText e1,e2;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
    e1=findViewById(R.id.et_name);
    e2=findViewById(R.id.et_pno);
    b1=findViewById(R.id.b_save);
    b1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            SQLiteDatabase db=openOrCreateDatabase("PhoneBook",MODE_PRIVATE,null);
            db.execSQL("create table if not exists contacts(name Varchar,phno Varchar,gp Varchar)");
            if(e1.getText().toString().equals("")  || e2.getText().toString().equals("") ) {
                Toast.makeText(AddContact.this, "Sorry. fill both", Toast.LENGTH_SHORT).show();
            }
            else
            {
                db.execSQL("insert into contacts values('" + e1.getText().toString() + "','" + e2.getText().toString() + "','')");
                Toast.makeText(AddContact.this, "Data Saved", Toast.LENGTH_SHORT).show();
                db.close();

            }
            e1.setText("");

            e2.setText("");

        }
    });
    }
}
