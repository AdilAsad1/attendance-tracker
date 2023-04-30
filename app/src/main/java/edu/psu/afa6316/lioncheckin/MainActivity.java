package edu.psu.afa6316.lioncheckin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.list_class);
        ClassListAdapter adapter = new ClassListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        classViewModel = new ViewModelProvider(this).get(ClassViewModel.class);
        classViewModel.getAll().observe(this, adapter::setClasses);


        FloatingActionButton addClassButton = findViewById(R.id.add_class_floating_button);

        addClassButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AddClassActivity.class);
            startActivity(intent);
        });
    }



   public class ClassListAdapter extends RecyclerView.Adapter<ClassListAdapter.ClassViewHolder> {

        class ClassViewHolder extends RecyclerView.ViewHolder {
           private final TextView classSubjectView;
           private Class class_;

           private ClassViewHolder(View itemView) {
               super(itemView);
               classSubjectView = itemView.findViewById(R.id.class_list);

               itemView.setOnClickListener(view -> {
                   Intent intent = new Intent(MainActivity.this, ClassDetailsActivity.class);
                   intent.putExtra("class_id", class_.id);
                   startActivity(intent);

               });
           }
       }

       private final LayoutInflater layoutInflater;
       private List<Class> classes;

       ClassListAdapter(Context context) {
           layoutInflater = LayoutInflater.from(context);
       }

       @Override
       public ClassViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
           View itemView = layoutInflater.inflate(R.layout.class_list_item, parent,false);
           return new ClassViewHolder(itemView);
       }

       @Override
       public void onBindViewHolder(ClassViewHolder holder, int position) {
           if (classes != null){
           Class current = classes.get(position);
           holder.class_ = current;
           holder.classSubjectView.setText(current.classSubject);
       } else {
           // Covers the case of data not being ready yet.
           holder.classSubjectView.setText(R.string.initializing);
       }
       }
       @Override
       public int getItemCount() {
           if (classes != null) {
               return classes.size();
           }
           else return 0;
       }

       void setClasses(List<Class> classes){
           this.classes = classes;
           notifyDataSetChanged();
       }


    }
}


