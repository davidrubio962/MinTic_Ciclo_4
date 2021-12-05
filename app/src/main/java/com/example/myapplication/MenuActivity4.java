package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavHostController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.navigation.NavigationView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


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
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerViewAdaptor = new RecyclerViewAdaptor(obtenerComida());
        recyclerView.setAdapter(recyclerViewAdaptor);


    }

    public Connection conexionBD(){
        Connection conexion = null;
        try{
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
            conexion = DriverManager.getConnection(
                    "jdbc:jtds:sqlserver://192.168.56.1;databaseName=AndroidStudio;user=sa;password=123;");

        }catch (Exception e){
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
        return conexion;
    }
    public List<Comida> obtenerComidaBD(){
        List<Comida> comida = new ArrayList<>();
        try {
            Statement st = conexionBD().createStatement();
            ResultSet rs = st.executeQuery("select * from tipo_de_comida");
            while(rs.next()){
                comida.add(new Comida(
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        R.drawable.atencion_al_cliente));
            }
        }catch(SQLException e){
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
        return comida;
    }


    public List<Comida> obtenerComida(){
        List<Comida> comida = new ArrayList<>();
        comida.add(new Comida("Pizza", "Pizza Hunt",R.drawable.atencion_al_cliente));
        comida.add(new Comida("Hamburguesa", "El corral",R.drawable.atencion_al_cliente));
        return comida;
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