package edu.psu.afa6316.lioncheckin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText username_field, password_field;

    boolean isAllFieldsChecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);


        Button loginButton = findViewById(R.id.button_login);
        Button signUpButton = findViewById(R.id.button_signup);

        //Form Validation
        username_field = findViewById(R.id.loginPage_username);
        password_field = findViewById(R.id.loginPage_password);

        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                isAllFieldsChecked = CheckAllFields();

                if(isAllFieldsChecked) {
                    Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                    Intent mainActivity_intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(mainActivity_intent);
                }
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent signUp_intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(signUp_intent);
            }
        });

    }

    private boolean CheckAllFields(){
        if(username_field.length() == 0){
            username_field.setError("This field is required");
            return false;
        }

        if(password_field.length() == 0){
            password_field.setError("This field is required");
            return false;
        }
        return true;
    }


}