package com.example.ejercicio3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText editTextCantidad;
    RadioButton radioDolares, radioSoles;
    Button btnConvertir;
    TextView txtResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextCantidad = findViewById(R.id.editTextCantidad);
        radioDolares = findViewById(R.id.radioDolares);
        radioSoles = findViewById(R.id.radioSoles);
        btnConvertir = findViewById(R.id.btnConvertir);
        txtResultado = findViewById(R.id.txtResultado);

        btnConvertir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertir();
            }
        });
    }

    private void convertir() {
        String texto = editTextCantidad.getText().toString().trim();

        if (texto.isEmpty()) {
            Toast.makeText(this, "Ingrese una cantidad", Toast.LENGTH_SHORT).show();
            return;
        }

        double cantidad;
        try {
            cantidad = Double.parseDouble(texto);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Ingrese un número válido", Toast.LENGTH_SHORT).show();
            return;
        }

        if (cantidad <= 0) {
            Toast.makeText(this, "Ingrese una cantidad mayor a 0", Toast.LENGTH_SHORT).show();
            return;
        }

        double tipoCambio = 3.65;
        double resultado;

        if (radioDolares.isChecked()) {
            resultado = cantidad / tipoCambio;
            txtResultado.setText(
                    String.format("Resultado: %.2f soles = %.2f dólares", cantidad, resultado)
            );

            radioDolares.setChecked(false);
            radioSoles.setChecked(true);

        } else if (radioSoles.isChecked()) {
            resultado = cantidad * tipoCambio;
            txtResultado.setText(
                    String.format("Resultado: %.2f dólares = %.2f soles", cantidad, resultado)
            );

            radioSoles.setChecked(false);
            radioDolares.setChecked(true);

        } else {
            Toast.makeText(this, "Seleccione una opción", Toast.LENGTH_SHORT).show();
        }
    }
}