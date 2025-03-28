package com.example.calculadora;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.CompoundButton;

public class MainActivity extends AppCompatActivity {


    private EditText num1, num2, resultado;
    private CheckBox cbSuma, cbResta, cbMultiplicar, cbDividir;
    private Switch switchCheckBox, switchImagen;
    private ImageView imagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Referencias a los elementos
        num1 = findViewById(R.id.editTextNumberDecimal);
        num2 = findViewById(R.id.editTextNumberDecimal3);
        resultado = findViewById(R.id.editTextNumberDecimal2);

        cbSuma = findViewById(R.id.checkBox);
        cbResta = findViewById(R.id.checkBox2);
        cbMultiplicar = findViewById(R.id.checkBox3);
        cbDividir = findViewById(R.id.checkBox4);

        switchCheckBox = findViewById(R.id.switch2);
        switchImagen = findViewById(R.id.switch1);
        imagen = findViewById(R.id.imageView3);

        // Listener único para los CheckBox
        CompoundButton.OnCheckedChangeListener listener = (buttonView, isChecked) -> {
            if (isChecked) {
                desmarcarOtros((CheckBox) buttonView);
                calcular((CheckBox) buttonView);
            } else {
                resultado.setText(""); // Si se desmarca, limpiar resultado
            }
        };

        // Asignar listener a cada CheckBox
        cbSuma.setOnCheckedChangeListener(listener);
        cbResta.setOnCheckedChangeListener(listener);
        cbMultiplicar.setOnCheckedChangeListener(listener);
        cbDividir.setOnCheckedChangeListener(listener);

        // **Switch para habilitar/deshabilitar los CheckBox**
        switchCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            setCheckBoxEnabled(isChecked);

            // Si lo desactivamos, desmarcamos todos los CheckBox y limpiamos el resultado
            if (!isChecked) {
                cbSuma.setChecked(false);
                cbResta.setChecked(false);
                cbMultiplicar.setChecked(false);
                cbDividir.setChecked(false);
                resultado.setText("");
            }
        });

        // **Switch para ocultar/mostrar la imagen**
        switchImagen.setChecked(true); // Iniciar en "visible"
        switchImagen.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                imagen.setVisibility(View.VISIBLE);
            } else {
                imagen.setVisibility(View.GONE);
            }
        });
    }

    // **Método para habilitar o deshabilitar los CheckBox**
    private void setCheckBoxEnabled(boolean enabled) {
        cbSuma.setEnabled(enabled);
        cbResta.setEnabled(enabled);
        cbMultiplicar.setEnabled(enabled);
        cbDividir.setEnabled(enabled);
    }

    // **Desmarcar todos los CheckBox excepto el seleccionado**
    private void desmarcarOtros(CheckBox seleccionado) {
        if (seleccionado != cbSuma) cbSuma.setChecked(false);
        if (seleccionado != cbResta) cbResta.setChecked(false);
        if (seleccionado != cbMultiplicar) cbMultiplicar.setChecked(false);
        if (seleccionado != cbDividir) cbDividir.setChecked(false);
    }

    // **Realizar la operación y actualizar el resultado**
    private void calcular(CheckBox seleccionado) {
        String textoNum1 = num1.getText().toString();
        String textoNum2 = num2.getText().toString();

        if (textoNum1.isEmpty() || textoNum2.isEmpty()) {
            resultado.setText(""); // Si falta un número, borrar resultado
            return;
        }

        try {
            double n1 = Double.parseDouble(textoNum1);
            double n2 = Double.parseDouble(textoNum2);
            double res = 0.0;

            if (seleccionado == cbSuma) {
                res = n1 + n2;
            } else if (seleccionado == cbResta) {
                res = n1 - n2;
            } else if (seleccionado == cbMultiplicar) {
                res = n1 * n2;
            } else if (seleccionado == cbDividir) {
                if (n2 != 0) {
                    res = n1 / n2;
                } else {
                    Toast.makeText(this, "No se puede dividir por cero", Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            resultado.setText(String.valueOf(res));

        } catch (NumberFormatException e) {
            resultado.setText("Error");
        }
    }
}
