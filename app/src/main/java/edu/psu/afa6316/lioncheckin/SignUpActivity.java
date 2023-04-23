package edu.psu.afa6316.lioncheckin;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

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



        signupButton.setOnClickListener(view -> {
            FirebaseAuth mAuth;
            mAuth = FirebaseAuth.getInstance();
            String username = signUpPage_username.getText().toString();
            String password = signUpPage_password.getText().toString();

            isAllFieldsChecked = CheckAllFields();

            if(isAllFieldsChecked) {
                mAuth.createUserWithEmailAndPassword(username, password).addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        System.out.println("Hello!");
                        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                        startActivity(intent);
                        Toast.makeText(SignUpActivity.this, "User added", Toast.LENGTH_SHORT).show();
                        FirebaseUser user = mAuth.getCurrentUser();
                        finish();
                    } else {
                        Toast.makeText(SignUpActivity.this, "Failed to register user", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

    private boolean CheckAllFields(){
        if(signUpPage_username.length() == 0){
            signUpPage_username.setError("This is a required field");
            return false;
        }
        if(signUpPage_password.length() == 0){
            signUpPage_password.setError("This is a required field");
            return false;
        }
        if(signUpPage_confirmPassword.length() == 0){
            signUpPage_confirmPassword.setError("This is a required field");
            return false;
        }
        if(!signUpPage_password.getText().toString().equals(signUpPage_confirmPassword.getText().toString())){
            signUpPage_confirmPassword.setError("Passwords should match");
            return false;
        }

        return true;
    }
}