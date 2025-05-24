package com.jivan.lab_jivan_kadel.options;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.jivan.lab_jivan_kadel.R;

public class OptionsActivity extends AppCompatActivity {
    TextView tvMenuText;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_options);

        tvMenuText = findViewById(R.id.tvSelectedMenuText);
        toolbar = findViewById(R.id.tbOptions);
        setSupportActionBar(toolbar);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            var padding = findViewById(R.id.main).getPaddingLeft();
            v.setPadding(padding, systemBars.top, padding, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        var id = item.getItemId();
        String tvContent;
        if (id == R.id.menu_item_add) {
            tvContent = "Add Menu Selected";
            tvMenuText.setText(tvContent);
            return true;
        } else if (id == R.id.menu_item_edit) {
            tvContent = "Edit Menu Selected";
            tvMenuText.setText(tvContent);
            return true;
        } else if (id == R.id.menu_item_delete) {
            tvContent = "Delete Menu Selected";
            tvMenuText.setText(tvContent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}