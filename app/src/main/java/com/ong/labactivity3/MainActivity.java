package com.ong.labactivity3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private EditText mSchool1,mSchool2,mSchool3,mSchool4,mSchool5,mSchool6,mSchool7,mSchool8;
    private Button mSave, mNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeWidget();


        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Set<String> set = new HashSet<>();

                set.add(String.valueOf(mSchool1.getText()));
                set.add(String.valueOf(mSchool2.getText()));
                set.add(String.valueOf(mSchool3.getText()));
                set.add(String.valueOf(mSchool4.getText()));
                set.add(String.valueOf(mSchool5.getText()));
                set.add(String.valueOf(mSchool6.getText()));
                set.add(String.valueOf(mSchool7.getText()));
                set.add(String.valueOf(mSchool8.getText()));
                Toast.makeText(view.getContext(), "Saving", Toast.LENGTH_SHORT).show();
                insertData(convertSetString(set));
                Toast.makeText(view.getContext(), "Successfully Saved", Toast.LENGTH_LONG).show();
            }
        });

        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity(VerifySchool.class);
            }
        });
    }

    private String convertSetString(Set<String> set){
        StringBuilder sb = new StringBuilder();
        for (String s: set) {
            sb.append(s).append("-");
        }
        return sb.toString();
    }

    private void initializeWidget(){
        mSchool1 = findViewById(R.id.edit_school1);
        mSchool2 = findViewById(R.id.edit_school2);
        mSchool3 = findViewById(R.id.edit_school3);
        mSchool4 = findViewById(R.id.edit_school4);
        mSchool5 = findViewById(R.id.edit_school5);
        mSchool6 = findViewById(R.id.edit_school6);
        mSchool7 = findViewById(R.id.edit_school7);
        mSchool8 = findViewById(R.id.edit_school8);

        mSave = findViewById(R.id.btn_save);
        mNext = findViewById(R.id.btn_next);
    }
    private void openActivity(Class activityClass){
        Intent intent = new Intent(this, activityClass);
        startActivity(intent);
    }

    private void insertData(String data){
        FileOutputStream stream = null;
        try {
            String filename = "data.txt";
            stream = openFileOutput(filename, Context.MODE_PRIVATE);
            stream.write(data.getBytes());
        } catch (FileNotFoundException e) {
            Log.d("error", "File not found");
        } catch (IOException e) {
            Log.d("error", "IO error");
        }finally {
            if(stream != null){
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
