package com.example.david.myapplication.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.david.myapplication.LogicaNegocio.Alumno;
import com.example.david.myapplication.LogicaNegocio.Profesor;
import com.example.david.myapplication.R;

public class AddUpdAlumnoActivity extends AppCompatActivity {
    private FloatingActionButton fBtn;
    private boolean editable = true;
    private EditText nomFld;
    private EditText cedFld;
    private EditText emailFld;
    private EditText telFld;
    private EditText fechaFld;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_upd_alumno);
        editable = true;

        // button check
        fBtn = findViewById(R.id.addUpdAlumoBtn);

        //cleaning stuff
        nomFld = findViewById(R.id.nombreAddUpdAlumno);
        cedFld = findViewById(R.id.cedulaAddUpdAlumno);
        emailFld = findViewById(R.id.emailAddUpdAlumno);
        telFld=findViewById(R.id.telefonoAddUpdAlumno);
        fechaFld=findViewById(R.id.fechaNacAddUpdAlumno);
        nomFld.setText("");
        cedFld.setText("");
        emailFld.setText("");
        telFld.setText("");
        fechaFld.setText("");

        //receiving data from admAlumnoActivity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            editable = extras.getBoolean("editable");
            if (editable) {   // is editing some row
                Alumno aux = (Alumno) getIntent().getSerializableExtra("alumno");
                cedFld.setText(aux.getCedula());
                cedFld.setEnabled(false);
                nomFld.setText(aux.getNombre());
                emailFld.setText(aux.getEmail());
                telFld.setText(Integer.toString(aux.getTelefono()));
                fechaFld.setText(aux.getFechaNacimiento());
                //edit action
                fBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        editAlumno();
                    }
                });
            } else {         // is adding new Carrera object
                //add new action
                fBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        addAlumno();
                    }
                });
            }
        }
    }

    public void addAlumno() {
        if (validateForm()) {
            //do something
            Alumno prof = new Alumno(cedFld.getText().toString(), nomFld.getText().toString(),
                    Integer.parseInt(telFld.getText().toString()),
                    emailFld.getText().toString(),
                    fechaFld.getText().toString());
            Intent intent = new Intent(getBaseContext(), AdmAlumnoActivity.class);
            //sending Alumno data
            intent.putExtra("addAlumno", prof);
            startActivity(intent);
            finish(); //prevent go back
        }
    }

    public void editAlumno() {
        if (validateForm()) {
            Alumno prof = new Alumno(cedFld.getText().toString(), nomFld.getText().toString(),
                    Integer.parseInt(telFld.getText().toString()),
                    emailFld.getText().toString(),
                    fechaFld.getText().toString());
            Intent intent = new Intent(getBaseContext(), AdmAlumnoActivity.class);
            //sending Alumno data
            intent.putExtra("editAlumno", prof);
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
        if (TextUtils.isEmpty(this.cedFld.getText())) {
            cedFld.setError("Cedula requerida");
            error++;
        }
        if (TextUtils.isEmpty(this.emailFld.getText())) {
            emailFld.setError("Email requerido");
            error++;
        }
        if (TextUtils.isEmpty(this.telFld.getText())) {
            telFld.setError("Telefono requerido");
            error++;
        }
        if (TextUtils.isEmpty(this.fechaFld.getText())) {
            fechaFld.setError("Fecha requerido");
            error++;
        }
        if (error > 0) {
            Toast.makeText(getApplicationContext(), "Algunos errores", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}
