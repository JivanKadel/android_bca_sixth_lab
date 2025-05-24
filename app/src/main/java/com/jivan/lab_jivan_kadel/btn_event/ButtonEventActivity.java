package com.jivan.lab_jivan_kadel.btn_event;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.jivan.lab_jivan_kadel.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ButtonEventActivity extends AppCompatActivity {

    TextView tvTimeStamp;
    Button btnTimeStamp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_button_event);

        tvTimeStamp = findViewById(R.id.tvTimeStamp);
        btnTimeStamp = findViewById(R.id.btnTimeStamp);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            var paddingLeft = findViewById(R.id.main).getPaddingLeft();
            v.setPadding(paddingLeft, systemBars.top, paddingLeft, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        btnTimeStamp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date now = new Date();
                // Option 1: Using SimpleDateFormat (as you had)
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd h:mm:ss a");
                String timestamp = sdf.format(now);
//                DateFormat dateTimeFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM, Locale.getDefault());
//                String timestamp = dateTimeFormat.format(now);
                tvTimeStamp.setText(timestamp);
            }
        });
    }
}