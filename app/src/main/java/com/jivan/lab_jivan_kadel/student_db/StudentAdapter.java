package com.jivan.lab_jivan_kadel.student_db;

import android.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jivan.lab_jivan_kadel.R;

import java.util.Locale;
import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {
    List<Student> studentList;
    StudentActivity activity;

    public StudentAdapter(List<Student> studentList, StudentActivity activity) {
        this.studentList = studentList;
        this.activity = activity;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Student student = studentList.get(position);
        holder.tvStudentNameAge.setText(String.format(Locale.getDefault(), "%s, %d", student.getName(), student.getAge()));
        holder.tvStudentFaculty.setText(student.getFaculty());

        holder.itemView.setOnLongClickListener(v -> {
            showUpdateStudentDialog(student, position);
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvStudentNameAge, tvStudentFaculty;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvStudentNameAge = itemView.findViewById(R.id.tvStudentNameAge);
            tvStudentFaculty = itemView.findViewById(R.id.tvStudentFaculty);
        }
    }

    public void showUpdateStudentDialog(Student student, int position) {
        View dialogView = LayoutInflater.from(activity).inflate(R.layout.dialog_add_student, null);
        EditText etName = dialogView.findViewById(R.id.etStudentName);
        EditText etAge = dialogView.findViewById(R.id.etStudentAge);
        EditText etFaculty = dialogView.findViewById(R.id.etStudentFaculty);

        if (student != null) {
//            Log.d("STUDENT RECORD", student.getName());
//            Log.d("STUDENT RECORD", String.valueOf(student.getAge()));
//            Log.d("STUDENT RECORD", student.getFaculty());
            etName.setText(student.getName());
            etAge.setText(String.valueOf(student.getAge()));
            etFaculty.setText(student.getFaculty());
        }

        new AlertDialog.Builder(activity)
                .setTitle("Update Student")
                .setView(dialogView)
                .setPositiveButton("Update", (dialog, which) -> {

                    if (!etName.getText().toString().isEmpty() && !etAge.getText().toString().isEmpty() && !etFaculty.getText().toString().isEmpty()) {
                        student.setName(etName.getText().toString());
                        student.setAge(Integer.parseInt(etAge.getText().toString()));
                        student.setFaculty(etFaculty.getText().toString());
                    }

                    activity.db.updateStudent(student.getId(), student.getName(), student.getAge(), student.getFaculty());
                    studentList.set(position, student);
                    notifyItemChanged(position);
                    Toast.makeText(activity, "Student Updated!", Toast.LENGTH_LONG).show();
                })
                .setNegativeButton("Cancel", null)
                .show();
    }
}
