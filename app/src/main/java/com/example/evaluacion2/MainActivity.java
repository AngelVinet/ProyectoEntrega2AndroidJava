package com.example.evaluacion2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    //Variables generales
    private com.google.android.material.textfield.TextInputEditText edNombre;
    private EditText naFecha;
    private com.google.android.material.textfield.TextInputEditText edTelefono;
    private com.google.android.material.textfield.TextInputEditText edEmail;
    private com.google.android.material.textfield.TextInputEditText edDescripcion;

    String nombre;
    String fecha;
    String telefono;
    String email;
    String descripcion;

    private int ultimoAnio, ultimoMes, ultimoDiaDelMes;
    final Calendar calendario = Calendar.getInstance();
    int anio = calendario.get(Calendar.YEAR);
    int mes = calendario.get(Calendar.MONTH);
    int diaDelMes = calendario.get(Calendar.DAY_OF_MONTH);

    //Función que obtiene la fecha.
    private DatePickerDialog.OnDateSetListener listenerDeDatePicker = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int anio, int mes, int diaDelMes) {
            ultimoAnio = anio;
            ultimoMes = mes;
            ultimoDiaDelMes = diaDelMes;

            refrescarFecha();
        }
    };

    //Función que refresca la fecha actual en el editText correspondiente.
    public void refrescarFecha(){
        String fecha = String.format(Locale.getDefault(), "%02d-%02d-%02d", ultimoAnio, ultimoMes+1, ultimoDiaDelMes);

        // La ponemos en el editText
        naFecha.setText(fecha);
    }

    //Función que se ejecuta una vez se inicia el layout.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Se inicializa el componente editText para mostrar la fecha.
        naFecha = findViewById(R.id.nacimientoFecha);
        //Se asigna a las variables el año, mes y dia actual
        final Calendar calendario = Calendar.getInstance();
        ultimoAnio = calendario.get(Calendar.YEAR);
        ultimoMes = calendario.get(Calendar.MONTH);
        ultimoDiaDelMes = calendario.get(Calendar.DAY_OF_MONTH);

        //Función que al presionar click en el editText naFecha muestra la fecha actual por pantalla.
        naFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dialogoFecha = new DatePickerDialog(MainActivity.this, listenerDeDatePicker, ultimoAnio, ultimoMes, ultimoDiaDelMes);
                dialogoFecha.show();
            }
        });

        //Se inicializa los componentes del layout.
        edNombre = (com.google.android.material.textfield.TextInputEditText) findViewById(R.id.edNombre);
        edTelefono = (com.google.android.material.textfield.TextInputEditText) findViewById(R.id.edTelefono);
        edEmail = (com.google.android.material.textfield.TextInputEditText) findViewById(R.id.edEmail);
        edDescripcion = (com.google.android.material.textfield.TextInputEditText) findViewById(R.id.edDescripcion);

        //Se recibe la información del layout mostrar_datos y se asigna a las vaariables correspondientes.
        nombre = getIntent().getStringExtra("nombre");
        fecha = getIntent().getStringExtra("fecha");
        telefono = getIntent().getStringExtra("telefono");
        email = getIntent().getStringExtra("email");
        descripcion = getIntent().getStringExtra("descripcion");

        //Se muestra por pantalla la información obtenida.
        edNombre.setText(nombre);
        naFecha.setText(fecha);
        edTelefono.setText(telefono);
        edEmail.setText(email);
        edDescripcion.setText(descripcion);
    }

    //Función que envía la información ingresada hacia el activity mostrar_datos y abre dicho layout.
    public void siguiente(View view){
        Intent intent = new Intent(this, mostrar_datos.class);
        intent.putExtra("nombre", edNombre.getText().toString() );
        intent.putExtra("fecha", naFecha.getText().toString());
        intent.putExtra("telefono", edTelefono.getText().toString());
        intent.putExtra("email", edEmail.getText().toString());
        intent.putExtra("descripcion", edDescripcion.getText().toString());
        finish();
        startActivity(intent);
    }
}

