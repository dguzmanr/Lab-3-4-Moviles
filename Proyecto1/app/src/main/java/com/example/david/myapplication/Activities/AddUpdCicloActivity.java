package com.example.david.myapplication.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.david.myapplication.LogicaNegocio.Ciclo;
import com.example.david.myapplication.LogicaNegocio.Profesor;
import com.example.david.myapplication.R;

public class AddUpdCicloActivity extends AppCompatActivity {
    private FloatingActionButton fBtn;
    private boolean editable = true;
    private EditText codFld;
    private EditText anoFld;
    private EditText estFld;
    private EditText numFld;
    private EditText iniFld;
    private EditText finFld;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_upd_ciclo);
        editable = true;

        // button check
        fBtn = findViewById(R.id.addUpdCicloBtn);

        //cleaning stuff
        codFld = findViewById(R.id.codigoAddUpdCiclo);
        anoFld = findViewById(R.id.anoAddUpdProf);
        estFld = findViewById(R.id.estadoAddUpdCiclo);
        numFld = findViewById(R.id.numeroAddUpdCiclo);
        iniFld=findViewById(R.id.FechaIAddUpdCiclo);
        finFld=findViewById(R.id.FechaFAddUpdCiclo);
        codFld.setText("");
        anoFld.setText("");
        estFld.setText("");
        numFld.setText("");
        iniFld.setText("");
        finFld.setText("");

        //receiving data from admCicloActivity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            editable = extras.getBoolean("editable");
            if (editable) {   // is editing some row
                Ciclo aux = (Ciclo) getIntent().getSerializableExtra("ciclo");
                codFld.setText(aux.getCodigo());
                codFld.setEnabled(false);
                anoFld.setText(aux.getAno());
                estFld.setText(String.valueOf(aux.getEstado()));
                numFld.setText(String.valueOf(aux.getNumero()));
                iniFld.setText(aux.getFecha_inicio());
                finFld.setText(aux.getFecha_finalizacion());
                //edit action
                fBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        editCiclo();
                    }
                });
            } else {         // is adding new Carrera object
                //add new action
                fBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        addCiclo();
                    }
                });
            }
        }
    }

    public void addCiclo() {
        if (validateForm()) {
            //do something
            Ciclo ciclo = new Ciclo(codFld.getText().toString(), anoFld.getText().toString(),estFld.getText().charAt(0) ,numFld.getText().charAt(0),
                    iniFld.getText().toString(),finFld.getText().toString());
            Intent intent = new Intent(getBaseContext(), AdmCicloActivity.class);
            //sending Ciclo data
            intent.putExtra("addciclo", ciclo);
            startActivity(intent);
            finish(); //prevent go back
        }
    }

    public void editCiclo() {
        if (validateForm()) {
            Ciclo ciclo = new Ciclo(codFld.getText().toString(), anoFld.getText().toString(),estFld.getText().charAt(0) ,numFld.getText().charAt(0),
                    iniFld.getText().toString(),finFld.getText().toString());
            Intent intent = new Intent(getBaseContext(), AdmCicloActivity.class);
            //sending Ciclo data
            intent.putExtra("editciclo", ciclo);
            startActivity(intent);
            finish(); //prevent go back
        }
    }

    public boolean validateForm() {
        int error = 0;
        if (TextUtils.isEmpty(this.codFld.getText())) {
            codFld.setError("Codigo requerido");
            error++;
        }
        if (TextUtils.isEmpty(this.anoFld.getText())) {
            anoFld.setError("Año requerido");
            error++;
        }
        if (TextUtils.isEmpty(this.estFld.getText())) {
            numFld.setError("Estado requerido");
            error++;
        }
        if (TextUtils.isEmpty(this.numFld.getText())) {
            numFld.setError("Numero requerida");
            error++;
        }
        if (TextUtils.isEmpty(this.iniFld.getText())) {
            iniFld.setError("Fecha Inicio requerida");
            error++;
        }
        if (TextUtils.isEmpty(this.finFld.getText())) {
            finFld.setError("Fecha Final requerida");
            error++;
        }
        if (error > 0) {
            Toast.makeText(getApplicationContext(), "Algunos errores", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}
