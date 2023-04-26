package edu.psu.afa6316.lioncheckin;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import edu.psu.afa6316.lioncheckin.db.Class;
import edu.psu.afa6316.lioncheckin.db.ClassViewModel;

public class MainActivity extends AppCompatActivity {

    private ClassViewModel classViewModel;
    private LayoutInflater layoutInflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layoutInflater = LayoutInflater.from(this);

        RecyclerView recyclerView = findViewById(R.id.list_class);

        ClassListAdapter adapter = new ClassListAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        classViewModel = new ViewModelProvider(this).get(ClassViewModel.class);
        classViewModel.getAllClasses().observe(this, classes -> adapter.setClasses(classes));


        FloatingActionButton addClassButton = findViewById(R.id.add_class_floating_button);

        addClassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddClassActivity.class);
                startActivity(intent);
            }
        });
    }

    private class ClassListAdapter extends RecyclerView.Adapter<ClassViewHolder> {
        private List<Class> classes;

        @NonNull
        @Override
        public ClassViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = layoutInflater.inflate(R.layout.class_list_item, parent, false);
            return new ClassViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull ClassViewHolder holder, int position) {
            Class currentClass = classes.get(position);
            holder.nameView.setText(currentClass.getClassName());
        }

        void setClasses(List<Class> classes) {
            this.classes = classes;
            notifyDataSetChanged();
        }

        @Override
        public int getItemCount() {
            if (classes != null)
                return classes.size();
            else
                return 0;
        }


    }

    private static class ClassViewHolder extends RecyclerView.ViewHolder {
        private final TextView nameView;

        private ClassViewHolder(View itemView) {
            super(itemView);
            nameView = itemView.findViewById(R.id.class_list_item);
        }
    }
}
