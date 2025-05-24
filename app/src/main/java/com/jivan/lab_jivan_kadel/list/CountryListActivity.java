package com.jivan.lab_jivan_kadel.list;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.jivan.lab_jivan_kadel.R;

public class CountryListActivity extends AppCompatActivity {

    ListView countriesList;
    String[] countries = {"Nepal", "India", "USA", "Canada", "Australia", "Germany", "France", "Japan", "Brazil", "South Africa"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_country_list);

        countriesList = findViewById(R.id.lvCountries);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, countries);
        countriesList.setAdapter(arrayAdapter);

        countriesList.setOnItemClickListener((parent, view, position, id) -> {
            String selectedCountry = countries[position];
            Toast.makeText(this, "Selected: " + selectedCountry, Toast.LENGTH_LONG).show();
        });
    }
}