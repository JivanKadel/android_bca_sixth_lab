package com.jivan.lab_jivan_kadel.recycler;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jivan.lab_jivan_kadel.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ContactActivity extends AppCompatActivity {

    RecyclerView recyclerViewContacts;
    List<Contact> contactList = new ArrayList<>(Arrays.asList(
            new Contact("Jivan Kadel", "9812345678"),
            new Contact("Pratik Satta", "9822345678"),
            new Contact("Pranil Shrestha", "9832345678"),
            new Contact("Anuj Shrestha", "9842345678"),
            new Contact("Kishor BK", "9852345678"),
            new Contact("Razz Awasthi", "9862345678"),
            new Contact("Sworup Dhakal", "9872345678")
    ));
    ContactAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_contact);

        recyclerViewContacts = findViewById(R.id.rvContacts);
        adapter = new ContactAdapter(contactList, position -> Toast.makeText(this, "Name: " + contactList.get(position).getName() + "\nContact: " + contactList.get(position).getContact(), Toast.LENGTH_LONG).show());
        recyclerViewContacts.setAdapter(adapter);
        recyclerViewContacts.setLayoutManager(new LinearLayoutManager(this));

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            var paddingLeft = findViewById(R.id.main).getPaddingLeft();
            v.setPadding(paddingLeft, systemBars.top, paddingLeft, systemBars.bottom);
            return insets;
        });
    }
}