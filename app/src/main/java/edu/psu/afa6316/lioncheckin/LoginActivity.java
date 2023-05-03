package edu.psu.afa6316.lioncheckin;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import android.media.MediaPlayer;


public class LoginActivity extends AppCompatActivity {

    private static final String USERNAME_KEY = "username";
    private static final String PASSWORD_KEY = "password";

    EditText username_field, password_field;

    boolean isAllFieldsChecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        FirebaseApp.initializeApp(this);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        if (savedInstanceState != null) {
            // Restore any saved state
            String savedUsername = savedInstanceState.getString(USERNAME_KEY);
            String savedPassword = savedInstanceState.getString(PASSWORD_KEY);
            username_field.setText(savedUsername);
            password_field.setText(savedPassword);
        }



        Button loginButton = findViewById(R.id.button_login);
        Button signUpButton = findViewById(R.id.button_signup);


        //Form Validation
        username_field = findViewById(R.id.loginPage_username);
        password_field = findViewById(R.id.loginPage_password);

        loginButton.setOnClickListener(view -> {

            String username = username_field.getText().toString();
            String password = password_field.getText().toString();
            isAllFieldsChecked = CheckAllFields();

            if(isAllFieldsChecked) {
                mAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener(LoginActivity.this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        MediaPlayer mediaPlayer = MediaPlayer.create(LoginActivity.this,R.raw.success);
                        mediaPlayer.start();
                        mediaPlayer.setOnCompletionListener(mp -> {
                            new android.os.Handler().postDelayed(() -> {
                                mp.release();
                            }, 1000);
                        });
                        Intent i = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(i);
                        finish();
                        Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(LoginActivity.this, "Login failed.", Toast.LENGTH_SHORT).show();
                    }

                });

            }
        });

        signUpButton.setOnClickListener(view -> {
            Intent signUp_intent = new Intent(LoginActivity.this, SignUpActivity.class);
            startActivity(signUp_intent);
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

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        String username = username_field.getText().toString();
        String password = password_field.getText().toString();
        outState.putString(USERNAME_KEY, username);
        outState.putString(PASSWORD_KEY, password);
    }


}