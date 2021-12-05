package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavHostController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.navigation.NavigationView;


public class MenuActivity4 extends AppCompatActivity {

    //All the code goes here
    DrawerLayout drawerLayout;
    ImageView icon;
    NavigationView navigationView;
    NavController navController;
    NavHostFragment navHostFragment;

    //Comunicacion
    private RecyclerView recyclerView;
    private RecyclerViewAdaptor recyclerViewAdaptor;


    private Button local;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu4);
        init();
        events();
        setup();

        local=(Button) findViewById(R.id.btnlocal);
        local.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MenuActivity4.this,MapsActivity.class);
                startActivity(i);
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recyclerComida);
        //recyclerViewAdaptor

    }

    private void setup() {

        navController = navHostFragment.getNavController();
        NavigationUI.setupWithNavController(navigationView,navController);
    }

    @SuppressLint("WrongConstant")
    private void events() {
        icon.setOnClickListener(v->{
            drawerLayout.openDrawer(Gravity.START);
        });
    }

    private void init(){

        drawerLayout = findViewById(R.id.drawer_layout);
        icon = findViewById(R.id.icon_toolbar);
        navigationView = findViewById(R.id.navigation_view);
        navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);

    }
}