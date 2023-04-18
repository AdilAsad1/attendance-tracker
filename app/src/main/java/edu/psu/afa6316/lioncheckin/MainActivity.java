package edu.psu.afa6316.lioncheckin;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import edu.psu.afa6316.lioncheckin.db.ClassViewModel;
import edu.psu.afa6316.lioncheckin.db.Student;
import edu.psu.afa6316.lioncheckin.db.StudentViewModel;

public class MainActivity extends AppCompatActivity {

    public MainActivity(LayoutInflater layoutInflater) {
        this.layoutInflater = layoutInflater;
    }
    private ClassViewModel classViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.class_details);

        RecyclerView recyclerView = findViewById(R.id.class_list);

        ClassListAdapter adapter = new ClassListAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        classViewModel = new ViewModelProvider(this).get(classViewModel.class);
        ClasViewModel.getStudentsByClassId(id).observe(this, adapter::setStudents);

        FloatingActionButton addClassButton = findViewById(R.id.add_class_floating_button);

        addClassButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this, AddClassActivity.class);
                startActivity(intent);
            }
        });
    }

    private LayoutInflater layoutInflater;

    public class ClassListAdapter extends RecyclerView.Adapter<MainActivity.ClassListAdapter.ClassViewHolder> {
        private List<Class> classes;

        @NonNull
        @Override
        public MainActivity.ClassListAdapter.ClassViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = layoutInflater.inflate(R.layout.class_list_item, parent, false);
            return new MainActivity.ClassListAdapter.ClassViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull MainActivity.ClassListAdapter.ClassViewHolder holder, int position) {
        }

        void setClasses(List<Class> classes){
            this.classes = classes;
            notifyDataSetChanged();
        }

        @Override
        public int getItemCount() {
            if (classes != null)
                return classes.size();
            else return 0;
        }


        class ClassViewHolder extends RecyclerView.ViewHolder {
            private final TextView nameView;


            private ClassViewHolder(View itemView) {
                super(itemView);
                nameView = itemView.findViewById(R.id.class_list_item);


            }
        }
}