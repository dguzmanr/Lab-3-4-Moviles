package com.example.david.myapplication.Activities;

import android.content.Intent;
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
import com.example.david.myapplication.LogicaNegocio.Grupo;
import com.example.david.myapplication.LogicaNegocio.Profesor;
import com.example.david.myapplication.R;

import java.util.List;

public class AddUpdGrupoActivity extends AppCompatActivity {
    private FloatingActionButton fBtn;
    private boolean editable = true;
    private EditText codFld;
    private Spinner cursos, profesores;
    private EditText horarioFld;

    private ModelData model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_upd_grupo);
        editable = true;

        // button check
        fBtn = findViewById(R.id.addUpdGrupoBtn);

        //cleaning stuff
        codFld = findViewById(R.id.codigoAddUpdGrupo);
        cursos = (Spinner) findViewById(R.id.sp_cursos);
        profesores = (Spinner) findViewById(R.id.sp_profesores);
        horarioFld = findViewById(R.id.horarioAddUpdGrupo);

        codFld.setText("");
        horarioFld.setText("");


        model = new ModelData();

        loadCursos();
        loadProfesores();

        //receiving data from admCursoActivity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            editable = extras.getBoolean("editable");
            if (editable) {   // is editing some row
                Grupo aux = (Grupo) getIntent().getSerializableExtra("grupo");
                codFld.setText(aux.getCodigo());
                codFld.setEnabled(false);
                horarioFld.setText(aux.getHorario());
                //edit action
                fBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        editGrupo();
                    }
                });
            } else {         // is adding new Carrera object
                //add new action
                fBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        addGrupo();
                    }
                });
            }
        }
    }

    public void addGrupo() {
        if (validateForm()) {
            //do something
            Curso cur = buscarCurso(model.getCursoList(), (Curso)cursos.getSelectedItem());
            Profesor prof = buscarProfesor(model.getProfesorList(),(Profesor)profesores.getSelectedItem());

            Grupo gru = new Grupo(codFld.getText().toString(),cur,prof,horarioFld.getText().toString());
            Intent intent = new Intent(getBaseContext(), AdmGrupoActivity.class);
            //sending curso data
            intent.putExtra("addGrupo", gru);
            startActivity(intent);
            finish(); //prevent go back
        }
    }

    public void editGrupo() {
        if (validateForm()) {
            Curso cur = buscarCurso(model.getCursoList(), (Curso)cursos.getSelectedItem());
            Profesor prof = buscarProfesor(model.getProfesorList(),(Profesor)profesores.getSelectedItem());

            Grupo gru = new Grupo(codFld.getText().toString(),cur,prof,horarioFld.getText().toString());
            Intent intent = new Intent(getBaseContext(), AdmGrupoActivity.class);
            //sending curso data
            intent.putExtra("editGrupo", gru);
            startActivity(intent);
            finish(); //prevent go back
        }
    }

    public Curso buscarCurso(List<Curso> cursoList, Curso curso){
        for (Curso c : cursoList) {
            if (c.getCodigo().equals(curso.getCodigo())) {
                return c;
            }
        }
        return null;
    }

    public Profesor buscarProfesor(List<Profesor> profesorList, Profesor profesor){
        for (Profesor p : profesorList) {
            if (p.getCedula().equals(profesor.getCedula())) {
                return p;
            }
        }
        return null;
    }


//    public Curso buscarCurso(List<Curso> cursoList, String cursoCodigo){
//        for (Curso curso : cursoList) {
//            if (curso.getCodigo().equals(cursoCodigo)) {
//                return curso;
//            }
//        }
//        return null;
//    }
//
//    public Profesor buscarProfesor(List<Profesor> profesorList, String profesorCedula){
//        for (Profesor profesor : profesorList) {
//            if (profesor.getCedula().equals(profesorCedula)) {
//                return profesor;
//            }
//        }
//        return null;
//    }


    public boolean validateForm() {
        int error = 0;
        if (TextUtils.isEmpty(this.codFld.getText())) {
            codFld.setError("Codigo requerido");
            error++;
        }
        if (TextUtils.isEmpty(this.horarioFld.getText())) {
            horarioFld.setError("Horario requerido");
            error++;
        }
        if (error > 0) {
            Toast.makeText(getApplicationContext(), "Algunos errores", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private void loadCursos() {
        // im not sure about this
        ArrayAdapter<Curso> adapter = new ArrayAdapter<Curso>(this, R.layout.support_simple_spinner_dropdown_item, model.getCursoList());
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        cursos.setAdapter(adapter);
    }

    private void loadProfesores() {
        // im not sure about this
        ArrayAdapter<Profesor> adapter = new ArrayAdapter<Profesor>(this, R.layout.support_simple_spinner_dropdown_item, model.getProfesorList());
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        profesores.setAdapter(adapter);
    }
}
