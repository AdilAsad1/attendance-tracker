package edu.psu.afa6316.lioncheckin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

    EditText signUpPage_username, signUpPage_password, signUpPage_confirmPassword;
    boolean isAllFieldsChecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_page);

        Button signupButton = findViewById(R.id.button_gotoSignUp);

        signUpPage_username = findViewById(R.id.signup_username);
        signUpPage_password = findViewById(R.id.signup_password);
        signUpPage_confirmPassword = findViewById(R.id.signup_passwordRetype);

        signupButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                isAllFieldsChecked = CheckAllFields();

                if(isAllFieldsChecked) {
                    Toast.makeText(SignUpActivity.this, "User added", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private boolean CheckAllFields(){
        if(signUpPage_username.length() == 0){
            signUpPage_username.setError("This is a required field");
            return false;
        }
        if(signUpPage_confirmPassword.length() == 0){
            signUpPage_confirmPassword.setError("This is a required field");
            return false;
        }
        if(signUpPage_password.length() == 0){
            signUpPage_password.setError("This is a required field");
            return false;
        }
        if(!signUpPage_password.getText().toString().equals(signUpPage_confirmPassword.getText().toString())){
            signUpPage_confirmPassword.setError("Passwords should match");
            return false;
        }

        return true;
    }
}