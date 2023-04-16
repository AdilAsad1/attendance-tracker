package edu.psu.afa6316.lioncheckin;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddClassActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_class);

        Button createClassButton = findViewById(R.id.button_createClass);


        createClassButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Toast.makeText(AddClassActivity.this, "Class successfully created", Toast.LENGTH_SHORT).show();
            }
        });

    }
}


