package com.jivan.lab_jivan_kadel;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.jivan.lab_jivan_kadel.btn_event.ButtonEventActivity;
import com.jivan.lab_jivan_kadel.dialog.AlertActivity;
import com.jivan.lab_jivan_kadel.json.PostsActivity;
import com.jivan.lab_jivan_kadel.layout.LoginActivity;
import com.jivan.lab_jivan_kadel.list.CountryListActivity;
import com.jivan.lab_jivan_kadel.options.OptionsActivity;
import com.jivan.lab_jivan_kadel.recycler.ContactActivity;
import com.jivan.lab_jivan_kadel.student_db.StudentActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnLab1, btnLab2, btnLab3, btnLab4, btnLab5, btnLab6, btnLab7, btnLab8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btnLab1 = findViewById(R.id.btnLab1);
        btnLab2 = findViewById(R.id.btnLab2);
        btnLab3 = findViewById(R.id.btnLab3);
        btnLab4 = findViewById(R.id.btnLab4);
        btnLab5 = findViewById(R.id.btnLab5);
        btnLab6 = findViewById(R.id.btnLab6);
        btnLab7 = findViewById(R.id.btnLab7);
        btnLab8 = findViewById(R.id.btnLab8);

        btnLab1.setOnClickListener(this);
        btnLab2.setOnClickListener(this);
        btnLab3.setOnClickListener(this);
        btnLab4.setOnClickListener(this);
        btnLab5.setOnClickListener(this);
        btnLab6.setOnClickListener(this);
        btnLab7.setOnClickListener(this);
        btnLab8.setOnClickListener(this);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void startActivity(Class destination) {
        Intent intent = new Intent(this, destination);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btnLab1) {
            startActivity(LoginActivity.class);
        } else if (id == R.id.btnLab2) {
            startActivity(ButtonEventActivity.class);
        } else if (id == R.id.btnLab3) {
            startActivity(ContactActivity.class);
        } else if (id == R.id.btnLab4) {
            startActivity(OptionsActivity.class);
        } else if (id == R.id.btnLab5) {
            startActivity(AlertActivity.class);
        } else if (id == R.id.btnLab6) {
            startActivity(CountryListActivity.class);
        } else if (id == R.id.btnLab7) {
            startActivity(StudentActivity.class);
        } else if (id == R.id.btnLab8) {
            startActivity(PostsActivity.class);
        }
    }
}

