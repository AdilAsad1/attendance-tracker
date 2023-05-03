package edu.psu.afa6316.lioncheckin;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import edu.psu.afa6316.lioncheckin.db.AttendanceDatabase;
import edu.psu.afa6316.lioncheckin.db.Class;

public class AddClassActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_class);

        Button createClassButton = findViewById(R.id.button_createClass);
        EditText classSubjectEditText = findViewById(R.id.subjectName_createClass);
        EditText classNameEditText = findViewById(R.id.className_createClass);

        createClassButton.setOnClickListener(view -> {
            String classSubject = classSubjectEditText.getText().toString();
            String className = classNameEditText.getText().toString();

            Class class_ = new Class(0, classSubject,className);
            AttendanceDatabase.insertClass(class_);

            Toast.makeText(AddClassActivity.this, "Class successfully created", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(AddClassActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }
}


