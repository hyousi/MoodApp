package com.example.moodapp;

import android.os.Bundle;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        // FIXME: should launch the app bar and drawer after login.
//        drawerLayout = findViewById(R.id.drawer_layout);


//        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout);


    }

//    @Override
//    public boolean onSupportNavigateUp() {
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//        return NavigationUI.navigateUp(navController, drawerLayout);
//    }
}
