package com.jivan.lab_jivan_kadel.student_db;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jivan.lab_jivan_kadel.R;

import java.util.List;

public class StudentActivity extends AppCompatActivity {

    FloatingActionButton fabAddStudent;
    TextView tvStudentTitle;
    public StudentDatabaseHelper db;
    List<Student> studentList;

    RecyclerView recyclerView;
    StudentAdapter studentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_student);

        fabAddStudent = findViewById(R.id.fabAddStudent);
        tvStudentTitle = findViewById(R.id.tvStudentTitle);

        db = new StudentDatabaseHelper(this);
        studentList = db.getAllStudents();

        changeTitle();

        recyclerView = findViewById(R.id.recyclerViewStudents);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        studentAdapter = new StudentAdapter(studentList, this);
        recyclerView.setAdapter(studentAdapter);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.studentActivity), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            var padding = findViewById(R.id.studentActivity).getPaddingLeft();
            v.setPadding(padding, systemBars.top, padding, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        fabAddStudent.setOnClickListener(v -> showAddStudentDialog());
        setupSwipeToDelete(recyclerView);

    }

    private void showAddStudentDialog() {
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_student, null);
        EditText etName = dialogView.findViewById(R.id.etStudentName);
        EditText etAge = dialogView.findViewById(R.id.etStudentAge);
        EditText etFaculty = dialogView.findViewById(R.id.etStudentFaculty);

        new AlertDialog.Builder(this)
                .setView(dialogView)
                .setPositiveButton("Add", (dialog, which) -> {
                    String name = etName.getText().toString();
                    int age = Integer.parseInt(etAge.getText().toString());
                    String grade = etFaculty.getText().toString();

                    db.addStudent(name, age, grade);
                    studentList.clear();
                    studentList.addAll(db.getAllStudents());
                    changeTitle();
                    studentAdapter.notifyDataSetChanged();

                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void setupSwipeToDelete(RecyclerView recyclerView) {
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                Student student = studentList.get(position);
                db.deleteStudent(student.getId());
                studentList.remove(position);
                studentAdapter.notifyItemRemoved(position);
                changeTitle();
                Toast.makeText(StudentActivity.this, "Student Deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);
    }

    public void changeTitle() {
        if (studentList.isEmpty()) {
            tvStudentTitle.setText("No Student Record to Show!");

        } else {
            tvStudentTitle.setText("Students");
        }
    }
}