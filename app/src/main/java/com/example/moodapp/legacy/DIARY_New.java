package com.example.moodapp.legacy;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.MultiAutoCompleteTextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.moodapp.R;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DIARY_New extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diary_edit);

        MultiAutoCompleteTextView diaryText = findViewById(R.id.diaryText);
        diaryText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                System.out.println("test");
                //do save function
            }
        });
    }

//    public void changeFont(){
//        //complicated task
//
//    }
//
    public void changeSize(View view){
        MultiAutoCompleteTextView diaryText = findViewById(R.id.diaryText);
        diaryText.setTextSize(40); //TEST
    }

    public void saveDiary(View view){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.US);
        Date now = new Date();
        String fileName = formatter.format(now) + ".txt";

//        System.out.println(DIARY_New.this.getFilesDir().getAbsolutePath());
        fileName = "test.txt";  //TEST
        MultiAutoCompleteTextView diaryText = findViewById(R.id.diaryText);
        String text = diaryText.getText().toString();
        FileOutputStream fos = null;
        try {
            fos = openFileOutput(fileName, MODE_PRIVATE);
            fos.write(text.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}