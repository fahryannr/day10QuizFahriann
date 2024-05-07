package com.example.day10;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private RadioGroup rgPilihan, rgEstimasi;
    private EditText etTotal;
    private Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rgPilihan = findViewById(R.id.rgPilihan);
        rgEstimasi = findViewById(R.id.rgEstimasi);
        etTotal = findViewById(R.id.etTotal);
        btnSend = findViewById(R.id.btnSend);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedCarId = rgPilihan.getCheckedRadioButtonId();
                RadioButton selectedCarRadioButton = findViewById(selectedCarId);
                String selectedCar = selectedCarRadioButton.getText().toString();

                int selectedCityId = rgEstimasi.getCheckedRadioButtonId();
                RadioButton selectedCityRadioButton = findViewById(selectedCityId);
                String selectedCity = selectedCityRadioButton.getText().toString();

                String rentDaysStr = etTotal.getText().toString();
                int rentDays = Integer.parseInt(rentDaysStr);

                double basePrice;
                switch (selectedCar) {
                    case "Pajero":
                        basePrice = 2400000;
                        break;
                    case "Alpard":
                        basePrice = 1550000;
                        break;
                    case "Innova":
                        basePrice = 850000;
                        break;
                    case "Brio":
                        basePrice = 550000;
                        break;
                    default:
                        basePrice = 0;
                }

                double totalPrice = basePrice * rentDays;

                // Add additional charges for inside or outside city
                if (selectedCity.equals("Inside city")) {
                    totalPrice += 135000;
                } else if (selectedCity.equals("Outside city")) {
                    totalPrice += 250000;
                }

                // Apply discount based on total price
                if (totalPrice > 10000000) { // If total price is more than 10 million
                    totalPrice *= 0.93; // 7% discount
                } else if (totalPrice > 5000000) { // If total price is more than 5 million
                    totalPrice *= 0.95; // 5% discount
                }

                // Example: Intent to DetailActivity
                Intent intent = new Intent(MainActivity.this, detaill.class);
                intent.putExtra("Pilihan", selectedCar);
                intent.putExtra("Estimasi", selectedCity);
                intent.putExtra("rental", rentDays);
                intent.putExtra("total", totalPrice);
                startActivity(intent);
            }
        });
    }
}