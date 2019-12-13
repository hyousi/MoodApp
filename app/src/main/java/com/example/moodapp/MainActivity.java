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

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private BottomNavigationView bottomNav;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);
        bottomNav = findViewById(R.id.bottom_nav);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        AppBarConfiguration appBarConfiguration =
                new AppBarConfiguration.Builder(R.id.homeFragment, R.id.diaryListFragment)
                        .setDrawerLayout(drawerLayout)
                        .build();
        toolbar = findViewById(R.id.toolbar);

        // set up auto nav for app bar.
        NavigationUI.setupWithNavController(toolbar, navController, appBarConfiguration);
        // set up auto nav for bottom nav bar.
        NavigationUI.setupWithNavController(bottomNav, navController);

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
                        bottomNav.setVisibility(View.GONE);
                        break;
                    case R.id.homeFragment:
                    case R.id.diaryListFragment:
                        toolbar.setVisibility(View.VISIBLE);
                        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                        bottomNav.setVisibility(View.VISIBLE);
                        break;
                    default:
                        toolbar.setVisibility(View.VISIBLE);
                        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                        bottomNav.setVisibility(View.GONE);
                        break;
                }
            }
        });
    }

}
