package edu.psu.afa6316.lioncheckin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.psu.afa6316.lioncheckin.db.AttendanceDatabase;
import edu.psu.afa6316.lioncheckin.db.Student;
import edu.psu.afa6316.lioncheckin.db.StudentViewModel;

public class ClassDetailsActivity extends AppCompatActivity {
    private StudentViewModel studentViewModel;
    private int class_id;
    private String class_name;
    private TextView classNameTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.class_details);
        classNameTextView = findViewById(R.id.class_details_classname);
        class_id = getIntent().getIntExtra("class_id",-1);
        class_name = getIntent().getStringExtra("class_name");
        Log.d("Here1", "setClassId: " + class_name);

        classNameTextView.setText(class_name);
        RecyclerView recyclerView = findViewById(R.id.class_details_students_list);
        StudentListAdapter adapter = new StudentListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        studentViewModel.setClassId(class_id);
        studentViewModel = new ViewModelProvider(this).get(StudentViewModel.class);
        studentViewModel.getAll().observe(this,adapter::setStudents);



        Button add_students_button = findViewById(R.id.add_students_button);

        add_students_button.setOnClickListener(view -> {
            Intent intent = new Intent(ClassDetailsActivity.this, AddStudentActivity.class);
            intent.putExtra("class_id", class_id);
            startActivity(intent);
        });

        Button settingsButton = findViewById(R.id.settings_button);
        settingsButton.setOnClickListener(v -> {
            Intent intent = new Intent(ClassDetailsActivity.this, SettingsActivity.class);
            startActivity(intent);
        });

        Button submitButton = findViewById(R.id.class_details_submitButton);
        submitButton.setOnClickListener(v -> {
            Intent intent = new Intent(ClassDetailsActivity.this, MainActivity.class );
            startActivity(intent);
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        StudentListAdapter adapter = new StudentListAdapter(this);
        studentViewModel.getAll().observe(this, adapter::setStudents);
    }

//    public void displaySetup(int class_id) {
//        AttendanceDatabase.getStudents(class_id, student -> {
//            Bundle args = new Bundle();
//            args.putInt("id", student.studentId);
//            args.putString("name", student.name);
//
//        });
//    }

    public class StudentListAdapter extends RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>{

        class StudentViewHolder extends RecyclerView.ViewHolder{
            private final TextView studentNameView;
            private Student student;

            private StudentViewHolder(View itemView){
                super(itemView);
                studentNameView = itemView.findViewById(R.id.student_list);
//                itemView.setOnClickListener(view -> displaySetup(class_id));


            }
        }
        private final LayoutInflater layoutInflater;
        private List<Student> students;

        StudentListAdapter(Context context){
            layoutInflater = LayoutInflater.from(context);
        }

        @NonNull
        @Override
        public StudentViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
            View itemView = layoutInflater.inflate(R.layout.student_list_item,parent, false);
            return new StudentViewHolder(itemView);
        }
        @Override
        public void onBindViewHolder(StudentViewHolder holder, int position){
            if (students != null){
                Student current = students.get(position);
                holder.student = current;
                holder.studentNameView.setText(current.name);
            }
            else{
                holder.studentNameView.setText("...initializing...");
            }
        }
        @Override
        public int getItemCount() {
            if (students != null) {
                return students.size();
            }
            else return 0;
        }

        void setStudents(List<Student> students){
            this.students = students;
            notifyDataSetChanged();
        }

    }

}
