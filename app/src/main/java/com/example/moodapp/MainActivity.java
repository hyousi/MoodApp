package com.example.moodapp;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        AppBarConfiguration appBarConfiguration =
                new AppBarConfiguration.Builder(R.id.homeFragment)
                        .setDrawerLayout(drawerLayout)
                        .build();
        final Toolbar toolbar = findViewById(R.id.toolbar);
        NavigationUI.setupWithNavController(toolbar, navController, appBarConfiguration);

        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller,
                                             @NonNull NavDestination destination, @Nullable Bundle arguments) {
                switch (destination.getId()) {
                    case R.id.loadingFragment:
                    case R.id.loginFragment:
                    case R.id.registrationFragment:
                        toolbar.setVisibility(View.GONE);
                        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                        break;
                    default:
                        toolbar.setVisibility(View.VISIBLE);
                        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                        break;
                }
            }
        });
    }

}
