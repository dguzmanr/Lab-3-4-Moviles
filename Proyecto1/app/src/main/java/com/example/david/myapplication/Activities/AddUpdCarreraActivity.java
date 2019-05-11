package com.example.david.myapplication.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.david.myapplication.LogicaNegocio.Carrera;
import com.example.david.myapplication.LogicaNegocio.Profesor;
import com.example.david.myapplication.R;

public class AddUpdCarreraActivity extends AppCompatActivity {

    private FloatingActionButton fBtn;
    private boolean editable = true;
    private EditText codFld;
    private EditText nomFld;
    private EditText tituloFld;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_upd_carrera);

        editable = true;

        // button check
        fBtn = findViewById(R.id.addUpdCarreraBtn);

        //cleaning stuff
        codFld = findViewById(R.id.codigoAddUpdCarrera);
        nomFld = findViewById(R.id.nombreAddUpdCarrera);
        tituloFld = findViewById(R.id.tituloAddUpdCarrera);
        codFld.setText("");
        nomFld.setText("");
        tituloFld.setText("");

        //receiving data from admCarreraActivity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            editable = extras.getBoolean("editable");
            if (editable) {   // is editing some row
                Carrera aux = (Carrera) getIntent().getSerializableExtra("carrera");
                codFld.setText(aux.getCodigo());
                codFld.setEnabled(false);
                nomFld.setText(aux.getNombre());
                tituloFld.setText(aux.getTitulo());
                //edit action
                fBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        editCarrera();
                    }
                });
            } else {         // is adding new Carrera object
                //add new action
                fBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        addCarrera();
                    }
                });
            }
        }
    }

    public void addCarrera() {
        if (validateForm()) {
            //do something
            Carrera car = new Carrera(codFld.getText().toString(), nomFld.getText().toString(), tituloFld.getText().toString());
            Intent intent = new Intent(getBaseContext(), AdmCarreraActivity.class);
            //sending carrera data
            intent.putExtra("addCarrera", car);
            startActivity(intent);
            finish(); //prevent go back
        }
    }

    public void editCarrera() {
        if (validateForm()) {
            Carrera car = new Carrera(codFld.getText().toString(), nomFld.getText().toString(), tituloFld.getText().toString());
            Intent intent = new Intent(getBaseContext(), AdmCarreraActivity.class);
            //sending carrera data
            intent.putExtra("editCarrera", car);
            startActivity(intent);
            finish(); //prevent go back
        }
    }

    public boolean validateForm() {
        int error = 0;
        if (TextUtils.isEmpty(this.nomFld.getText())) {
            nomFld.setError("Nombre requerido");
            error++;
        }
        if (TextUtils.isEmpty(this.codFld.getText())) {
            codFld.setError("Codigo requerido");
            error++;
        }
        if (TextUtils.isEmpty(this.tituloFld.getText())) {
            tituloFld.setError("Titulo requerido");
            error++;
        }
        if (error > 0) {
            Toast.makeText(getApplicationContext(), "Algunos errores", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}
