package com.jivan.lab_jivan_kadel.dialog;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.jivan.lab_jivan_kadel.R;

public class AlertActivity extends AppCompatActivity {
    Button btnShowDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_alert);

        btnShowDialog = findViewById(R.id.btnShowDialog);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        btnShowDialog.setOnClickListener(v -> {
            showAlertDialog();
        });
    }

    public void showAlertDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("JK_10 Alert Dialog")
                .setMessage("This is an alert dialog. You can choose to proceed or cancel!")
                .setPositiveButton("Confirm", (dialog, which) -> {
                    Toast.makeText(this, "You have pressed \"Confirm\"", Toast.LENGTH_LONG).show();
                })
                .setNegativeButton("Cancel", (dialog, which) -> {
                    Toast.makeText(this, "You have pressed \"Cancel\"", Toast.LENGTH_LONG).show();
                })
                .setCancelable(false)
                .show();
    }
}