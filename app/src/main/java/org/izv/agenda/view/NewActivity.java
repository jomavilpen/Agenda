package org.izv.agenda.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.izv.agenda.R;
import org.izv.agenda.model.entity.Persona;

public class NewActivity extends AppCompatActivity {

    private TextView cabecera;
    private EditText nombre,apellidos,telefono,fnacimiento,localidad,calle,numero;
    private String nombret,apellidost,telefonot,fnacimientot,localidadt,callet,numerot;
    private Button enviar;
    private Persona editPersona;

    private static String ACTION = "Funcion";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        init();
    }

    public void init(){
        cabecera = findViewById(R.id.tv_cabecera);
        nombre = findViewById(R.id.nombre_et);
        apellidos = findViewById(R.id.apellidos_et);
        telefono = findViewById(R.id.telefono_et);
        fnacimiento = findViewById(R.id.fnacimiento_et);
        localidad = findViewById(R.id.localidad_et);
        calle = findViewById(R.id.calle_et);
        numero = findViewById(R.id.numero_et);
        enviar = findViewById(R.id.enviar_btn);

        Intent intent = getIntent();
        if(intent.getStringExtra(ACTION)!=null){
            cabecera.setText("EDITAR CONTACTO");
            editPersona = (Persona) intent.getSerializableExtra("Persona");
            nombre.setText(editPersona.getNombre());
            apellidos.setText(editPersona.getApellidos());
            telefono.setText(editPersona.getTelefono());
            fnacimiento.setText(editPersona.getFNacimiento());
            localidad.setText(editPersona.getLocalidad());
            calle.setText(editPersona.getCalle());
            numero.setText(editPersona.getNumero());
        }


        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actividad_main();
            }
        });
    }

    public void actividad_main(){
        Intent intent = new Intent(this,MainActivity.class);
        nombret = nombre.getText().toString();
        apellidost = apellidos.getText().toString();
        telefonot = telefono.getText().toString();
        fnacimientot = fnacimiento.getText().toString();
        localidadt = localidad.getText().toString();
        callet = calle.getText().toString();
        numerot = numero.getText().toString();
        if(nombret==null||apellidost==null||telefonot==null||fnacimientot==null||
        localidadt==null||callet==null||numerot==null){
            intent.putExtra(ACTION,"Insertar");
        } else{
            String prueba = intent.getStringExtra(ACTION);
            if(editPersona!=null){
                editPersona.setNombre(nombret);
                editPersona.setApellidos(apellidost);
                editPersona.setTelefono(telefonot);
                editPersona.setFNacimiento(fnacimientot);
                editPersona.setLocalidad(localidadt);
                editPersona.setCalle(callet);
                editPersona.setNumero(numerot);
                intent.putExtra(ACTION,"Editar");
                intent.putExtra("Persona",editPersona);
            } else {
                Persona persona = new Persona(nombret,apellidost,telefonot,fnacimientot,localidadt,callet,numerot);
                intent.putExtra(ACTION,"Insertar");
                intent.putExtra("Persona",persona);
            }
        }
        startActivity(intent);
    }
}