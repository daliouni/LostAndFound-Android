package com.example.projetandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.view.MenuItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.projetandroid.R;
import com.example.projetandroid.fragment.homefragment;
import com.example.projetandroid.fragment.profilfragment;
import com.example.projetandroid.fragment.notificationfragment;


import androidx.annotation.NonNull;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        //Mengganti Judul Pada Action Bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("lost and found");
        }

        //Memunculkan fragment di awal
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new homefragment()).addToBackStack(null)
                .commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    switch (item.getItemId()) {
                        case R.id.nav_home:
                            selectedFragment = new homefragment();
                            break;
                        case R.id.nav_profil:
                            selectedFragment = new profilfragment();
                            break;
                        case R.id.nav_notification:
                            selectedFragment = new notificationfragment();
                            break;
                    }

                        getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container, selectedFragment)
                            .commit();

                    return true;
                }
            };
}

