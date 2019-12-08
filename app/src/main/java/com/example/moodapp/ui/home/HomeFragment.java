package com.example.moodapp.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.moodapp.DIARY_Save;
import com.example.moodapp.LoginPage;
import com.example.moodapp.MainActivity;
import com.example.moodapp.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.timessquare.CalendarPickerView;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.Executor;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    private static final String TAG = "CalendarActivity";

    private CalendarView mCalendarView;

    private Button SignOut;


    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    private DatabaseReference mDatabase;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        SignOut = (Button)view.findViewById(R.id.btn_signOut);


        mCalendarView = view.findViewById(R.id.calendarView_mainPage);

        CalendarView cal = new CalendarView(getActivity());
        cal.setDate(System.currentTimeMillis(),false,true);

        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        calendar.setTimeZone(TimeZone.getTimeZone("Asia/Calcutta"));
        String currentDate = DateFormat.getDateInstance(DateFormat.LONG).format(calendar.getTime());
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        TextView textViewDay = view.findViewById(R.id.txt_home_day);

        switch (day) {
            case Calendar.SUNDAY:
                textViewDay.setText("Sunday");
                break;
            case Calendar.MONDAY:
                textViewDay.setText("Monday");
                break;
            case Calendar.TUESDAY:
                textViewDay.setText("Tuesday");
                break;
            case Calendar.WEDNESDAY:
                textViewDay.setText("Wednesday");
                break;
            case Calendar.THURSDAY:
                textViewDay.setText("Thursday");
                break;
            case Calendar.FRIDAY:
                textViewDay.setText("Friday");
                break;
            case Calendar.SATURDAY:
                textViewDay.setText("Saturday");
                break;
        }

        //============================================Firebase related stuff================================================

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(getActivity(), gso);

        mAuth = FirebaseAuth.getInstance();

        mDatabase = FirebaseDatabase.getInstance().getReference();
        //==================================================================================================================

        SignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOut();
            }
        });

        TextView textViewDate = view.findViewById(R.id.txt_home_date);
        textViewDate.setText(currentDate);



        return view;
    }


    private void signOut() {
        // Firebase sign out
        mAuth.signOut();
        // Google sign out
        mGoogleSignInClient.signOut().addOnCompleteListener( getActivity(),
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        updateUI(null);
                    }
                });
    }

    private void updateUI(FirebaseUser user) {
        if(user==null) {
            Intent intent = new Intent(getActivity(), LoginPage.class);
            startActivity(intent);
        }else{
        }

    }
}