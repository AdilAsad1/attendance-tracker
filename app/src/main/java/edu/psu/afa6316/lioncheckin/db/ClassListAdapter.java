package edu.psu.afa6316.lioncheckin.db;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.psu.afa6316.lioncheckin.R;
import edu.psu.afa6316.lioncheckin.db.Class;

public class ClassListAdapter extends RecyclerView.Adapter<ClassListAdapter.ClassViewHolder> {

    private List<edu.psu.afa6316.lioncheckin.db.Class> classes;

    public ClassListAdapter(List<Class> classes) {
        this.classes = classes;
    }

    @NonNull
    @Override
    public ClassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.class_list_item, parent, false);
        return new ClassViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassViewHolder holder, int position) {
        // Bind the data for the given position
        Class current = classes.get(position);
        holder.nameView.setText(current.getClassName());
    }

    @Override
    public int getItemCount() {
        if (classes != null) {
            return classes.size();
        } else {
            return 0;
        }
    }

    static class ClassViewHolder extends RecyclerView.ViewHolder {

        private final TextView nameView;

        private ClassViewHolder(View itemView) {
            super(itemView);
            nameView = itemView.findViewById(R.id.class_list_item);
        }
    }
}
