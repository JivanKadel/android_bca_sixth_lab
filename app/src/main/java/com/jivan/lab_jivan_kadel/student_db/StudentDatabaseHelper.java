package com.jivan.lab_jivan_kadel.student_db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

public class StudentDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "student_db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Student";

    // Column names
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_AGE = "age";
    private static final String COLUMN_FACULTY = "faculty";

    // Create Table SQL
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NAME + " TEXT, "
            + COLUMN_AGE + " INTEGER, "
            + COLUMN_FACULTY + " TEXT)";

    public StudentDatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // add student to table
    public int addStudent(String name, int age, String faculty) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_AGE, age);
        values.put(COLUMN_FACULTY, faculty);

        int columnsInserted = (int) db.insert(TABLE_NAME, null, values);
        db.close();
        return columnsInserted;
    }

    // get all students
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(COLUMN_ID);
            int nameIndex = cursor.getColumnIndex(COLUMN_NAME);
            int ageIndex = cursor.getColumnIndex(COLUMN_AGE);
            int facultyIndex = cursor.getColumnIndex(COLUMN_FACULTY);

            if (nameIndex != -1 && ageIndex != -1 && facultyIndex != -1) {
                do {
                    int id = cursor.getInt(idIndex);
                    String name = cursor.getString(nameIndex);
                    int age = cursor.getInt(ageIndex);
                    String faculty = cursor.getString(facultyIndex);

                    students.add(new Student(id, name, age, faculty));
                } while (cursor.moveToNext());
            }

        }
        cursor.close();
        db.close();
        return students;
    }

    // Update Student
    public int updateStudent(int id, String name, int age, String faculty) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_NAME, name);
        values.put(COLUMN_AGE, age);
        values.put(COLUMN_FACULTY, faculty);

        int columnsUpdated = db.update(TABLE_NAME, values, COLUMN_ID + " = ? ", new String[]{String.valueOf(id)});

        db.close();
        return columnsUpdated;
    }

    // Delete student
    public int deleteStudent(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int deletedColumnCount = db.delete(TABLE_NAME, COLUMN_ID + " = ? ", new String[]{String.valueOf(id)});
        db.close();
        return deletedColumnCount;
    }

    public Student getStudentById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Student student = null;

        Cursor cursor = db.query(TABLE_NAME, new String[]{COLUMN_ID, COLUMN_NAME, COLUMN_AGE, COLUMN_FACULTY}, COLUMN_ID + " = ? ", new String[]{String.valueOf(id)}, null, null, null);

        student = new Student(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getInt(2),
                cursor.getString(3)
        );
        cursor.close();

        db.close();
        return student;
    }
}
