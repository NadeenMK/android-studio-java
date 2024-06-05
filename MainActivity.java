package com.example.myapplication_1;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText inputEditText;
    private TextView binaryTextView, octalTextView, decimalTextView, hexTextView;
    private String currentSystem = DECIMAL;
    private RadioButton binary,octal,decimal,hex;
    private static final String BINARY = "Binary";
    private static final String OCTAL = "Octal";
    private static final String DECIMAL = "Decimal";
    private static final String HEX = "Hexdecimal";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputEditText = findViewById(R.id.edit);
        binaryTextView = findViewById(R.id.lab3);
        octalTextView = findViewById(R.id.lab4);
        decimalTextView = findViewById(R.id.lab5);
        hexTextView = findViewById(R.id.lab6);
        binary = findViewById(R.id.binary);
        octal=findViewById(R.id.octal);
        decimal=findViewById(R.id.decimal);
        hex = findViewById(R.id.hexdecimal);
        binary.setOnClickListener(this);
        octal.setOnClickListener(this);
        decimal.setOnClickListener(this);
        hex.setOnClickListener(this);
        inputEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                updateConversions(currentSystem);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void updateConversions(String s) {
        String input = inputEditText.getText().toString().trim();
        if (input.isEmpty()) {
            clearConversionFields();
            return;
        }

        try {
            long value;
            switch (s) {
                case BINARY:
                    value = Long.parseLong(input, 2);
                    break;
                case OCTAL:
                    value = Long.parseLong(input, 8);
                    break;
                case DECIMAL:
                    value = Long.parseLong(input, 10);
                    break;
                case HEX:
                    value = Long.parseLong(input, 16);
                    break;
                default:
                    return;
            }

            binaryTextView.setText("Binary: " + Long.toBinaryString(value));
            octalTextView.setText("Octal: " + Long.toOctalString(value));
            decimalTextView.setText("Decimal: " + String.valueOf(value));
            hexTextView.setText("Hexadecimal: " + Long.toHexString(value).toUpperCase());
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid input for the selected base", Toast.LENGTH_SHORT).show();
            clearConversionFields();
        }
    }

    private void clearConversionFields() {
        binaryTextView.setText("Binary: ");
        octalTextView.setText("Octal: ");
        decimalTextView.setText("Decimal: ");
        hexTextView.setText("Hexadecimal: ");
    }

    @Override
    public void onClick(View v) {
        BottomNavigationView navigationView = findViewById(R.id.buttom1);
        RadioButton binaryRadioButton = (RadioButton) findViewById(R.id.binary);
        if(binaryRadioButton.isChecked()){
            currentSystem = binaryRadioButton.getText().toString();
            updateConversions(currentSystem);
            return;
        }
        RadioButton ocatlRadioButton = (RadioButton) findViewById(R.id.octal);
        if(ocatlRadioButton.isChecked()){
            currentSystem = ocatlRadioButton.getText().toString();
            updateConversions(currentSystem);
        }

        RadioButton decmaillRadioButton = (RadioButton) findViewById(R.id.decimal);
        if(decmaillRadioButton.isChecked()){
            currentSystem = decmaillRadioButton.getText().toString();
            updateConversions(currentSystem);
        }

        RadioButton hexlRadioButton = (RadioButton) findViewById(R.id.hexdecimal);
        if(hexlRadioButton.isChecked()){
            currentSystem = hexlRadioButton.getText().toString();
            updateConversions(currentSystem);
        }
    }
}
