package com.example.david.myapplication.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.david.myapplication.LogicaNegocio.Profesor;
import com.example.david.myapplication.R;

public class AddUpdProfesorActivity extends AppCompatActivity {
    private FloatingActionButton fBtn;
    private boolean editable = true;
    private EditText nomFld;
    private EditText cedFld;
    private EditText emailFld;
    private EditText telFld;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_upd_profesor);
        editable = true;

        // button check
        fBtn = findViewById(R.id.addUpdProfesorBtn);

        //cleaning stuff
        nomFld = findViewById(R.id.nombreAddUpdProf);
        cedFld = findViewById(R.id.cedulaAddUpdProf);
        emailFld = findViewById(R.id.emailAddUpdProf);
        telFld=findViewById(R.id.telefonoAddUpdProf);
        nomFld.setText("");
        cedFld.setText("");
        emailFld.setText("");
        telFld.setText("");

        //receiving data from admProfesorActivity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            editable = extras.getBoolean("editable");
            if (editable) {   // is editing some row
                Profesor aux = (Profesor) getIntent().getSerializableExtra("profesor");
                cedFld.setText(aux.getCedula());
                cedFld.setEnabled(false);
                nomFld.setText(aux.getNombre());
                emailFld.setText(aux.getEmail());
                telFld.setText(Integer.toString(aux.getTelefono()));
                //edit action
                fBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        editProfesor();
                    }
                });
            } else {         // is adding new Carrera object
                //add new action
                fBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        addProfesor();
                    }
                });
            }
        }
    }

    public void addProfesor() {
        if (validateForm()) {
            //do something
            Profesor prof = new Profesor(cedFld.getText().toString(), nomFld.getText().toString(),
                    emailFld.getText().toString(),
                    Integer.parseInt(telFld.getText().toString()));
            Intent intent = new Intent(getBaseContext(), AdmProfesorActivity.class);
            //sending Profesor data
            intent.putExtra("addProfesor", prof);
            startActivity(intent);
            finish(); //prevent go back
        }
    }

    public void editProfesor() {
        if (validateForm()) {
            Profesor prof = new Profesor(cedFld.getText().toString(), nomFld.getText().toString(),
                    emailFld.getText().toString(),
                    Integer.parseInt(telFld.getText().toString()));
            Intent intent = new Intent(getBaseContext(), AdmProfesorActivity.class);
            //sending Profesor data
            intent.putExtra("editProfesor", prof);
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
        if (error > 0) {
            Toast.makeText(getApplicationContext(), "Algunos errores", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}
