package edu.psu.afa6316.lioncheckin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import edu.psu.afa6316.lioncheckin.db.AttendanceDatabase;
import edu.psu.afa6316.lioncheckin.db.Class;
import edu.psu.afa6316.lioncheckin.db.Student;

public class AddStudentActivity extends AppCompatActivity {
    private int class_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_student);

        class_id = getIntent().getIntExtra("class_id",-1);

        Button addStudentButton = findViewById(R.id.add_student_addButton);
        Button cancelButton = findViewById(R.id.add_student_cancelButton);
        EditText studentNameEditText = findViewById(R.id.add_student_studentName);
        EditText studentIdEditText = findViewById(R.id.add_student_studentID);


        addStudentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String studentName = studentNameEditText.getText().toString();
                String studentId = studentIdEditText.getText().toString();

                Student student = new Student(studentName,0,class_id);
                AttendanceDatabase.insertStudent(student);

                Toast.makeText(AddStudentActivity.this, "Student successfully added", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AddStudentActivity.this, ClassDetailsActivity.class);
                startActivity(intent);
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(AddStudentActivity.this, ClassDetailsActivity.class);
                startActivity(intent);
            }
        });
    }
}
