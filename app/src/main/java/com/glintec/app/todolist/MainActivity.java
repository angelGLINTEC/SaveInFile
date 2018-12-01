package com.glintec.app.todolist;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    private EditText edTitle, edTarea;
    Button btnGuardar, btnCargar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edTarea = findViewById(R.id.edTxtMulti_LISTA);
        edTitle = findViewById(R.id.edText_TITULO);
        btnGuardar = findViewById(R.id.btn_NOTA);
        btnCargar = findViewById(R.id.btn_LoadNOTA);

//        String[] archivos = fileList();
//
//        if(existeFichero(archivos,"notas.txt")){
//            try {
//                InputStreamReader isr = new InputStreamReader(openFileInput("notas.txt"));
//                BufferedReader lector = new BufferedReader(isr);
//                String linea = null;
//                try {
//                    linea = lector.readLine();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                String todoelTexto = "";
//                while(linea != null){
//                    todoelTexto = todoelTexto + linea;
//                    try {
//                        linea = lector.readLine();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//                lector.close();
//                isr.close();
//                edTarea.setText(todoelTexto);
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }

        View.OnClickListener cl = new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                guardarNota();
            }
        };

        View.OnClickListener clLoad = new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                cargarNota();
            }
        };

        btnGuardar.setOnClickListener(cl);
        btnCargar.setOnClickListener(clLoad);



    }

    private  boolean existeFichero(String[] archivos, String FicheroABuscar){
        for (int i=0; i< archivos.length; i++){
            if(archivos[i].equals(FicheroABuscar))
                return true;
        }
        return false;
    }

    private void  guardarNota(){
        String nomFichero = edTitle.getText().toString() + ".txt";

        try {
            OutputStreamWriter osw = new OutputStreamWriter(openFileOutput(nomFichero, Activity.MODE_PRIVATE));
            osw.write(edTarea.getText().toString());
            osw.flush();
            osw.close();
            Toast.makeText(this, "Los datos se han guardado correctamente", Toast.LENGTH_LONG).show();
//            finish();
        }catch(IOException e){
            e.printStackTrace();
        }

    }

    private void cargarNota(){
        String[] archivos = fileList();
        String nomFichero = edTitle.getText().toString() + ".txt";

        if(existeFichero(archivos,nomFichero)){
            try {
                InputStreamReader isr = new InputStreamReader(openFileInput(nomFichero));
                BufferedReader lector = new BufferedReader(isr);
                String linea = null;
                try {
                    linea = lector.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String todoelTexto = "";
                while(linea != null){
                    todoelTexto = todoelTexto + linea;
                    try {
                        linea = lector.readLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                lector.close();
                isr.close();
                edTarea.setText(todoelTexto);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
