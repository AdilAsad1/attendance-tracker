package edu.psu.afa6316.lioncheckin;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.psu.afa6316.lioncheckin.db.Student;
import edu.psu.afa6316.lioncheckin.db.StudentViewModel;


public class MarkAttendanceActivity extends AppCompatActivity {
    public MarkAttendanceActivity(LayoutInflater layoutInflater) {
        this.layoutInflater = layoutInflater;
    }

    private StudentViewModel studentViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_details);


        RecyclerView recyclerView = findViewById(R.id.attendance);
        StudentListAdapter adapter = new StudentListAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        studentViewModel = new ViewModelProvider(this).get(StudentViewModel.class);
        StudentViewModel.getStudentsByClassId(id).observe(this, adapter::setStudents);
    }

    private LayoutInflater layoutInflater;
    private List<Student> students; // Cached copy of jokes

    void StudentListAdapter(Context context) {
        layoutInflater = LayoutInflater.from(context);
    }


    public class StudentListAdapter extends RecyclerView.Adapter<StudentListAdapter.StudentViewHolder> {
        private List<Student> students;

        @NonNull
        @Override
        public StudentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = layoutInflater.inflate(R.layout.student_list_item, parent, false);
            return new StudentViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        }

        void setStudents(List<Student> students){
            this.students = students;
            notifyDataSetChanged();
        }

        @Override
        public int getItemCount() {
            if (students != null)
                return students.size();
            else return 0;
        }


        class StudentViewHolder extends RecyclerView.ViewHolder {
            private final TextView nameView;
            private final CheckBox attendanceView;
            private Student student;


            private StudentViewHolder(View itemView) {
                super(itemView);
                nameView = itemView.findViewById(R.id.student_name);
                attendanceView = itemView.findViewById(R.id.attendance);


            }
        }


    }
}
