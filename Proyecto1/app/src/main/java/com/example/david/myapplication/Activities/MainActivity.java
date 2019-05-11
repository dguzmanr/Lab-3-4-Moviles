package com.example.david.myapplication.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.david.myapplication.R;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        whiteNotificationBar(navigationView);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void whiteNotificationBar(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int flags = view.getSystemUiVisibility();
            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            view.setSystemUiVisibility(flags);
            getWindow().setStatusBarColor(Color.rgb(1,5,16));
//            getWindow().setStatusBarColor(Color.rgb(28,118,178));
        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_salir) {
            // Handle the camera action
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            MainActivity.this.startActivity(intent);
            Toast.makeText(this, " Salió.", Toast.LENGTH_LONG).show();
        } else if (id == R.id.nav_profsores) {
            Toast.makeText(this, " Profesores.", Toast.LENGTH_LONG).show();
            abrirAdmProfesor();
        }else if (id == R.id.nav_ciclos) {
            Toast.makeText(this, " Ciclos.", Toast.LENGTH_LONG).show();
            abrirAdmCiclo();
        } else if (id == R.id.nav_carreras) {
            Toast.makeText(this, " Carreras.", Toast.LENGTH_LONG).show();
            abrirAdmCarrera();
        } else if (id == R.id.nav_cursos) {
        Toast.makeText(this, " Cursos.", Toast.LENGTH_LONG).show();
            abrirAdmCurso();
        }else if (id == R.id.nav_grupos) {
            Toast.makeText(this, " Grupos.", Toast.LENGTH_LONG).show();
            abrirAdmGrupo();
        }else if (id == R.id.nav_alumnos) {
            Toast.makeText(this, " Alumnos.", Toast.LENGTH_LONG).show();
            abrirAdmAlumno();
        }
        //else if (id == R.id.nav_slideshow) {
//
//        } else if (id == R.id.nav_manage) {
//
//        } else if (id == R.id.nav_share) {
//
//        }
//        } else if (id == R.id.nav_send) {
//
//        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void abrirAdmProfesor() {
        Intent intent = new Intent(this, AdmProfesorActivity.class);
        startActivity(intent);
    }

    public void abrirAdmCiclo() {
        Intent intent = new Intent(this, AdmCicloActivity.class);
        startActivity(intent);
    }
    public void abrirAdmCarrera() {
        Intent intent = new Intent(this, AdmCarreraActivity.class);
        startActivity(intent);
    }

    public void abrirAdmCurso() {
        Intent intent = new Intent(this, AdmCursoActivity.class);
        startActivity(intent);
    }

    public void abrirAdmGrupo() {
        Intent intent = new Intent(this, AdmGrupoActivity.class);
        startActivity(intent);
    }

    public void abrirAdmAlumno() {
        Intent intent = new Intent(this, AdmAlumnoActivity.class);
        startActivity(intent);
    }
}
