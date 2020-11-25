package org.izv.agenda.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.izv.agenda.R;
import org.izv.agenda.model.entity.Persona;
import org.izv.agenda.viewmodel.PersonaViewModel;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button listaPersonasButton,nuevaPersonaButton,actualizarPersonaButton,borrarPersonaButton;
    private PersonaViewModel viewModel;

    private static String ACTION = "Funcion";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = new ViewModelProvider(this).get(PersonaViewModel.class);

        Intent intent = getIntent();
        if(intent.getStringExtra("Funcion")!=null){
            if(intent.getStringExtra("Funcion").equals("Insertar")){
                if(intent.getSerializableExtra("Persona")!=null){
                    Persona persona = (Persona) intent.getSerializableExtra("Persona");
                    viewModel.insert(persona);
                } else {
                    Toast.makeText(getApplicationContext(),"Alguno de los campos estaba vacío", Toast.LENGTH_LONG).show();
                }
            } else if(intent.getStringExtra("Funcion").equals("Borrar")){
                Thread thread = new Thread(){
                    @Override
                    public void run() {
                        Persona persona = (Persona) intent.getSerializableExtra("Persona");
                        viewModel.delete(persona);

                    }
                };
                thread.start();
                Toast.makeText(getApplicationContext(),"Contacto borrado", Toast.LENGTH_LONG).show();

            } else if(intent.getStringExtra("Funcion").equals("Editar")){
                Thread thread = new Thread(){
                    @Override
                    public void run() {
                        Persona persona = (Persona) intent.getSerializableExtra("Persona");
                        viewModel.update(persona);

                    }
                };
                thread.start();
                Toast.makeText(getApplicationContext(),"Contacto actualizado", Toast.LENGTH_LONG).show();
            }

        }

        init();
    }

    public void init(){
        listaPersonasButton = findViewById(R.id.lista_btn);
        nuevaPersonaButton = findViewById(R.id.nuevo_btn);
        actualizarPersonaButton = findViewById(R.id.actualizar_btn);
        borrarPersonaButton = findViewById(R.id.borrar_btn);

        listaPersonasButton.setOnClickListener(this);
        nuevaPersonaButton.setOnClickListener(this);
        actualizarPersonaButton.setOnClickListener(this);
        borrarPersonaButton.setOnClickListener(this);

    }

    public void mostrarLista(){
        Intent intent = new Intent(this, ListActivity.class);
        intent.putExtra(ACTION,"Listar");
        startActivity(intent);
    }

    public void nuevaPersona(){
        Intent intent = new Intent(this, NewActivity.class);
        startActivity(intent);
    }

    public void actualizarPersona(){
        Intent intent = new Intent(this, ListActivity.class);
        intent.putExtra(ACTION,"Editar");
        startActivity(intent);
    }

    public void borrarPersona(){
        Intent intent = new Intent(this, ListActivity.class);
        intent.putExtra(ACTION,"Borrar");
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lista_btn:
                mostrarLista();
                break;
            case R.id.nuevo_btn:
                nuevaPersona();
                break;
            case R.id.actualizar_btn:
                actualizarPersona();
                break;
            case R.id.borrar_btn:
                borrarPersona();
                break;
            default:
                break;
        }
    }
}