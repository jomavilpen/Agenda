package org.izv.agenda.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.izv.agenda.R;
import org.izv.agenda.model.entity.Persona;
import org.izv.agenda.view.adapter.PersonaAdapter;
import org.izv.agenda.view.adapter.RecyclerViewAdapter;
import org.izv.agenda.viewmodel.PersonaViewModel;

import java.util.List;

public class ListActivity extends AppCompatActivity {

    private List<Persona> contactos;
    private PersonaViewModel viewModel;

    private static String ACTION = "Funcion";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        viewModel = new ViewModelProvider(this).get(PersonaViewModel.class);

        init();
    }


    private void init(){
        RecyclerView recicler = findViewById(R.id.mirecycler);
        final PersonaAdapter adaptador = new PersonaAdapter(new PersonaAdapter.PersonaDiff());
        adaptador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = adaptador.getCurrentList().get(recicler.getChildAdapterPosition(v)).getId();
                funcion(id);
            }
        });
        recicler.setAdapter(adaptador);
        recicler.setLayoutManager(new LinearLayoutManager(this));

        viewModel.getAllPersonas().observe(this, new Observer<List<Persona>>() {
            @Override
            public void onChanged(List<Persona> personas) {
                adaptador.submitList(personas);
            }
        });



    }

    public void funcion(int id){
        Intent intent = getIntent();
        if(intent.getStringExtra(ACTION).equals("Borrar")){
            MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(ListActivity.this);
            builder.setTitle("Borrar contacto");
            builder.setMessage("¿Estás seguro de que quieres borrar el contacto?");
            builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Thread thread = new Thread(){
                        @Override
                        public void run() {
                            Intent intent_destino;
                            intent_destino = new Intent(ListActivity.this,MainActivity.class);
                            intent_destino.putExtra(ACTION,"Borrar");
                            Persona persona = viewModel.get(id);
                            intent_destino.putExtra("Persona",persona);
                            startActivity(intent_destino);
                        }
                    };
                    thread.start();
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.show();

        } else if(intent.getStringExtra(ACTION).equals("Editar")){
            Thread thread = new Thread(){
                @Override
                public void run() {
                    Intent intent_destino;
                    intent_destino = new Intent(ListActivity.this,NewActivity.class);
                    intent_destino.putExtra(ACTION,"Editar");
                    Persona persona = viewModel.get(id);
                    intent_destino.putExtra("Persona",persona);
                    startActivity(intent_destino);

                }
            };
            thread.start();
        }
    }
}