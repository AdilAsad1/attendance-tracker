package edu.psu.afa6316.lioncheckin;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import edu.psu.afa6316.lioncheckin.db.AttendanceDatabase;
import edu.psu.afa6316.lioncheckin.db.Class;
import edu.psu.afa6316.lioncheckin.db.ClassDao;

public class AddClassActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_class);

        Button createClassButton = findViewById(R.id.button_createClass);
        EditText classSubjectEditText = findViewById(R.id.subjectName_createClass);
        EditText classNameEditText = findViewById(R.id.className_createClass);

        createClassButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String classSubject = classSubjectEditText.getText().toString();
                String className = classNameEditText.getText().toString();
                Context context = getApplicationContext();
                AttendanceDatabase db = AttendanceDatabase.getInstance(context);
                ClassDao classDao = db.classDao();
                Class class_ = new Class(0, classSubject,className);

                new InsertClassTask(classDao).execute(class_);

                Toast.makeText(AddClassActivity.this, "Class successfully created", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AddClassActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private static class InsertClassTask extends AsyncTask<Class, Void, Void> {
        private ClassDao classDao;

        public InsertClassTask(ClassDao classDao) {
            this.classDao = classDao;
        }

        @Override
        protected Void doInBackground(Class... classes) {
            classDao.insertClass(classes[0]);
            return null;
        }
    }
}


