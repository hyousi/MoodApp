package com.example.moodapp.legacy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.moodapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class DIARY_Save extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private Button mCreateButton;
    private Button mReadButton;
    private Button mUpdateButton;
    private Button mDeleteButton;

    private EditText mEmail;
    private EditText mText;
    private EditText mEmotion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diary_save);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        final SimpleDateFormat date_long = new SimpleDateFormat("yyyy_MM_dd_mm_ss", Locale.US);


        // CREATE
        mCreateButton = findViewById(R.id.create_button);
        mCreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date now = new Date();
                String date = date_long.format(now);

                mEmail = findViewById(R.id.email);
                mText = findViewById(R.id.text);
                mEmotion = findViewById(R.id.emotion);

                String email = mEmail.getText().toString().trim();
                String text = mText.getText().toString().trim();
                String emotion= mEmotion.getText().toString().trim();

                HashMap<String,String> dataMap = new HashMap<String, String>();
                dataMap.put("Email", email);
                dataMap.put("Date", date);
                dataMap.put("Text", text);
                dataMap.put("Emotion", emotion);

                mDatabase.push().setValue(dataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"Data Created",Toast.LENGTH_SHORT).show();
                        } else{
                            Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });


        //READ
        mReadButton = findViewById(R.id.read_button);
        mReadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        List<String> keys = new ArrayList<String>();
                        if (dataSnapshot.exists()){
                            int i = 0;
                            for(DataSnapshot d : dataSnapshot.getChildren()) {
                                keys.add(d.getKey());
                                i++;
                            }
                        }
//                        System.out.println(keys);
//                        System.out.println(keys.get(1));
//                        System.out.println(keys.size());

                        String print1 = dataSnapshot.child(keys.get(1)).child("Date").getValue().toString();
                        String print2 = dataSnapshot.child(keys.get(1)).child("Email").getValue().toString();
                        String print3 = dataSnapshot.child(keys.get(1)).child("Emotion").getValue().toString();
                        String print4 = dataSnapshot.child(keys.get(1)).child("Text").getValue().toString();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        //UPDATE
        mUpdateButton = findViewById(R.id.update_button);
        mUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        mEmail = findViewById(R.id.email);
                        mText = findViewById(R.id.text);
                        mEmotion = findViewById(R.id.emotion);

                        String email = mEmail.getText().toString().trim();
                        String text = mText.getText().toString().trim();
                        String emotion= mEmotion.getText().toString().trim();


                        List<String> keys = new ArrayList<String>();
                        if (dataSnapshot.exists()){
                            int i = 0;
                            for(DataSnapshot d : dataSnapshot.getChildren()) {
                                keys.add(d.getKey());
                                i++;
                            }
                        }

                        //REMOVE THIS LATER
                        String temp_varibale = "2019_11_29_12_27" ;

                        for(int i=0; i<keys.size(); i++){
                            String fb_date = dataSnapshot.child(keys.get(i)).child("Date").getValue().toString();
                            String fb_email = dataSnapshot.child(keys.get(i)).child("Email").getValue().toString();
                            String fb_emo = dataSnapshot.child(keys.get(i)).child("Emotion").getValue().toString();
                            String fb_text = dataSnapshot.child(keys.get(i)).child("Text").getValue().toString();

                            if(fb_email.equals(email) && fb_date.equals(temp_varibale)){
                                DatabaseReference updater = mDatabase.child(keys.get(i));
                                Map<String, Object> updates = new HashMap<>();
                                updates.put("Text", text);
                                updates.put("Emotion", emotion);

                                updater.updateChildren(updates);
                            }
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });


        //DELETE
        mDeleteButton = findViewById(R.id.delete_button);
        mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        mEmail = findViewById(R.id.email);
                        String email = mEmail.getText().toString().trim();

                        List<String> keys = new ArrayList<String>();
                        if (dataSnapshot.exists()){
                            int i = 0;
                            for(DataSnapshot d : dataSnapshot.getChildren()) {
                                keys.add(d.getKey());
                                i++;
                            }
                        }

                        //REMOVE THIS LATER
                        String temp_varibale = "2019_11_29_12_27" ;

                        for(int i=0; i<keys.size(); i++){
                            String fb_date = dataSnapshot.child(keys.get(i)).child("Date").getValue().toString();
                            String fb_email = dataSnapshot.child(keys.get(i)).child("Email").getValue().toString();
                            String fb_emo = dataSnapshot.child(keys.get(i)).child("Emotion").getValue().toString();
                            String fb_text = dataSnapshot.child(keys.get(i)).child("Text").getValue().toString();
                            if(fb_email.equals(email) && fb_date.equals(temp_varibale)){
                                DatabaseReference deleter = mDatabase.child(keys.get(i));
                                deleter.removeValue();

                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });


    }
}
