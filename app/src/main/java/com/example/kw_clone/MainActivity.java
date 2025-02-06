package com.example.kw_clone;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.example.kw_clone.fragments.Favorites;
import com.example.kw_clone.fragments.Home;
import com.example.kw_clone.fragments.Profile;
import com.example.kw_clone.fragments.Search;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.main_page_bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.nav_search);
        bottomNavigationView.setOnItemSelectedListener(NavListener);

        Fragment selectedFragment = new Search();
        getSupportFragmentManager().beginTransaction().replace(R.id.main_page_fragment_container, selectedFragment).commit();
    }

    private NavigationBarView.OnItemSelectedListener NavListener  = item ->
    {
        int itemId = item.getItemId();

        Fragment selectedFragment = null;

        if (itemId == R.id.nav_home)
        {
            selectedFragment = new Home();
        }
        else if (itemId == R.id.nav_search)
        {
            selectedFragment = new Search();
        }
        else if (itemId == R.id.nav_fav)
        {
            selectedFragment = new Favorites();
        }
        else
        {
            selectedFragment = new Profile();
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.main_page_fragment_container, selectedFragment).commit();

        return true;
    };
}