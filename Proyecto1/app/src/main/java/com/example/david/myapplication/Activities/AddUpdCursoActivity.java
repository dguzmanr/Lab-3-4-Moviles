package com.example.david.myapplication.Activities;

import android.content.Intent;
import android.graphics.ColorSpace;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.david.myapplication.AccesoDatos.ModelData;
import com.example.david.myapplication.LogicaNegocio.Carrera;
import com.example.david.myapplication.LogicaNegocio.Ciclo;
import com.example.david.myapplication.LogicaNegocio.Curso;
import com.example.david.myapplication.LogicaNegocio.Profesor;
import com.example.david.myapplication.R;

import java.util.List;

public class AddUpdCursoActivity extends AppCompatActivity {
    private FloatingActionButton fBtn;
    private boolean editable = true;
    private EditText codFld;
    private Spinner carreras, ciclos;
    private EditText nomFld;
    private EditText creditosFld;
    private EditText horasFld;

    private ModelData model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_upd_curso);
        editable = true;

        // button check
        fBtn = findViewById(R.id.addUpdCursoBtn);

        //cleaning stuff
        codFld = findViewById(R.id.codigoAddUpdCurso);
        carreras = (Spinner) findViewById(R.id.sp_carreras);
        ciclos = (Spinner) findViewById(R.id.sp_ciclos);
        nomFld = findViewById(R.id.nombreAddUpdCurso);
        creditosFld = findViewById(R.id.creditosAddUpdCurso);
        horasFld = findViewById(R.id.horasAddUpdCiclo);
        codFld.setText("");
        nomFld.setText("");
        creditosFld.setText("");
        horasFld.setText("");

        model = new ModelData();

        loadCarreras();
        loadCiclos();

        //receiving data from admCursoActivity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            editable = extras.getBoolean("editable");
            if (editable) {   // is editing some row
                Curso aux = (Curso) getIntent().getSerializableExtra("curso");
                codFld.setText(aux.getCodigo());
                codFld.setEnabled(false);
                nomFld.setText(aux.getNombre());
                creditosFld.setText(Integer.toString(aux.getCreditos()));
                horasFld.setText(Integer.toString(aux.getHoras()));
                //edit action
                fBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        editCurso();
                    }
                });
            } else {         // is adding new Carrera object
                //add new action
                fBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        addCurso();
                    }
                });
            }
        }
    }

    public void addCurso() {
        if (validateForm()) {
            //do something
            Carrera carr = buscarCarrera(model.getCarreraList(), (Carrera)carreras.getSelectedItem());
            Ciclo cic = buscarCiclo(model.getCicloList(), ciclos.getSelectedItem().toString());

            Curso cur = new Curso(codFld.getText().toString(),cic,carr, nomFld.getText().toString(),
                    Integer.parseInt(creditosFld.getText().toString()),
                    Integer.parseInt(horasFld.getText().toString()));
            Intent intent = new Intent(getBaseContext(), AdmCursoActivity.class);
            //sending curso data
            intent.putExtra("addCurso", cur);
            startActivity(intent);
            finish(); //prevent go back
        }
    }

    public void editCurso() {
        if (validateForm()) {
            Carrera carr = buscarCarrera(model.getCarreraList(), (Carrera)carreras.getSelectedItem());
            Ciclo cic = buscarCiclo(model.getCicloList(), ciclos.getSelectedItem().toString());

            Curso cur = new Curso(codFld.getText().toString(),cic,carr, nomFld.getText().toString(),
                    Integer.parseInt(creditosFld.getText().toString()),
                    Integer.parseInt(horasFld.getText().toString()));
            Intent intent = new Intent(getBaseContext(), AdmCursoActivity.class);
            //sending curso data
            intent.putExtra("editCurso", cur);
            startActivity(intent);
            finish(); //prevent go back
        }
    }

    public Carrera buscarCarrera(List<Carrera> carreraList, Carrera carrera){
        for (Carrera c : carreraList) {
            if (c.getCodigo().equals(carrera.getCodigo())) {
                return c;
            }
        }
        return null;
    }

    public Ciclo buscarCiclo(List<Ciclo> cicloList, String cicloCodigo){
        for (Ciclo ciclo : cicloList) {
            if (ciclo.getCodigo().equals(cicloCodigo)) {
                return ciclo;
            }
        }
        return null;
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
        if (TextUtils.isEmpty(this.creditosFld.getText())) {
            creditosFld.setError("Creditos requerido");
            error++;
        }
        if (TextUtils.isEmpty(this.horasFld.getText())) {
            horasFld.setError("Horas requerido");
            error++;
        }
        if (error > 0) {
            Toast.makeText(getApplicationContext(), "Algunos errores", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private void loadCiclos() {
        // im not sure about this
        ArrayAdapter<Ciclo> adapter = new ArrayAdapter<Ciclo>(this, R.layout.support_simple_spinner_dropdown_item, model.getCicloList());
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        ciclos.setAdapter(adapter);
    }

    private void loadCarreras() {
        // im not sure about this
        ArrayAdapter<Carrera> adapter = new ArrayAdapter<Carrera>(this, R.layout.support_simple_spinner_dropdown_item, model.getCarreraList());
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        carreras.setAdapter(adapter);
    }

    private int getIndex(Spinner spinner, String cod) {
        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(cod)) {
                return i;
            }
        }

        return 0;
    }
}
